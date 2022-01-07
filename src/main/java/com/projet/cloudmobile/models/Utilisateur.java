package com.projet.cloudmobile.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date dtn;



    public Utilisateur(Long id, String username, String password, String email, Date dtn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dtn = dtn;
    }

    public Utilisateur(String username, String password, String email, Date dtn) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dtn = dtn;
    }

    public Utilisateur(Long id){
        this.id=id;
    }

    public Utilisateur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }


}
