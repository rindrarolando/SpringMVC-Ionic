package com.projet.cloudmobile.models;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String designation ;
    private String username ;
    private String password ;

    public Region(int idRegion, String designation, String username, String password) {
        this.id = idRegion;
        this.designation = designation;
        this.username = username;
        this.password = password;
    }

    public Region(String designation, String username, String password) {
        this.designation = designation;
        this.username = username;
        this.password = password;
    }

    public Region() {
    }

    public int getId() {
        return id;
    }

    public void setId(int idRegion) {
        this.id = idRegion;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
}
