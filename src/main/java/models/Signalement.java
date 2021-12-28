package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "signalement")
public class Signalement {
    @Id
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

    public Signalement(Long id, TypeSignalement type, Utilisateur utilisateur, Date dateSignalement, String description) {
        this.id = id;
        this.type = type;
        this.utilisateur = utilisateur;
        this.dateSignalement = dateSignalement;
        this.description = description;
    }

    public Signalement(TypeSignalement type, Utilisateur utilisateur, Date dateSignalement, String description) {
        this.type = type;
        this.utilisateur = utilisateur;
        this.dateSignalement = dateSignalement;
        this.description = description;
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
}
