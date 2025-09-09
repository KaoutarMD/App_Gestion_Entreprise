package com.Entreprise.GestionEntreprise.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employes") 
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
    private Long id;

    @Column(name = "nom") 
    private String nom;

    @Column(name = "prenom") 
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "numTel")
    private String numTel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", nullable = false)
    private User user;


    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Employe [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", numTel=" + numTel + "]";
    }
    public Employe(String nom, String prenom, String adresse, String numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
    }
    // Constructeurs
    public Employe(Long id, String nom, String prenom, String adresse, String numTel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
    }

    public Employe(Long id, String nom, String prenom, String adresse, String numTel, User user) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.user = user;
    }

  

    public Employe() {
    }
}
