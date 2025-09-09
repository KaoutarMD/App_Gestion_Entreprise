import models.FicheSalaire;
import services.GestionSalaireV2;

public class test {
	public static void main(String[] args) {
		
		 GestionSalaireV2 gsalaire= new GestionSalaireV2();
		 FicheSalaire f=gsalaire.getToutesLesFichesSalaires().get(0);
		 System.out.println(f.toString());
	}
}
