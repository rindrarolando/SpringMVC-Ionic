package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

public class UtilisateurDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Transactional
    public void insert(Utilisateur u){
        tx.begin();
        if(u.getId()==0){
            em.persist(u);
        }else{
            em.merge(u);
        }
        tx.commit();
    }

    public List<Utilisateur> getAllUtilisateur(){
        return em.createQuery("select u from Utilisateur u").getResultList();
    }

    public Utilisateur getUtilisateur(Long id){
        Utilisateur r = em.find(Utilisateur.class,id);
        if (r == null) {
            throw new EntityNotFoundException("Id not found :"
                    + id);
        }
        return r;
    }

    @Transactional
    public void remove(Long id) {
        Utilisateur t = em.find(Utilisateur.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }
}
