package composantSalaires;

import java.util.List;
import java.util.Scanner;

import composantEmployes.interfaceIHMemploye;
import models.FicheSalaire;
import models.Employe;
import services.InterfaceGestionSalaire;
import services.GestionEmployesV1;
import services.GestionEmployesV2;
import services.GestionSalaireV1;
import services.GestionSalaireV2;
import services.InterfaceGestionEmployes;

public class IHMSalaireConsole implements interfaceIHMSalaire {

    static Scanner scan = new Scanner(System.in);
    InterfaceGestionSalaire gestionSalaire;

    InterfaceGestionEmployes gemp;

    public IHMSalaireConsole() {
        gestionSalaire = new GestionSalaireV2(); // Instanciation du service
        gemp= new GestionEmployesV2();
    }

    @Override
    public void saisirFicheSalaire() {
        FicheSalaire fiche = new FicheSalaire();
        Employe emp = null; // Ne pas instancier ici

        System.out.println("Entrer le numéro de la fiche :");
        fiche.setNumfiche(scan.next());

        System.out.println("Entrer la date de la fiche (yyyy-mm-dd) :");
        String dateStr = scan.next();
        fiche.setDatef(java.sql.Date.valueOf(dateStr));

        System.out.println("Entrer le taux horaire :");
        fiche.setTauxtt(scan.nextDouble());

        System.out.println("Entrer le nombre d'heures :");
        fiche.setNbheures(scan.nextInt());

        System.out.println("Entrer le montant brut :");
        fiche.setMontantbrut(scan.nextDouble());

        System.out.println("Entrer le taux de taxe :");
        fiche.setTaxe(scan.nextDouble());

        fiche.setMontantnet(fiche.getMontantbrut() - (fiche.getMontantbrut() * fiche.getTaxe() / 100));

        System.out.println("Entrer les informations de l'employé associé :");
        System.out.println("Entrer le matricule:");
        
        int id = scan.nextInt(); // Lecture du matricule
        emp = gemp.chercherEmployee(id); // Chercher l'employé par matricule

        if (emp == null) {
            emp = new Employe(); // Créer un nouvel employé si non trouvé
            emp.setId(id);

            System.out.println("Entrer le nom:");
            emp.setNom(scan.next());

            System.out.println("Entrer le prenom:");
            emp.setPrenom(scan.next());

            System.out.println("Entrer le numero de telephone:");
            emp.setNumTel(scan.next());

            System.out.println("Entrer l'adresse:");
            emp.setAdresse(scan.next());

            gemp.ajouterEmployee(emp); // Ajouter l'employé à la gestion
            System.out.println("Employé ajouté avec succès !");
        }

        fiche.setEmp(emp); // Associer l'employé à la fiche de salaire
        gestionSalaire.ajouterFicheSalaire(fiche); // Ajouter la fiche de salaire
    }


    @Override
    public void AfficherFicheSalaire(FicheSalaire fiche) {
        System.out.println("Détails de la fiche de salaire :");
        System.out.println("Numéro : " + fiche.getNumfiche());
        System.out.println("Date : " + fiche.getDatef());
        System.out.println("Taux horaire : " + fiche.getTauxtt());
        System.out.println("Nombre d'heures : " + fiche.getNbheures());
        System.out.println("Montant brut : " + fiche.getMontantbrut());
        System.out.println("Taxe : " + fiche.getTaxe());
        System.out.println("Montant net : " + fiche.getMontantnet());

        System.out.println("Informations de l'employé :");
        System.out.println("Matricule : " + fiche.getEmp().getId());
        System.out.println("Nom : " + fiche.getEmp().getNom());
        System.out.println("Prénom : " + fiche.getEmp().getPrenom());
        System.out.println("Adresse : " + fiche.getEmp().getAdresse());
        System.out.println("Téléphone : " + fiche.getEmp().getNumTel());
    }

    @Override
    public void ajouterFicheSalaire(FicheSalaire fiche) {
        gestionSalaire.ajouterFicheSalaire(fiche);
        System.out.println("Fiche de salaire ajoutée avec succès !");
    }

    @Override
    public void supprimerFicheSalaire(FicheSalaire fiche) {
        gestionSalaire.supprimerFicheSalaire(fiche);
        System.out.println("Fiche de salaire supprimée avec succès !");
    }

    @Override
    public void supprimerFicheSalaire(String numfiche) {
        gestionSalaire.supprimerFicheSalaire(numfiche);
        System.out.println("Fiche de salaire avec le numéro " + numfiche + " supprimée avec succès !");
    }

    @Override
    public FicheSalaire chercherFicheSalaire(String numfiche) {
        FicheSalaire fiche = gestionSalaire.chercherFicheSalaire(numfiche);
        if (fiche != null) {
            AfficherFicheSalaire(fiche);
        } else {
            System.out.println("Aucune fiche trouvée avec ce numéro.");
        }
        return fiche;
    }

    @Override
    public FicheSalaire chercherFicheSalaireByName(String nom) {
        FicheSalaire fiche = gestionSalaire.chercherFicheSalairebyname(nom);
        if (fiche != null) {
            AfficherFicheSalaire(fiche);
        } else {
            System.out.println("Aucune fiche trouvée pour cet employé.");
        }
        return fiche;
    }

    @Override
    public void modifierFicheSalaire(FicheSalaire fiche, String numfiche) {
        System.out.println("Modification de la fiche de salaire :");
        System.out.println("Entrer les nouvelles informations :");

        System.out.println("Nouveau taux horaire :");
        fiche.setTauxtt(scan.nextDouble());

        System.out.println("Nouveau nombre d'heures :");
        fiche.setNbheures(scan.nextInt());

        System.out.println("Nouveau montant brut :");
        fiche.setMontantbrut(scan.nextDouble());

        System.out.println("Nouveau taux de taxe :");
        fiche.setTaxe(scan.nextDouble());

        fiche.setMontantnet(fiche.getMontantbrut() - (fiche.getMontantbrut() * fiche.getTaxe()/100));

        gestionSalaire.modifierFicheSalaire(fiche, numfiche);
        System.out.println("Fiche de salaire modifiée avec succès !");
    }

    @Override
    public List<FicheSalaire> getToutesLesFichesSalaires() {
        List<FicheSalaire> fiches = gestionSalaire.getToutesLesFichesSalaires();
        System.out.println("Liste de toutes les fiches de salaire :");
        for (FicheSalaire fiche : fiches) {
            AfficherFicheSalaire(fiche);
        }
        return fiches;
    }
}
