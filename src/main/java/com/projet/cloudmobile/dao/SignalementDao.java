package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Signal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

<<<<<<< Updated upstream
    //Statistique par rapport au type de problemes
    public long getStatEtat1(){
       long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=1").getSingleResult();
        return sig;
    }

    public long getStatEtat2(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=2").getSingleResult();
        return sig;
    }

    public long getStatEtat3(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=3").getSingleResult();
        return sig;
    }

    //Statistique par rapport au problemes cas Nouveau
    public long getStatEtat1N(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=1 and c.etat='Nouveau'").getSingleResult();
        return sig;
    }

    public long getStatEtat2N(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=2 and c.etat='Nouveau'").getSingleResult();
        return sig;
    }

    public long getStatEtat3N(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=3 and c.etat='Nouveau'").getSingleResult();
        return sig;
    }

    //Statistique par rapport au problemes cas En cours
    public long getStatEtat1E(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=1 and c.etat='En cours'").getSingleResult();
        return sig;
    }

    public long getStatEtat2E(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=2 and c.etat='En cours'").getSingleResult();
        return sig;
    }

    public long getStatEtat3E(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=3 and c.etat='En cours'").getSingleResult();
        return sig;
    }

    //Statistique par rapport au problemes cas Termine
    public long getStatEtat1T(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=1 and c.etat='Terminé'").getSingleResult();
        return sig;
    }

    public long getStatEtat2T(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=2 and c.etat='Terminé'").getSingleResult();
        return sig;
    }

    public long getStatEtat3T(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=3 and c.etat='Terminé'").getSingleResult();
        return sig;
    }

    //Statistique par rapport au signalement utilisateur reporter
    public long getStatUtil(){
        long sig = (Long) em.createQuery("select count(c) from Signalement c where c.type.id=1 and c.etat='Terminé'").getSingleResult();
        return sig;
    }







    public List<Signalement> getNewSignalement(){
        return em.createQuery("select c from Signalement c where c.etat='Nouveau'").getResultList();
    }
=======
    public List<Signalement> getSignalementByUtil(String name){
        String names = name;
        List<Signalement>  sig = null;
        sig = em
                .createQuery("select c from Signalement c where c.utilisateur.username = :names", Signalement.class)
                .setParameter("names", names)
                .getResultList();
        return sig;
    }


    public List<Signalement> getStatEtat(){
        List<Signalement> sigo = null;
        sigo = em
                .createQuery("select c.type.designation,count(*) as total from Signalement c group by c.type.designation", Signalement.class)
                .getResultList();
        return sigo;
    }

>>>>>>> Stashed changes

    @Transactional
    public void remove(Long id) {
        Signalement t = em.find(Signalement.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }

    @Transactional
    public Signalement getSignalement(Long id){
        Signalement r =  em.find(Signalement.class,id);
        if (r == null) {
            throw new EntityNotFoundException("Id not found :"
                    + id);
        }
        return r;
    }

}
