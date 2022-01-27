package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.models.Administrateur;
import com.projet.cloudmobile.models.Tokenadmin;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDate;
import java.util.Formatter;

public class TokenDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public String insertTokenAdmin(Administrateur admin){
        Connection conn = null;
        try {
            String token = createToken(admin.getId());
            Date creation = Date.valueOf(LocalDate.now());
            Date expiration = Date.valueOf(LocalDate.now().plusDays(3));
            String role = "administrateur";

            conn = Rescue.connectToDatabase();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO tokenadmin "
                    + "(id,idadmin,token,date_creation,date_expiration,role) "
                    + "VALUES ( DEFAULT, ? , ? , ? , ? , ?) ");
            pst.setInt(1, admin.getId());
            pst.setString(2, token);
            pst.setDate(3,creation);
            pst.setDate(4,expiration);
            pst.setString(5,role);
            pst.executeUpdate();
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteTokenAdmin(String token, int id) throws Exception{
        Connection conn = null;
        try {

            conn = Rescue.connectToDatabase();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM tokenadmin WHERE token=? AND idadmin=?");
            pst.setString(1, token);
            pst.setInt(2,id);

            pst.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isValidTokenAdmin(String token) throws Exception{
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet resultat = null;
        String requete = null;
        try{
            conn = Rescue.connectToDatabase();
            requete = "SELECT * FROM tokenadmin where token=? and date_expiration<current_date";
            pst = conn.prepareStatement(requete);
            pst.setString(1, token);
            resultat = pst.executeQuery();

            if(resultat.next()){
                return true;
            }else {
                return false;
            }
        }catch(Exception ex) {
            return false;
        }finally{
            if(conn!=null) {
                conn.close();
            }
            if(pst!=null) {
                pst.close();
            }
        }
    }

    public boolean isAdminToken(String token) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet resultat = null;
        String requete = null;
        try{
            conn = Rescue.connectToDatabase();
            requete = "SELECT * FROM tokenadmin where token=? and role=?";
            pst = conn.prepareStatement(requete);
            pst.setString(1, token);
            pst.setString(2,"administrateur");
            resultat = pst.executeQuery();

            if(resultat.next()){
                return true;
            }else {
                return false;
            }
        }catch(Exception ex) {
            return false;
        }finally{
            if(conn!=null) {
                conn.close();
            }
            if(pst!=null) {
                pst.close();
            }
        }
    }

    public Tokenadmin getTokenAdmin(String token){
        Tokenadmin token_result = null;
        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from tokenadmin where token='"+token+"'");
            while(res.next()){
                int id = res.getInt("id");
                int idadmin = res.getInt("idadmin");
                Administrateur admin = AdministrateurDao.getAdminById(idadmin);
                String tok = res.getString("token");
                Date cr = res.getDate("date_creation");
                Date exp = res.getDate("date_expiration");
                String role = res.getString("role");
                token_result = new Tokenadmin(id,admin,tok,cr,exp,role);
            }
            return token_result;
        }catch (Exception e){
            return null;
        }
    }

    public String createToken(int id) throws Exception{
        String token = null;
        Date timest = Date.valueOf(LocalDate.now());
        String timestamp = String.valueOf(timest);
        String peps = "this_is_secret";
        token = cript(timestamp+peps+id);
        return token;
    }

    public String cript(String st) throws Exception {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(st.getBytes("UTF-8"));
        return byteToHex(crypt.digest());
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
