package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

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
}
