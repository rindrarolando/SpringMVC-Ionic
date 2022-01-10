package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.SignalementRegion;
import com.projet.cloudmobile.models.TypeSignalement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

public class TypeSignalementDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Transactional
    public void insert(TypeSignalement t){
        tx.begin();
        if(t.getId()==0){
            em.persist(t);
        }else{
            em.merge(t);
        }
        tx.commit();
    }

    public void delete(TypeSignalement t){
        tx.begin();
        em.remove(t);
        tx.commit();
    }

    public List<TypeSignalement> getAllTypeSignalement(){
        return em.createQuery("select t from TypeSignalement t").getResultList();
    }

    public Signalement getSignalement(int id){
        Signalement r = em.find(Signalement.class,id);
        if (r == null) {
            throw new EntityNotFoundException("ID not found : "
                    + id);
        }
        return r;
    }

    @Transactional
    public void remove(int id) {
        TypeSignalement t = em.find(TypeSignalement.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }
}
