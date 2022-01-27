package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.models.Administrateur;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

        public Administrateur checkAdmin(String ident , String mdp){
            Administrateur admin = null;
            try {
                Connection c = Rescue.connectToDatabase();
                Statement stmt = c.createStatement();
                ResultSet res = stmt.executeQuery("select * from administrateur where identifiant='"+ident
                        +"' and motdepasse=md5('"+mdp+"')");
                while(res.next()){
                    int id = res.getInt("id");
                    String identifiant = res.getString("identifiant");
                    String password = res.getString("motdepasse");
                    admin = new Administrateur(id,identifiant,password);
                }
                return admin;
            }catch (Exception e){
                return null;
            }
        }

    public static Administrateur getAdminById(int id){
        Administrateur admin = null;
        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from administrateur where id='"+id+"'");
            while(res.next()){
                int i = res.getInt("id");
                String identifiant = res.getString("identifiant");
                String password = res.getString("motdepasse");
                admin = new Administrateur(i,identifiant,password);
            }
            return admin;
        }catch (Exception e){
            return null;
        }
    }
}
