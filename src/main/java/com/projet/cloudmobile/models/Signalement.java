package com.projet.cloudmobile.models;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "signalement")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})

public class Signalement {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idType", referencedColumnName = "id")
    private TypeSignalement type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "id")
    private Utilisateur utilisateur;
    private Date dateSignalement;
    private String description ;
    private double longitude ;
    private double latitude;
    private String etat;



    public Signalement(Long id, TypeSignalement type, Utilisateur utilisateur, Date dateSignalement, String description, double longitude, double latitude, String etat) {
        this.id = id;
        this.type = type;
        this.utilisateur = utilisateur;
        this.dateSignalement = dateSignalement;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.etat = etat;
    }

    public Signalement(TypeSignalement type, Utilisateur utilisateur, Date dateSignalement, String description, double longitude, double latitude, String etat) {
        this.type = type;
        this.utilisateur = utilisateur;
        this.dateSignalement = dateSignalement;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.etat = etat;
    }

    public Signalement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeSignalement getType() {
        return type;
    }

    public void setType(TypeSignalement type) {
        this.type = type;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getEtat(){return etat;}

    public void setEtat(String etat){this.etat=etat;}


}
