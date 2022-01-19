package com.projet.cloudmobile.models;

import org.hibernate.annotations.Entity;
import javax.persistence.*;


@Entity
@Table(name = "administrateur")
public class Administrateur {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String identifiant;
    private String motDePasse;


    public Administrateur(int id, String identifiant, String motDePasse) {
        this.id = id;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public Administrateur(String identifiant, String motDePasse) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public Administrateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
