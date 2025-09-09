package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Employe;
import models.FicheSalaire;
import services.GestionEmployesV2;
import services.GestionSalaireV2;

@WebServlet("/SalaireServlet")
public class SalaireServlet extends HttpServlet {

    private GestionSalaireV2 gsalaire;
    private GestionEmployesV2 gemp;

    public SalaireServlet() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        gsalaire = new GestionSalaireV2();
        gemp=new GestionEmployesV2();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("supprimer".equals(action)) {
            String numfiche = request.getParameter("numfiche");
            gsalaire.supprimerFicheSalaire(numfiche);
            response.sendRedirect("SalaireServlet");

        } else if ("modifier".equals(action)) {
            String numfiche = request.getParameter("id");
          
            FicheSalaire fiche = gsalaire.chercherFicheSalaire(numfiche);

            if (fiche == null) {
                // Handle case when fiche is not found
                request.setAttribute("error", "Fiche de salaire introuvable.");
                request.getRequestDispatcher("views/Fiches/GestionSalaire.jsp").forward(request, response);
            } else {
                request.setAttribute("fiche", fiche);
                request.getRequestDispatcher("views/Fiches/editFiche.jsp").forward(request, response);
            }
        }

        else if ("ajouter".equals(action)) {
            String numfiche = request.getParameter("numfiche");
            FicheSalaire fiche = new FicheSalaire();
            request.setAttribute("fiche", fiche);
            request.getRequestDispatcher("views/Fiches/addFiche.jsp").forward(request, response);

        } 
        
        
        else {
            List<FicheSalaire> fiches = gsalaire.getToutesLesFichesSalaires();
            request.setAttribute("fiches", fiches);
            request.getRequestDispatcher("views/Fiches/GestionSalaire.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            String numfiche = request.getParameter("numfiche");
            Date datef = Date.valueOf(request.getParameter("datef"));
            double tauxtt = Double.parseDouble(request.getParameter("tauxtt"));
            int nbheures = Integer.parseInt(request.getParameter("nbheures"));
            double montantbrut = Double.parseDouble(request.getParameter("montantbrut"));
            double taxe = Double.parseDouble(request.getParameter("taxe"));
            double montantnet = montantbrut - (montantbrut * taxe / 100);
            int empId = Integer.parseInt(request.getParameter("empId"));

            FicheSalaire fiche = new FicheSalaire(numfiche, datef, tauxtt, nbheures, montantbrut, taxe, montantnet, new Employe(empId, "", "", "", ""));
            gsalaire.modifierFicheSalaire(fiche, numfiche);

            response.sendRedirect("SalaireServlet");

        } else if ("add".equals(action)) {
            String numfiche = request.getParameter("numfiche");
            Date datef = Date.valueOf(request.getParameter("datef"));
            double tauxtt = Double.parseDouble(request.getParameter("tauxtt"));
            int nbheures = Integer.parseInt(request.getParameter("nbheures"));
            double montantbrut = Double.parseDouble(request.getParameter("montantbrut"));
            double taxe = Double.parseDouble(request.getParameter("taxe"));
            double montantnet = montantbrut - (montantbrut * taxe / 100);
            int empId = Integer.parseInt(request.getParameter("empId"));
            
            Employe emp = gemp.chercherEmployee(empId);
            if (emp != null) {
             
                FicheSalaire fiche = new FicheSalaire(numfiche, datef, tauxtt, nbheures, montantbrut, taxe, montantnet, new Employe(empId, "", "", "", ""));
                gsalaire.ajouterFicheSalaire(fiche);
                response.sendRedirect("SalaireServlet");
            
            }else {
            	
            	 // Si l'employé n'existe pas, redirigez vers la page d'ajout avec un message
                request.setAttribute("message", "L'employé avec l'ID " + empId + " n'existe pas. Veuillez d'abord l'ajouter.");
                request.getRequestDispatcher("views/Fiches/addFiche.jsp").forward(request, response);
            	
            }
            

            
        }
    }
}
