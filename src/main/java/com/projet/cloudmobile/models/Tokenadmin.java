package com.projet.cloudmobile.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Tokenadmin {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idadmin", referencedColumnName = "id")
    private Administrateur admin;
    private String token;
    private Date date_creation;
    private Date date_expiration;
    private String role;

    public Tokenadmin() {
    }

    public Tokenadmin(int id, Administrateur admin, String token, Date date_creation, Date date_expiration, String role) {
        this.id = id;
        this.admin = admin;
        this.token = token;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
        this.role = role;
    }

    public Tokenadmin(Administrateur admin, String token, Date date_creation, Date date_expiration, String role) {
        this.admin = admin;
        this.token = token;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Administrateur getAdmin() {
        return admin;
    }

    public void setAdmin(Administrateur admin) {
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
