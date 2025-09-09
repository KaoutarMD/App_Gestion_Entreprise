package services;

import java.util.List;

import models.FicheSalaire;

public interface InterfaceGestionSalaire {
	
	 public Boolean ajouterFicheSalaire(FicheSalaire f);

	    public Boolean supprimerFicheSalaire(FicheSalaire f);

	    public Boolean supprimerFicheSalaire(String numfiche);

	    public FicheSalaire chercherFicheSalaire(String numfiche);

	    public FicheSalaire chercherFicheSalairebyname(String nom);

	    public Boolean modifierFicheSalaire(FicheSalaire f, String numfiche);

	    public List<FicheSalaire> getToutesLesFichesSalaires();
	
}
