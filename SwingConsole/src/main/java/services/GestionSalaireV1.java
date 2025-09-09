package services;

import java.util.ArrayList;
import java.util.List;

import models.Employe;
import models.FicheSalaire;

public class GestionSalaireV1 implements InterfaceGestionSalaire {

    static ArrayList<FicheSalaire> Fiches = new ArrayList<>();

    @Override
    public Boolean ajouterFicheSalaire(FicheSalaire f) {
        Fiches.add(f);
        return true;
    }

    @Override
    public Boolean supprimerFicheSalaire(FicheSalaire f) {
        return Fiches.remove(f);
    }

    @Override
    public Boolean supprimerFicheSalaire(String numfiche) {
        for (int i = 0; i < Fiches.size(); i++) {
            if (Fiches.get(i).getNumfiche().equals(numfiche)) {
                Fiches.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public FicheSalaire chercherFicheSalaire(String numfiche) {
        for (FicheSalaire fiche : Fiches) {
            if (fiche.getNumfiche().equals(numfiche)) {
                return fiche;
            }
        }
        return null;
    }

    @Override
    public FicheSalaire chercherFicheSalairebyname(String nom) {
        for (FicheSalaire fiche : Fiches) {
            if (fiche.getEmp() != null && fiche.getEmp().getNom().equalsIgnoreCase(nom)) {
                return fiche;
            }
        }
        return null;
    }

    @Override
    public Boolean modifierFicheSalaire(FicheSalaire f, String numfiche) {
        for (int i = 0; i < Fiches.size(); i++) {
            if (Fiches.get(i).getNumfiche().equals(numfiche)) {
                Fiches.set(i, f);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<FicheSalaire> getToutesLesFichesSalaires() {
        return new ArrayList<>(Fiches);
    }
}
