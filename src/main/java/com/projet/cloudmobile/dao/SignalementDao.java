package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

public class SignalementDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Transactional
    public void insert(Signalement s){
        tx.begin();
        em.merge(s);
        tx.commit();
    }

    public List<Signalement> getAllSignalement(){
        return em.createQuery("select c from Signalement c").getResultList();
    }

    @Transactional
    public void remove(Long id) {
        Signalement t = em.find(Signalement.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }

    public Signalement getSignalement(Long id){
        Signalement r = em.find(Signalement.class,id);
        if (r == null) {
            throw new EntityNotFoundException("Id not found :"
                    + id);
        }
        return r;
    }
}
