package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

public class RegionDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Transactional
    public void insert(Region r){
        tx.begin();
        if(r.getId()==0){
            em.persist(r);
        }else{
            em.merge(r);
        }
        tx.commit();
    }

    public List<Region> getAllRegions(){
        return em.createQuery("select r from Region r").getResultList();

    }

    public Region getRegion(int id){
        Region r = em.find(Region.class,id);
        if (r == null) {
            throw new EntityNotFoundException("Id not found : "
                    + id);
        }
        return r;
    }

    @Transactional
    public void remove(int id) {
        Region t = em.find(Region.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }
}
