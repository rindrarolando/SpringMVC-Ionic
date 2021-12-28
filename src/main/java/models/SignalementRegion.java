package models;

import javax.persistence.*;

@Entity
@Table(name = "signalementregion")
public class SignalementRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSignalement", referencedColumnName = "id")
    private Signalement signalement;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRegion" , referencedColumnName = "id")
    private Region region ;
    private boolean status ;

    public SignalementRegion(Long id, Signalement signalement, Region region, boolean status) {
        this.id = id;
        this.signalement = signalement;
        this.region = region;
        this.status = status;
    }

    public SignalementRegion(Signalement signalement, Region region, boolean status) {
        this.signalement = signalement;
        this.region = region;
        this.status = status;
    }

    public SignalementRegion(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Signalement getSignalement() {
        return signalement;
    }

    public void setSignalement(Signalement signalement) {
        this.signalement = signalement;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
