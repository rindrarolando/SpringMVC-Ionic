package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SignalementRegionDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Transactional
    public void insert(SignalementRegion s){
        tx.begin();
        if(s.getId()==null){
            em.merge(s);
        }else{
            em.persist(s);
        }
        tx.commit();
    }

    public List<SignalementRegion> getAllSignalementRegion(){
        return em.createQuery("select e from SignalementRegion e").getResultList();
    }

    public SignalementRegion getSignalementRegion(Long id){
        SignalementRegion r = em.find(SignalementRegion.class,id);
        if (r == null) {
            throw new EntityNotFoundException("ID not found : "
                    + id);
        }
        return r;
    }

    @Transactional
    public void remove(Long id) {
        SignalementRegion t = em.find(SignalementRegion.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }

    //Statistique par rapport au region
    public Long getStatReg(Long id){
        long sigos = (Long) em.createQuery("select count(e) from SignalementRegion e where e.region.id= :idregion").setParameter("idregion", id).getSingleResult();
        return sigos;
    }
    public Long getStatRegC(Long id){
        String s = "En cours";
        long sigos = (Long) em.createQuery("select count(e) from SignalementRegion e where e.region.id= :idregion and e.signalement.etat= :state").setParameter("idregion", id).setParameter("state", s).getSingleResult();
        return sigos;
    }
    public Long getStatRegN(Long id) {
        String s = "Nouveau";
        long sigos = (Long) em.createQuery("select count(e) from SignalementRegion e where e.region.id= :idregion and e.signalement.etat= :state").setParameter("idregion", id).setParameter("state", s).getSingleResult();
        return sigos;
    }

    public List<Signalement> getSignalementByFilter(Long idRegion , Long idType , String etat , Date debut , Date fin){

        List<Signalement>  sig = null;
        String operatorRegion = "=";
        String operatorType = "=";
        String operatorEtat = "=";

        if (idRegion == -1) {
            operatorRegion = "!=";
        }

        if(idType == -1){
            operatorType = "!=";
        }

        if (Objects.equals(etat, "tout")){
            operatorEtat = "!=";
        }

        sig = em
                .createQuery("select e.signalement from SignalementRegion e join e.signalement on e.id = e.signalement.id where e.region.id "+operatorRegion+" :idRegion and e.signalement.type "+operatorType+" :idType and e.signalement.etat "+operatorEtat+" :etat and e.signalement.dateSignalement >= :debut and e.signalement.dateSignalement <= :fin", Signalement.class)
                .setParameter("idRegion", idRegion)
                .setParameter("idType",idType)
                .setParameter("etat",etat)
                .setParameter("debut",debut)
                .setParameter("fin",fin)
                .getResultList();
        return sig;

    }
}
