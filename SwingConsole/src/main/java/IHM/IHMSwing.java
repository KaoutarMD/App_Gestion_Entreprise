package IHM;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Employe;
import models.FicheSalaire;
import services.GestionEmployesV2;
import services.GestionSalaireV2;
import services.InterfaceGestionEmployes;
import services.InterfaceGestionSalaire;

public class IHMSwing {

    InterfaceGestionEmployes gestionemp;
    JFrame mainFrame;
    DefaultTableModel employesTableModel, salaireTableModel;
    JTable employesTable, salaireTable;
    JPanel mainMenuPanel, gestionEmployesOptionsPanel, employesTablePanel, gestionSalaireOptionsPanel, salaireTablePanel;
    CardLayout cardLayout;

    InterfaceGestionSalaire gestionSalaire;

    public IHMSwing() {
        gestionemp = new GestionEmployesV2();
        gestionSalaire = new GestionSalaireV2();
        initUI();
    }

    private void initUI() {

        // Create the main frame
        mainFrame = new JFrame("Gestion des Employés et Salaires");
        mainFrame.setSize(800, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainFrame.setLayout(cardLayout);

        // Main Menu Panel with "Gestion Employés" and "Gestion Salaire" buttons
        mainMenuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton gestionEmployesButton = new JButton("Gestion Employés");
        JButton gestionSalaireButton = new JButton("Gestion Salaire");

        gestionEmployesButton.addActionListener(e -> cardLayout.show(mainFrame.getContentPane(), "GestionEmployesOptionsPanel"));
        gestionSalaireButton.addActionListener(e -> cardLayout.show(mainFrame.getContentPane(), "GestionSalaireOptionsPanel"));

        mainMenuPanel.add(gestionEmployesButton);
        mainMenuPanel.add(gestionSalaireButton);

        // Panel with buttons for "Gestion Employés" options
        gestionEmployesOptionsPanel = new JPanel(new BorderLayout());
        JPanel gestionEmployesButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton afficherEmployesButton = new JButton("Afficher Employés");
        JButton ajouterEmployeButton = new JButton("Ajouter Employé");
        JButton modifierEmployeButton = new JButton("Modifier Employé");
        JButton supprimerEmployeButton = new JButton("Supprimer Employé");
        JButton retourButton1 = new JButton("Retour");

        afficherEmployesButton.addActionListener(e -> chargerEmployes());
        ajouterEmployeButton.addActionListener(e -> saisirEmploye());
        modifierEmployeButton.addActionListener(e -> modifierEmployeeUI());
        supprimerEmployeButton.addActionListener(e -> supprimerEmployeeUI());
        retourButton1.addActionListener(e -> cardLayout.show(mainFrame.getContentPane(), "MainMenuPanel"));

        gestionEmployesButtonsPanel.add(afficherEmployesButton);
        gestionEmployesButtonsPanel.add(ajouterEmployeButton);
        gestionEmployesButtonsPanel.add(modifierEmployeButton);
        gestionEmployesButtonsPanel.add(supprimerEmployeButton);
        gestionEmployesOptionsPanel.add(gestionEmployesButtonsPanel, BorderLayout.CENTER);
        gestionEmployesOptionsPanel.add(retourButton1, BorderLayout.SOUTH);

        // Table Panel to show employees
        employesTablePanel = new JPanel(new BorderLayout());
        employesTableModel = new DefaultTableModel(new Object[]{"Matricule", "Nom", "Prénom", "Numéro de téléphone", "Adresse"}, 0);
        employesTable = new JTable(employesTableModel);

        employesTable.setFillsViewportHeight(true);
        employesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        employesTable.setRowHeight(30);
        JTableHeader header = employesTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(51, 102, 255));
        header.setForeground(Color.WHITE);
        employesTable.setBackground(new Color(245, 245, 245));
        employesTable.setGridColor(Color.BLACK);
        employesTable.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(employesTable);
        employesTablePanel.add(scrollPane, BorderLayout.CENTER);

        JButton retourButton2 = new JButton("Retour");
        retourButton2.addActionListener(e -> cardLayout.show(mainFrame.getContentPane(), "GestionEmployesOptionsPanel"));
        employesTablePanel.add(retourButton2, BorderLayout.SOUTH);

        // Panel for salary management options
        gestionSalaireOptionsPanel = new JPanel(new BorderLayout());
        JPanel gestionSalaireButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton afficherFichesButton = new JButton("Afficher Fiches");
        JButton ajouterFicheButton = new JButton("Ajouter Fiche");
        JButton modifierFicheButton = new JButton("Modifier Fiche");
        JButton supprimerFicheButton = new JButton("Supprimer Fiche");
        JButton retourButton1F = new JButton("Retour");

        afficherFichesButton.addActionListener(e -> chargerFiches());
        ajouterFicheButton.addActionListener(e -> saisirFicheSalaire());
        modifierFicheButton.addActionListener(e -> modifierFicheUI());
        supprimerFicheButton.addActionListener(e -> supprimerFicheUI());
        retourButton1F.addActionListener(e -> cardLayout.show(mainFrame.getContentPane(), "MainMenuPanel"));

        gestionSalaireButtonsPanel.add(afficherFichesButton);
        gestionSalaireButtonsPanel.add(ajouterFicheButton);
        gestionSalaireButtonsPanel.add(modifierFicheButton);
        gestionSalaireButtonsPanel.add(supprimerFicheButton);
        gestionSalaireOptionsPanel.add(gestionSalaireButtonsPanel, BorderLayout.CENTER);
        gestionSalaireOptionsPanel.add(retourButton1F, BorderLayout.SOUTH);

        // Table Panel to show salary records
        salaireTablePanel = new JPanel(new BorderLayout());
        salaireTableModel = new DefaultTableModel(new Object[]{"Num Fiche", "Date", "Taux TT", "Nb Heures", "Montant Net", "Employé"}, 0);
        salaireTable = new JTable(salaireTableModel);

        salaireTable.setFillsViewportHeight(true);
        JScrollPane scrollPaneF = new JScrollPane(salaireTable);
        salaireTablePanel.add(scrollPaneF, BorderLayout.CENTER);

        JButton retourButton2F = new JButton("Retour");
        retourButton2F.addActionListener(e -> cardLayout.show(mainFrame.getContentPane(), "GestionSalaireOptionsPanel"));
        salaireTablePanel.add(retourButton2F, BorderLayout.SOUTH);

        // Add panels to main frame with CardLayout
        mainFrame.add(mainMenuPanel, "MainMenuPanel");
        mainFrame.add(gestionEmployesOptionsPanel, "GestionEmployesOptionsPanel");
        mainFrame.add(gestionSalaireOptionsPanel, "GestionSalaireOptionsPanel");
        mainFrame.add(employesTablePanel, "EmployesTablePanel");
        mainFrame.add(salaireTablePanel, "SalaireTablePanel");

        cardLayout.show(mainFrame.getContentPane(), "MainMenuPanel");
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    
    public void chargerEmployes() {
        employesTableModel.setRowCount(0);
        List<Employe> employes = gestionemp.getTousLesEmployes();

        if (employes.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Aucun employé trouvé dans la base de données.");
        } else {
            for (Employe emp : employes) {
                employesTableModel.addRow(new Object[]{
                        emp.getId(), emp.getNom(), emp.getPrenom(), emp.getNumTel(), emp.getAdresse()
                });
            }
            cardLayout.show(mainFrame.getContentPane(), "EmployesTablePanel");
        }
    }

    
   
    
    public Employe saisirEmploye() {
        // Create text fields for input
        JTextField nomField = new JTextField(10);
        JTextField prenomField = new JTextField(10);
        JTextField numTelField = new JTextField(10);
        JTextField adresseField = new JTextField(10);

        // Create a panel for the input dialog
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nom:"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom:"));
        panel.add(prenomField);
        panel.add(new JLabel("Numéro de téléphone:"));
        panel.add(numTelField);
        panel.add(new JLabel("Adresse:"));
        panel.add(adresseField);

        // Show the input dialog
        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Ajouter un nouvel employé", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // If the user clicked "OK", proceed with adding the employee
        if (result == JOptionPane.OK_OPTION) {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String numTel = numTelField.getText().trim();
            String adresse = adresseField.getText().trim();

            // Check if any field is empty
            if (nom.isEmpty() || prenom.isEmpty() || numTel.isEmpty() || adresse.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                // Create a new employee object (ensure correct field order)
                Employe newEmploye = new Employe(nom, prenom, adresse,numTel);

                // Call the method to add the employee
                gestionemp.ajouterEmployee(newEmploye);

                // Refresh the employee table to show the new employee
                chargerEmployes();
                return newEmploye;
            }
        } else {
            // If user clicked "Cancel", show a message and return
            JOptionPane.showMessageDialog(mainFrame, "Ajout d'employé annulé.");
        }
        return null;
    }

   
    public void modifierEmployeeUI() {
        String idStr = JOptionPane.showInputDialog(mainFrame, "Saisir l'ID de l'employé à modifier");
        
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                
                Employe employe = gestionemp.chercherEmployee(id);
                if (employe == null) {
                    JOptionPane.showMessageDialog(mainFrame, "Employé non trouvé.");
                    return;
                }

                JTextField nomField = new JTextField(employe.getNom(), 10);
                JTextField prenomField = new JTextField(employe.getPrenom(), 10);
                JTextField numTelField = new JTextField(employe.getNumTel(), 10);
                JTextField adresseField = new JTextField(employe.getAdresse(), 10);

                JPanel panel = new JPanel(new GridLayout(5, 2));
                panel.add(new JLabel("Nom:"));
                panel.add(nomField);
                panel.add(new JLabel("Prénom:"));
                panel.add(prenomField);
                panel.add(new JLabel("Numéro de téléphone:"));
                panel.add(numTelField);
                panel.add(new JLabel("Adresse:"));
                panel.add(adresseField);

                int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Modifier l'employé", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    employe.setNom(nomField.getText());
                    employe.setPrenom(prenomField.getText());
                    employe.setNumTel(numTelField.getText());
                    employe.setAdresse(adresseField.getText());

                    gestionemp.modifierEmployee(employe, id);
                    JOptionPane.showMessageDialog(mainFrame, "Employé modifié avec succès.");

                    // Recharger les employés après modification
                    chargerEmployes();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame, "L'ID doit être un nombre entier.");
            }
        }
    }
   
    public void supprimerEmployeeUI() {
        String idStr = JOptionPane.showInputDialog(mainFrame, "Saisir l'ID de l'employé à supprimer");
        
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                
                Employe employe = gestionemp.chercherEmployee(id);
                if (employe == null) {
                    JOptionPane.showMessageDialog(mainFrame, "Employé non trouvé.");
                    return;
                }

                int confirmation = JOptionPane.showConfirmDialog(mainFrame, 
                    "Êtes-vous sûr de vouloir supprimer l'employé : " + employe.getNom() + " " + employe.getPrenom() + " ?", 
                    "Confirmer la suppression", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    gestionemp.supprimerEmployee(id);
                    JOptionPane.showMessageDialog(mainFrame, "Employé supprimé avec succès.");

                    // Recharger les employés après suppression
                    chargerEmployes();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame, "L'ID doit être un nombre entier.");
            }
        }
    }

    
   

    
   

    
    
    public void chargerFiches() {
        salaireTableModel.setRowCount(0);  // Réinitialiser les lignes de la table avant de la remplir

        // Charger toutes les fiches de salaire à partir du service de gestion des salaires
        List<FicheSalaire> fiches = gestionSalaire.getToutesLesFichesSalaires();

        if (fiches.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Aucune fiche de salaire trouvée.");
        } else {
            // Ajouter chaque fiche de salaire dans la table
            for (FicheSalaire fiche : fiches) {
                salaireTableModel.addRow(new Object[]{
                        fiche.getNumfiche(), 
                        fiche.getDatef(), 
                        fiche.getTauxtt(),
                        fiche.getNbheures(), 
                        fiche.getMontantnet(),
                        fiche.getEmp().getNom()  // Nom de l'employé associé à la fiche
                });
            }

            // Afficher la table des fiches de salaire après le remplissage
            cardLayout.show(mainFrame.getContentPane(), "SalaireTablePanel");
        }
    }
    
    

    
    public void saisirFicheSalaire() {
        JTextField numFicheField = new JTextField(10);
        JTextField dateField = new JTextField(10);
        JTextField tauxField = new JTextField(10);
        JTextField heuresField = new JTextField(10);
        JTextField montantBrutField = new JTextField(10);
        JTextField taxeField = new JTextField(10);
        JTextField empIdField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Num Fiche:"));
        panel.add(numFicheField);
        panel.add(new JLabel("Date (yyyy-mm-dd):"));
        panel.add(dateField);
        panel.add(new JLabel("Taux Horaire:"));
        panel.add(tauxField);
        panel.add(new JLabel("Nb Heures:"));
        panel.add(heuresField);
        panel.add(new JLabel("Montant Brut:"));
        panel.add(montantBrutField);
        panel.add(new JLabel("Taux de Taxe (%):"));
        panel.add(taxeField);
        panel.add(new JLabel("Employé ID:"));
        panel.add(empIdField);

        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Ajouter une nouvelle fiche", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                FicheSalaire fiche = new FicheSalaire();
                fiche.setNumfiche(numFicheField.getText());
                fiche.setDatef(java.sql.Date.valueOf(dateField.getText()));
                fiche.setTauxtt(Double.parseDouble(tauxField.getText()));
                fiche.setNbheures(Integer.parseInt(heuresField.getText()));
                fiche.setMontantbrut(Double.parseDouble(montantBrutField.getText()));
                fiche.setTaxe(Double.parseDouble(taxeField.getText()));

                // Calcul du montant net
                fiche.setMontantnet(fiche.getMontantbrut() - (fiche.getMontantbrut() * fiche.getTaxe() / 100));

                // Gestion de l'employé
                int empId = Integer.parseInt(empIdField.getText());
                Employe emp = gestionemp.chercherEmployee(empId);

                if (emp == null) {
                    emp = new Employe();
                    emp.setId(empId);

                    // Saisie des détails de l'employé
                    JTextField nomField = new JTextField(10);
                    JTextField prenomField = new JTextField(10);
                    JTextField telField = new JTextField(10);
                    JTextField adresseField = new JTextField(10);

                    JPanel empPanel = new JPanel(new GridLayout(4, 2));
                    empPanel.add(new JLabel("Nom:"));
                    empPanel.add(nomField);
                    empPanel.add(new JLabel("Prénom:"));
                    empPanel.add(prenomField);
                    empPanel.add(new JLabel("Téléphone:"));
                    empPanel.add(telField);
                    empPanel.add(new JLabel("Adresse:"));
                    empPanel.add(adresseField);

                    int empResult = JOptionPane.showConfirmDialog(mainFrame, empPanel, "Ajouter un nouvel employé", JOptionPane.OK_CANCEL_OPTION);

                    if (empResult == JOptionPane.OK_OPTION) {
                        emp.setNom(nomField.getText());
                        emp.setPrenom(prenomField.getText());
                        emp.setNumTel(telField.getText());
                        emp.setAdresse(adresseField.getText());
                        gestionemp.ajouterEmployee(emp);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Ajout de fiche annulé. L'employé est requis.");
                        return;
                    }
                }

                fiche.setEmp(emp);
                gestionSalaire.ajouterFicheSalaire(fiche);
                JOptionPane.showMessageDialog(mainFrame, "Fiche ajoutée avec succès !");
                chargerFiches();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, "Erreur lors de l'ajout de la fiche : " + ex.getMessage());
            }
        }
    }


    

    
    public void modifierFicheUI() {
        String numFiche = JOptionPane.showInputDialog(mainFrame, "Saisir le numéro de la fiche à modifier");

        if (numFiche != null && !numFiche.trim().isEmpty()) {
            FicheSalaire fiche = gestionSalaire.chercherFicheSalaire(numFiche);

            if (fiche != null) {
                JTextField dateField = new JTextField(fiche.getDatef().toString(), 20);
                JTextField tauxField = new JTextField(String.valueOf(fiche.getTauxtt()), 20);
                JTextField heuresField = new JTextField(String.valueOf(fiche.getNbheures()), 20);
                JTextField montantBrutField = new JTextField(String.valueOf(fiche.getMontantbrut()), 20);
                JTextField taxeField = new JTextField(String.valueOf(fiche.getTaxe()), 20);
                JTextField empIdField = new JTextField(String.valueOf(fiche.getEmp().getId()), 20);
                empIdField.setEditable(false);

                JPanel panel = new JPanel(new GridBagLayout());
                panel.setBackground(new Color(245, 245, 245));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5); // Padding for a cleaner look

                panel.add(new JLabel("Num Fiche (non modifiable):"), gbc);
                gbc.gridx = 1;
                panel.add(new JLabel(fiche.getNumfiche()), gbc);

                gbc.gridx = 0;
                panel.add(new JLabel("Date (yyyy-mm-dd):"), gbc);
                gbc.gridx = 1;
                panel.add(dateField, gbc);

                gbc.gridx = 0;
                panel.add(new JLabel("Taux Horaire:"), gbc);
                gbc.gridx = 1;
                panel.add(tauxField, gbc);

                gbc.gridx = 0;
                panel.add(new JLabel("Nb Heures:"), gbc);
                gbc.gridx = 1;
                panel.add(heuresField, gbc);

                gbc.gridx = 0;
                panel.add(new JLabel("Montant Brut:"), gbc);
                gbc.gridx = 1;
                panel.add(montantBrutField, gbc);

                gbc.gridx = 0;
                panel.add(new JLabel("Taux de Taxe (%):"), gbc);
                gbc.gridx = 1;
                panel.add(taxeField, gbc);

                gbc.gridx = 0;
                panel.add(new JLabel("Employé ID (non modifiable):"), gbc);
                gbc.gridx = 1;
                panel.add(empIdField, gbc);

                int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Modifier la fiche de salaire", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        fiche.setDatef(java.sql.Date.valueOf(dateField.getText()));
                        fiche.setTauxtt(Double.parseDouble(tauxField.getText()));
                        fiche.setNbheures(Integer.parseInt(heuresField.getText()));
                        fiche.setMontantbrut(Double.parseDouble(montantBrutField.getText()));
                        fiche.setTaxe(Double.parseDouble(taxeField.getText()));
                        fiche.setMontantnet(fiche.getMontantbrut() - (fiche.getMontantbrut() * fiche.getTaxe() / 100));

                        gestionSalaire.modifierFicheSalaire(fiche, numFiche);

                        JOptionPane.showMessageDialog(mainFrame, "Fiche modifiée avec succès !");
                        chargerFiches(); // Reload the list after modification
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Erreur lors de la modification de la fiche : " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Fiche de salaire non trouvée.");
            }
        }
    }


    
    public void supprimerFicheUI() {
        // Demander à l'utilisateur de saisir le numéro de fiche à supprimer
        String numFiche = JOptionPane.showInputDialog("Entrez le numéro de fiche à supprimer:");

        if (numFiche != null && !numFiche.trim().isEmpty()) {
            // Afficher un message de confirmation avant de supprimer
            int confirm = JOptionPane.showConfirmDialog(mainFrame, 
                "Êtes-vous sûr de vouloir supprimer la fiche de salaire numéro " + numFiche + " ?", 
                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                // Supprimer la fiche si l'utilisateur confirme
                gestionSalaire.supprimerFicheSalaire(numFiche);
                chargerFiches();  // Recharger la liste après suppression
                JOptionPane.showMessageDialog(mainFrame, "Fiche supprimée avec succès !");
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Le numéro de fiche est requis.");
        }
    }

    
    public void ajouterFicheSalaire(FicheSalaire fiche) {
        gestionSalaire.ajouterFicheSalaire(fiche);
    }

  
    public void supprimerFicheSalaire(FicheSalaire fiche) {
        gestionSalaire.supprimerFicheSalaire(fiche);
    }

    
    public void supprimerFicheSalaire(String numfiche) {
        gestionSalaire.supprimerFicheSalaire(numfiche);
    }

    
    public FicheSalaire chercherFicheSalaire(String numfiche) {
        return gestionSalaire.chercherFicheSalaire(numfiche);
    }

  
    public FicheSalaire chercherFicheSalaireByName(String nom) {
        return gestionSalaire.chercherFicheSalairebyname(nom);
    }

    
    public void modifierFicheSalaire(FicheSalaire fiche, String numfiche) {
        gestionSalaire.modifierFicheSalaire(fiche, numfiche);
    }

   
    public List<FicheSalaire> getToutesLesFichesSalaires() {
        return gestionSalaire.getToutesLesFichesSalaires();
    }
    
}
