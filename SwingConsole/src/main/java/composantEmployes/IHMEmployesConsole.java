package composantEmployes;

import java.util.List;
import java.util.Scanner;

import models.Employe;
import services.InterfaceGestionEmployes;
import services.GestionEmployesV2;

public class IHMEmployesConsole implements interfaceIHMemploye {
	
	static Scanner scan = new Scanner(System.in);
	InterfaceGestionEmployes gestionemp;

	public IHMEmployesConsole() {
		gestionemp = new GestionEmployesV2(); // Instanciation du service
	}

	@Override
	public Employe saisirEmploye() {
		Employe emp = new Employe();

		System.out.println("Entrer le matricule:");
		emp.setId(scan.nextInt());
		
		if(gestionemp.chercherEmployee(emp.getId())==null) {
		
		System.out.println("Entrer le nom:");
		emp.setNom(scan.next());

		System.out.println("Entrer le prenom:");
		emp.setPrenom(scan.next());

		System.out.println("Entrer le numero de telephone:");
		emp.setNumTel(scan.next());

		System.out.println("Entrer l'adresse:");
		emp.setAdresse(scan.next());

		gestionemp.ajouterEmployee(emp); // Ajouter l'employé via le service
		
		}
		return emp;
	}

	@Override
	public void AfficherEmploye(Employe emp) {
		System.out.println("Les informations de l'employé sont :");
		System.out.println("Matricule: " + emp.getId());
		System.out.println("Nom: " + emp.getNom());
		System.out.println("Prénom: " + emp.getPrenom());
		System.out.println("Numéro de téléphone: " + emp.getNumTel());
		System.out.println("Adresse: " + emp.getAdresse());
	}

	@Override
	public void ajouterEmployee(Employe e) {
		gestionemp.ajouterEmployee(e);
		System.out.println("Employé ajouté avec succès !");
	}

	@Override
	public void supprimerEmployee(Employe e) {
		gestionemp.supprimerEmployee(e);
		
	}

	@Override
	public void supprimerEmployee(int matricule) {
		Employe emp = gestionemp.chercherEmployee(matricule);
		if (emp != null) {
			gestionemp.supprimerEmployee(emp);
		} else {
			System.out.println("Aucun employé trouvé avec ce matricule.");
		}
	}

	@Override
	public Employe chercherEmployee(int matricule) {
		Employe emp = gestionemp.chercherEmployee(matricule);
		if (emp != null) {
			AfficherEmploye(emp); // Affichage de l'employé trouvé
		} else {
			System.out.println("Aucun employé trouvé avec ce matricule.");
		}
		return emp;
	}

	@Override
	public Employe chercherEmployee(String nom) {
		Employe emp = gestionemp.chercherEmployee(nom);
		if (emp != null) {
			AfficherEmploye(emp); // Affichage de l'employé trouvé
		} else {
			System.out.println("Aucun employé trouvé avec ce nom.");
		}
		return emp;
	}

	@Override
	public void modifierEmployee(Employe e, int id) {
		Employe emp = gestionemp.chercherEmployee(id);
		if (emp != null) {
			System.out.println("Modifications de l'employé " + id);

			System.out.println("Nouveau nom (actuel: " + emp.getNom() + "):");
			emp.setNom(scan.next());

			System.out.println("Nouveau prénom (actuel: " + emp.getPrenom() + "):");
			emp.setPrenom(scan.next());

			System.out.println("Nouveau numéro de téléphone (actuel: " + emp.getNumTel() + "):");
			emp.setNumTel(scan.next());

			System.out.println("Nouvelle adresse (actuel: " + emp.getAdresse() + "):");
			emp.setAdresse(scan.next());

			gestionemp.modifierEmployee(emp, id); // Mise à jour des données
		
		} else {
			System.out.println("Aucun employé trouvé avec ce matricule.");
		}
	}

	@Override
	public List<Employe> getTousLesEmployes() {
		List<Employe> employes = gestionemp.getTousLesEmployes();
		if (employes != null && !employes.isEmpty()) {
			System.out.println("Liste des employés :");
			for (Employe emp : employes) {
				AfficherEmploye(emp); // Affichage des informations de chaque employé
			}
		} else {
			System.out.println("Aucun employé trouvé.");
		}
		return employes;
	}
}
