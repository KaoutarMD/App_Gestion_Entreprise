package composantSalaires;

import java.util.List;
import models.FicheSalaire;

public interface interfaceIHMSalaire {

    public void saisirFicheSalaire();
    public void AfficherFicheSalaire(FicheSalaire fiche);
    public void ajouterFicheSalaire(FicheSalaire fiche);

    public void supprimerFicheSalaire(FicheSalaire fiche);

    public void supprimerFicheSalaire(String numfiche);
    
    public FicheSalaire chercherFicheSalaire(String numfiche);

    public FicheSalaire chercherFicheSalaireByName(String nom);

    public void modifierFicheSalaire(FicheSalaire fiche, String numfiche);

    public List<FicheSalaire> getToutesLesFichesSalaires();
}
