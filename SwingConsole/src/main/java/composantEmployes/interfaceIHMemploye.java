package composantEmployes;

import java.util.List;

import models.Employe;

public interface interfaceIHMemploye {

	public Employe saisirEmploye() ;
    public void AfficherEmploye(Employe emp);
    public void ajouterEmployee(Employe e);

    public void supprimerEmployee(Employe e);

    public void supprimerEmployee(int matricule);
    
    public Employe chercherEmployee(int matricule);

    public Employe chercherEmployee(String nom);

    public void modifierEmployee(Employe e,int id);

	public List<Employe> getTousLesEmployes();
}
