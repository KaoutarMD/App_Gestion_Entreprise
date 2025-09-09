package IHM;

import java.util.Scanner;

import composantEmployes.IHMEmployesConsole;
import composantSalaires.IHMSalaireConsole;
import models.Employe;
import models.FicheSalaire;

public class IHMConsole {
    public IHMConsole() {
        IHMEmployesConsole ihmemp = new IHMEmployesConsole();
        IHMSalaireConsole ihmSalaire = new IHMSalaireConsole();

        Scanner scan = new Scanner(System.in);
        String input = "";

        // Répéter jusqu'à ce que l'utilisateur saisisse "exit"
        while (!input.equalsIgnoreCase("exit")) {
            System.out.println("\nMenu:");
            System.out.println("\n1-Gestion Employes ");
            System.out.println("\n2-Gestion Salaire ");
            System.out.println("\nEntrez un nombre (1, 2) ou tapez 'exit' pour quitter :");

            input = scan.nextLine().trim();

            // Vérifier si l'utilisateur a entré "exit"
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Sortie du programme.");
                break;
            }

            try {
                int n = Integer.parseInt(input);

                if (n == 1) {
                    System.out.println("\n1-Gestion Employes ");
                    System.out.println("\n1-1-Ajouter Employe ");
                    System.out.println("\n1-2-Supprimer Employe ");
                    System.out.println("\n1-3-Chercher Employe ");
                    System.out.println("\n1-4-Modifier Employe ");
                    System.out.println("\nEntrez un nombre (1, 2, 3, 4) :");

                    int n2 = scan.nextInt();
                    scan.nextLine(); // Consomme la fin de ligne après l'entrée

                    switch (n2) {
                        case 1:
                            ihmemp.saisirEmploye();
                            break;
                        case 2:
                            System.out.println("\nEntrez le matricule:");
                            int matricule = scan.nextInt();
                            scan.nextLine();
                            Employe e2 = ihmemp.chercherEmployee(matricule);
                            ihmemp.supprimerEmployee(e2);
                            break;
                        case 3:
                            System.out.println("\nEntrez le matricule:");
                            int matriculeCherche = scan.nextInt();
                            scan.nextLine();
                            Employe e3 = ihmemp.chercherEmployee(matriculeCherche);
                            
                            break;
                        case 4:
                            System.out.println("\nEntrez le matricule:");
                            int matriculeModif = scan.nextInt();
                            scan.nextLine();
                            Employe e = new Employe();
                            ihmemp.modifierEmployee(e, matriculeModif);
                            break;
                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }

                } else if (n == 2) {
                    System.out.println("\n2-Gestion Salaire ");
                    System.out.println("\n1-1-Ajouter Fiche ");
                    System.out.println("\n1-2-Supprimer Fiche ");
                    System.out.println("\n1-3-Chercher Fiche ");
                    System.out.println("\n1-4-Modifier Fiche ");
                    System.out.println("\nEntrez un nombre (1, 2, 3, 4) :");

                    int nf = scan.nextInt();
                    scan.nextLine(); // Consomme la fin de ligne après l'entrée

                    switch (nf) {
                        case 1:
                            ihmSalaire.saisirFicheSalaire();
                            break;
                        case 2:
                            System.out.println("\nEntrez le numFiche:");
                            String numFicheSupprimer = scan.nextLine();
                            FicheSalaire f2 = ihmSalaire.chercherFicheSalaire(numFicheSupprimer);
                            ihmSalaire.supprimerFicheSalaire(f2);
                            break;
                        case 3:
                            System.out.println("\nEntrez le numFiche:");
                            String numFicheChercher = scan.nextLine();
                            FicheSalaire f3 = ihmSalaire.chercherFicheSalaire(numFicheChercher);
                            break;
                        case 4:
                            System.out.println("\nEntrez le numFiche:");
                            String numFicheModif = scan.nextLine();
                            FicheSalaire fModif = new FicheSalaire();
                            ihmSalaire.modifierFicheSalaire(fModif, numFicheModif);
                            break;
                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }
                } else {
                    System.out.println("Choix invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide ou 'exit' pour quitter.");
            }
        }

        scan.close();
    }
}
