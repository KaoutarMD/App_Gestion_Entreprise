package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Employe;
import models.FicheSalaire;

public class GestionSalaireV2 implements InterfaceGestionSalaire {
 
	GestionEmployesV2 gemp=new GestionEmployesV2();
	
    @Override
    public Boolean ajouterFicheSalaire(FicheSalaire f) {
        String query = "INSERT INTO fichesalaire (numfiche, datef, tauxtt, nbheures, montantbrut, taxe, montantnet, empId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, f.getNumfiche());
            stmt.setDate(2, new java.sql.Date(f.getDatef().getTime()));
            stmt.setDouble(3, f.getTauxtt());
            stmt.setInt(4, f.getNbheures());
            stmt.setDouble(5, f.getMontantbrut());
            stmt.setDouble(6, f.getTaxe());
            stmt.setDouble(7, f.getMontantnet());
            stmt.setInt(8, f.getEmp().getId());
            
         
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean supprimerFicheSalaire(FicheSalaire f) {
        return supprimerFicheSalaire(f.getNumfiche());
    }

    @Override
    public Boolean supprimerFicheSalaire(String numfiche) {
        String query = "DELETE FROM fichesalaire WHERE numfiche = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numfiche);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public FicheSalaire chercherFicheSalaire(String numfiche) {
        String query = "SELECT * FROM fichesalaire f JOIN employe e ON f.empId = e.Id WHERE numfiche = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numfiche);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employe emp = new Employe(rs.getInt("Id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("numTel"));
                FicheSalaire fiche = new FicheSalaire(
                    rs.getString("numfiche"),
                    rs.getDate("datef"),
                    rs.getDouble("tauxtt"),
                    rs.getInt("nbheures"),
                    rs.getDouble("montantbrut"),
                    rs.getDouble("taxe"),
                    rs.getDouble("montantnet"),
                    emp
                );
                return fiche;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public FicheSalaire chercherFicheSalairebyname(String nom) {
        String query = "SELECT * FROM fichesalaire f JOIN employe e ON f.empId = e.Id WHERE e.nom = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employe emp = new Employe(rs.getInt("Id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("numTel"));
                FicheSalaire fiche = new FicheSalaire(
                    rs.getString("numfiche"),
                    rs.getDate("datef"),
                    rs.getDouble("tauxtt"),
                    rs.getInt("nbheures"),
                    rs.getDouble("montantbrut"),
                    rs.getDouble("taxe"),
                    rs.getDouble("montantnet"),
                    emp
                );
                return fiche;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean modifierFicheSalaire(FicheSalaire f, String numfiche) {
        String query = "UPDATE fichesalaire SET datef = ?, tauxtt = ?, nbheures = ?, montantbrut = ?, taxe = ?, montantnet = ?, empId = ? WHERE numfiche = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(f.getDatef().getTime()));
            stmt.setDouble(2, f.getTauxtt());
            stmt.setInt(3, f.getNbheures());
            stmt.setDouble(4, f.getMontantbrut());
            stmt.setDouble(5, f.getTaxe());
            stmt.setDouble(6, f.getMontantnet());
            stmt.setInt(7, f.getEmp().getId());
            stmt.setString(8, numfiche);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<FicheSalaire> getToutesLesFichesSalaires() {
        List<FicheSalaire> fiches = new ArrayList<>();
        String query = "SELECT * FROM fichesalaire f JOIN employe e ON f.empId = e.Id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Employe emp = new Employe(rs.getInt("Id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("numTel"));
                FicheSalaire fiche = new FicheSalaire(
                    rs.getString("numfiche"),
                    rs.getDate("datef"),
                    rs.getDouble("tauxtt"),
                    rs.getInt("nbheures"),
                    rs.getDouble("montantbrut"),
                    rs.getDouble("taxe"),
                    rs.getDouble("montantnet"),
                    emp
                );
                fiches.add(fiche);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fiches;
    }
}
