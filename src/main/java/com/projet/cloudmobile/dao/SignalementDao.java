package com.projet.cloudmobile.dao;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Signal;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
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
        return em.createQuery("select c from signalement c where c.etat='Nouveau'").getResultList();
    }

    public List<Signalement> getSignalementByRegion(int id){
        return em.createQuery("select c from signalementRegion where c.idRegion="+id).getResultList();
    }

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

    @Transactional
    public Signalement add(Long id, Long type, Long idutilisateur, Date dateSignalement, String description, double longitude, double latitude, String etat){
        Signalement r =  em.find(Signalement.class,id);
        if (r == null) {
            throw new EntityNotFoundException("Id not found :"
                    + id);
        }
        return r;
    }

    //Resultat = Diana : 70 problemes , Boeny : 30 problemes , sns ..
    public ArrayList<HashMap<String, Object>> getStatistiqueRegion(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select region.designation as region, count(*) as total from signalement join signalementregion on signalementregion.idsignalement = signalement.id join region on region.id = signalementregion.idregion group by region.designation");
            while(res.next()){

                String region = res.getString("region");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("region",region);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }

    //Resultat = accident 1 : 10 , accident 2 : 20 , accident 3 : 40
    public ArrayList<HashMap<String, Object>> getStatistiqueType(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select typesignalement.designation as type, count(*) as total from signalement join typesignalement on typesignalement.id = signalement.idtype group by typesignalement.designation");
            while(res.next()){

                String type = res.getString("type");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("type",type);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }

    //Resultat =
    // nouveau : accident 1 = 30 , accident 2 = 10 , sns
    //en cours : accident 1 = 30 , accident 2 = 10 , sns
    //termine : accident 1 = 30 , accident 2 = 10 , sns
    //En cours
    public ArrayList<HashMap<String, Object>> getStatistiqueTypeCas1(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select typesignalement.designation as type, count(*) as total from signalement join typesignalement on typesignalement.id = signalement.idtype where signalement.etat = 'En cours' group by typesignalement.designation");
            while(res.next()){

                String type = res.getString("type");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("type",type);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }

    //Nouveau
    public ArrayList<HashMap<String, Object>> getStatistiqueTypeCas2(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select typesignalement.designation as type, count(*) as total from signalement join typesignalement on typesignalement.id = signalement.idtype where signalement.etat = 'Nouveau' group by typesignalement.designation");
            while(res.next()){

                String type = res.getString("type");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("type",type);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }

    //termine
    public ArrayList<HashMap<String, Object>> getStatistiqueTypeCas3(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select typesignalement.designation as type, count(*) as total from signalement join typesignalement on typesignalement.id = signalement.idtype where signalement.etat = 'terminé' group by typesignalement.designation");
            while(res.next()){

                String type = res.getString("type");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("type",type);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }

    //Nouveau
    public ArrayList<HashMap<String, Object>> getStatistiqueRegion2(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select region.designation as region, count(*) as total from signalement join signalementregion on signalementregion.idsignalement = signalement.id join region on region.id = signalementregion.idregion where signalement.etat = 'Nouveau' group by region.designation");
            while(res.next()){

                String region = res.getString("region");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("region",region);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }
    //En cours
    public ArrayList<HashMap<String, Object>> getStatistiqueRegion1(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select region.designation as region, count(*) as total from signalement join signalementregion on signalementregion.idsignalement = signalement.id join region on region.id = signalementregion.idregion where signalement.etat = 'En cours' group by region.designation");
            while(res.next()){

                String region = res.getString("region");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("region",region);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }
    //Termine
    public ArrayList<HashMap<String, Object>> getStatistiqueRegion3(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select region.designation as region, count(*) as total from signalement join signalementregion on signalementregion.idsignalement = signalement.id join region on region.id = signalementregion.idregion where signalement.etat = 'terminé' group by region.designation");
            while(res.next()){

                String region = res.getString("region");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("region",region);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }

    public ArrayList<HashMap<String, Object>> getStatistiqueUtil(){
        ArrayList<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();

        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select utilisateur.username as name, count(*) as total from signalement join utilisateur on utilisateur.id = signalement.idutilisateur group by utilisateur.username order by total asc limit 10");
            while(res.next()){

                String name = res.getString("name");
                String total = res.getString("total");

                HashMap<String, Object> stat = new HashMap<String, Object>();
                stat.put("name",name);
                stat.put("total",total);

                array.add(stat);

            }
            return array;
        }catch (Exception e){
            return null;
        }
    }

}
