package services;

import java.util.ArrayList;
import java.util.List;

import models.Employe;

public class GestionEmployesV1 implements InterfaceGestionEmployes {
	
	    static ArrayList<Employe> Employes=new ArrayList<Employe>();
        
	    public GestionEmployesV1() {
		
	    }

		@Override
		public void ajouterEmployee(Employe e) {
			Employes.add(e);	
		}

		@Override
		public void supprimerEmployee(Employe e) {
			Employes.remove(e);
			
		}

		@Override
		public void supprimerEmployee(int matricule) {
			for(int i=0;i<Employes.size();i++) {
				if(Employes.get(i).getId()==matricule) {
					Employes.remove(i);
				}
			}
			
		}

		@Override
		public Employe chercherEmployee(int matricule) {
			Employe emp=new Employe();
			for(int i=0;i<Employes.size();i++) {
				if(Employes.get(i).getId()==matricule) {
					emp=Employes.get(i);
				}
			}
			return emp;
		}

		@Override
		public Employe chercherEmployee(String nom) {
			Employe emp=new Employe();
			for(int i=0;i<Employes.size();i++) {
				if(Employes.get(i).getNom()==nom) {
					emp=Employes.get(i);
				}
			}
			return emp;
			
		}

		@Override
		public void modifierEmployee(Employe e, int id) {
			
			for(int i=0;i<Employes.size();i++) {
				if(Employes.get(i).getId()==id) {
					Employes.get(i).setNom(e.getNom());
					Employes.get(i).setPrenom(e.getPrenom());
					Employes.get(i).setAdresse(e.getAdresse());
					Employes.get(i).setNumTel(e.getNumTel());
					
				}
			}
			
		
		}

		@Override
		public List<Employe> getTousLesEmployes() {
			// TODO Auto-generated method stub
			return null;
		}

}
