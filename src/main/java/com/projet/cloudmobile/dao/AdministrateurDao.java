package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.models.Administrateur;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

public class AdministrateurDao {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        @Transactional
        public void initAdministrator1(){
            tx.begin();
            /*int nul = Integer.parseInt(null);
            if(admin.getId()==nul){
                em.persist(admin);
            }else{
                em.merge(admin);
            }*/


            String query = "insert into administrateur(id,identifiant,motdepasse) values (DEFAULT,:identifiant,md5(:motdepasse))";
            Query jpqlQuery = em.createNativeQuery(query)
                    .setParameter("identifiant", "Admin1")
                    .setParameter("motdepasse","admin1");
            em.joinTransaction();
            jpqlQuery.executeUpdate();

            tx.commit();
        }

    @Transactional
    public void initAdministrator2(){
        tx.begin();
        String query = "insert into administrateur(id,identifiant,motdepasse) values (DEFAULT,:identifiant,md5(:motdepasse))";
        Query jpqlQuery = em.createNativeQuery(query)
                .setParameter("identifiant", "Admin2")
                .setParameter("motdepasse","admin2");
        em.joinTransaction();
        jpqlQuery.executeUpdate();

        tx.commit();
    }
}
