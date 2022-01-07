package com.projet.cloudmobile.models;

import javax.persistence.*;

@Entity
@Table(name = "typesignalement")
public class TypeSignalement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id ;

    private String designation ;

    public TypeSignalement(int id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public TypeSignalement(String designation) {
        this.designation = designation;
    }

    public TypeSignalement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}
