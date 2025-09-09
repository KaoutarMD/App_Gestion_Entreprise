package models;


import java.util.Date;



public class FicheSalaire {
 
	private String numfiche;
    private Date datef;
    private double tauxtt;
    private int nbheures;
    private double montantbrut ;
    private double taxe;
    private double montantnet ;
    
    private Employe emp;

	public String getNumfiche() {
		return numfiche;
	}

	public void setNumfiche(String numfiche) {
		this.numfiche = numfiche;
	}

	public Date getDatef() {
		return datef;
	}

	public void setDatef(Date datef) {
		this.datef = datef;
	}

	public double getTauxtt() {
		return tauxtt;
	}

	public void setTauxtt(double tauxtt) {
		this.tauxtt = tauxtt;
	}

	public int getNbheures() {
		return nbheures;
	}

	public void setNbheures(int nbheures) {
		this.nbheures = nbheures;
	}

	public double getMontantbrut() {
		return montantbrut;
	}

	public void setMontantbrut(double montantbrut) {
		this.montantbrut = montantbrut;
	}

	public double getTaxe() {
		return taxe;
	}

	public void setTaxe(double taxe) {
		this.taxe = taxe;
	}

	public double getMontantnet() {
		return montantnet;
	}

	public void setMontantnet(double montantnet) {
		this.montantnet = montantnet;
	}

	public Employe getEmp() {
		return emp;
	}

	public void setEmp(Employe emp) {
		this.emp = emp;
	}

	public FicheSalaire(String numfiche, Date datef, double tauxtt, int nbheures, double montantbrut, double taxe,
			double montantnet, Employe emp) {
		super();
		this.numfiche = numfiche;
		this.datef = datef;
		this.tauxtt = tauxtt;
		this.nbheures = nbheures;
		this.montantbrut = montantbrut;
		this.taxe = taxe;
		this.montantnet = montantnet;
		this.emp = emp;
	}
    
	
	public FicheSalaire() {
		
	}
    
}

