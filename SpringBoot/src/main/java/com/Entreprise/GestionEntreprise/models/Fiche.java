package com.Entreprise.GestionEntreprise.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "fiches") 
public class Fiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "numfiche") 
    private int numfiche;

    @Temporal(TemporalType.DATE)
    @Column(name = "datef")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Format attendu
    private Date datef;


    @Column(name = "tauxtt")
    private double tauxtt;

    @Column(name = "nbheures")
    private int nbheures;

    @Column(name = "montantbrut")
    private double montantbrut;

    @Column(name = "taxe")
    private double taxe;

    @Column(name = "montantnet")
    private double montantnet;

    @ManyToOne 
    @JoinColumn(name = "empid")
    private Employe emp;

    // Getters et Setters
    public int getNumfiche() {
        return numfiche;
    }

    public void setNumfiche(int numfiche) {
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

 
    public Fiche(int numfiche, Date datef, double tauxtt, int nbheures, double montantbrut, double taxe,
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

   
    public Fiche() {
    }
}
