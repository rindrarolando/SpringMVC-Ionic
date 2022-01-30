package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
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

    public static Region getRegionById(int id) throws Exception{
        Region region = null;
        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from region where id='"+id+"'");
            while(res.next()){
                int i = res.getInt("id");
                String designation = res.getString("designation");
                String username = res.getString("username");
                String password = res.getString("password");
                region = new Region(i,designation,username,password);
            }
            return region;
        }catch (Exception e){
            return null;
        }
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

    public Region checkLoginRegion(String username , String password) throws SQLException {
        Region region = null;
        Connection c = null;
        Statement stmt = null;
        try {
            c = Rescue.connectToDatabase();
            stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from region where username='"+username+"' and password=md5('"+password+"')");
            while(res.next()){
                int id= res.getInt("id");
                String designation = res.getString("designation");
                String usernam = res.getString("username");
                String pass = res.getString("password");
                region = new Region(id,designation,usernam,pass);

            }
            return region;
        }catch (Exception e){
            return null;
        }finally {
            if(c!=null) c.close();
            if(stmt!=null) stmt.close();
        }
    }
}
