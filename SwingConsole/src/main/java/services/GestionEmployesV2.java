package services;

import models.Employe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestionEmployesV2 implements InterfaceGestionEmployes {

    @Override
    public void ajouterEmployee(Employe e) {
        String query = "INSERT INTO employe (Id, nom, prenom, adresse, numTel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, e.getId());
            stmt.setString(2, e.getNom());
            stmt.setString(3, e.getPrenom());
            stmt.setString(4, e.getAdresse());
            stmt.setString(5, e.getNumTel());
            stmt.executeUpdate();
            System.out.println("Employé ajouté avec succès.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimerEmployee(Employe e) {
        supprimerEmployee(e.getId());
    }

    @Override
    public void supprimerEmployee(int matricule) {
        String query = "DELETE FROM employe WHERE Id = ?";
        String query2 = "DELETE FROM fichesalaire WHERE empId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
        		PreparedStatement stmt2 = conn.prepareStatement(query2)) {
            stmt.setInt(1, matricule);
            stmt2.setInt(1, matricule);
            stmt2.executeUpdate();
            stmt.executeUpdate();
            System.out.println("Employé supprimé avec succès.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Employe chercherEmployee(int matricule) {
        String query = "SELECT * FROM employe WHERE Id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, matricule);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employe(rs.getInt("Id"), rs.getString("nom"), rs.getString("prenom"),
                                   rs.getString("adresse"), rs.getString("numTel"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Employe chercherEmployee(String nom) {
        String query = "SELECT * FROM employe WHERE nom = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employe(rs.getInt("Id"), rs.getString("nom"), rs.getString("prenom"),
                                   rs.getString("adresse"), rs.getString("numTel"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void modifierEmployee(Employe e, int id) {
        String query = "UPDATE employe SET nom = ?, prenom = ?, adresse = ?, numTel = ? WHERE Id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, e.getNom());
            stmt.setString(2, e.getPrenom());
            stmt.setString(3, e.getAdresse());
            stmt.setString(4, e.getNumTel());
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Employé modifié avec succès.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Employe> getTousLesEmployes() {
        List<Employe> employes = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connexion à la base de données
            connection = DatabaseConnection.getConnection();
            
            // Exécution de la requête SQL pour récupérer les employés
            String sql = "SELECT * FROM employe"; // Vérifiez le nom de la table
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            // Traitement du résultat
            while (rs.next()) {
                int id = rs.getInt("Id"); // Assurez-vous que la colonne "Id" existe
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String numTel = rs.getString("numTel");
                String adresse = rs.getString("adresse");

                Employe emp = new Employe(id, nom, prenom, adresse, numTel);
                employes.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Afficher l'exception pour aider au débogage
        } finally {
            try {
                // Fermeture des ressources
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employes;
    }

}
