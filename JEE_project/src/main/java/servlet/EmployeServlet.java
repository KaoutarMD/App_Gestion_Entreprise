package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Employe;
import services.GestionEmployesV2;

@WebServlet("/EmployeServlet")
public class EmployeServlet extends HttpServlet {
    
    public EmployeServlet() {
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Charger le driver MySQL (si nécessaire)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Vérifier l'action (modifier ou supprimer)
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("edit")) {
                    // Gérer la modification
                    int id = Integer.parseInt(request.getParameter("id"));
                    GestionEmployesV2 gestionemp = new GestionEmployesV2();
                    Employe employe = gestionemp.chercherEmployee(id);
                    request.setAttribute("employe", employe); // Pass the employee data for editing
                    // Redirect to the edit page
                    request.getRequestDispatcher("views/Employe/editEmploye.jsp").forward(request, response);
                    return;
                } else if (action.equals("delete")) {
                    // Gérer la suppression
                    int id = Integer.parseInt(request.getParameter("id"));
                    GestionEmployesV2 gestionemp = new GestionEmployesV2();
                    gestionemp.supprimerEmployee(id);
                    // After deletion, redirect to the list of employees with updated data
                    response.sendRedirect("EmployeServlet");
                    return;
                }  else if (action.equals("add")) {
                	GestionEmployesV2 gestionemp = new GestionEmployesV2();
                    Employe employe = new Employe();
                    request.setAttribute("employe", employe); // Pass the employee data for editing
                    // Redirect to the edit page
                    request.getRequestDispatcher("views/Employe/addEmploye.jsp").forward(request, response);
                    return;
                  
                }
                
            }

            // Show the list of employees
            GestionEmployesV2 gestionemp = new GestionEmployesV2();
            List<Employe> employes = gestionemp.getTousLesEmployes();
            request.setAttribute("employes", employes);
            // Redirect to the employee management page
            request.getRequestDispatcher("views/Employe/GestionEmploye.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle POST for adding and updating employees
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	  // Charger le driver MySQL (si nécessaire)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String action = request.getParameter("action");
            GestionEmployesV2 gestionemp = new GestionEmployesV2();

            if ("add".equals(action)) {
                // Add employee
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String adresse = request.getParameter("adresse");
                String numTel = request.getParameter("numTel");

                // Create the employee object and save it
                Employe employe = new Employe(nom, prenom, adresse, numTel);
                gestionemp.ajouterEmployee(employe);
                response.sendRedirect("EmployeServlet");
            } else if ("update".equals(action)) {
                // Update employee
                int id = Integer.parseInt(request.getParameter("id"));
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String adresse = request.getParameter("adresse");
                String numTel = request.getParameter("numTel");

                // Create the employee object and update it
                Employe employe = new Employe(id, nom, prenom, adresse, numTel);
                gestionemp.modifierEmployee(employe, id);
                response.sendRedirect("EmployeServlet");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
