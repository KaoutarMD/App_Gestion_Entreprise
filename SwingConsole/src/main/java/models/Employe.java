package models;



public class Employe {
   
	private int Id;
    private String nom;
    private String prenom;
    private String adresse ;
    private String numTel;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	@Override
	public String toString() {
		return "Employe [Id=" + Id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", numTel="
				+ numTel + "]";
	}
	public Employe(int id, String nom, String prenom, String adresse, String numTel) {
		super();
		Id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
	}
	
	public Employe(String nom, String prenom, String adresse, String numTel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
	}

	public Employe() {
	
	}
    
}
