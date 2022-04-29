package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.models.Administrateur;
import com.projet.cloudmobile.models.Tokenadmin;
import com.projet.cloudmobile.models.Tokenuser;
import com.projet.cloudmobile.models.Utilisateur;

import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDate;
import java.util.Formatter;

public class TokenUserDao {
    public String insertTokenUser(Utilisateur user) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            String token = createToken(user.getId());
            Date creation = Date.valueOf(LocalDate.now());
            Date expiration = Date.valueOf(LocalDate.now().plusDays(3));
            String role = "utilisateur";

            conn = Rescue.connectToDatabase();
            pst = conn.prepareStatement("INSERT INTO tokenuser "
                    + "(id,iduser,token,date_creation,date_expiration,role) "
                    + "VALUES ( DEFAULT, ? , ? , ? , ? , ?) ");
            pst.setLong(1, user.getId());
            pst.setString(2, token);
            pst.setDate(3,creation);
            pst.setDate(4,expiration);
            pst.setString(5,role);
            pst.executeUpdate();
            return token;
        } catch (Exception e) {
            return null;
        }finally{
            if(conn!=null) {
                conn.close();
            }
            if(pst!=null) {
                pst.close();
            }
        }
    }

    public void deleteTokenUser(String token, int id) throws Exception{
        Connection conn = null;
        PreparedStatement pst = null;
        try {

            conn = Rescue.connectToDatabase();
            pst = conn.prepareStatement("DELETE FROM tokenuser WHERE token=? AND iduser=?");
            pst.setString(1, token);
            pst.setInt(2,id);

            pst.executeUpdate();

        } catch (Exception e) {
            throw e;
        }finally{
            if(conn!=null) {
                conn.close();
            }
            if(pst!=null) {
                pst.close();
            }
        }
    }

    public boolean isValidTokenUser(String token) throws Exception{

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet resultat = null;
        String requete = null;
        try{
            conn = Rescue.connectToDatabase();
            requete = "SELECT * FROM tokenuser where token=? and date_expiration>current_date";
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

    public Tokenuser getTokenUser(String token) throws SQLException {
        Tokenuser token_result = null;
        Connection c = null;
        Statement stmt = null;
        try {
            c = Rescue.connectToDatabase();
             stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from tokenuser where token='"+token+"'");
            while(res.next()){
                int id = res.getInt("id");
                int iduser = res.getInt("iduser");
                Utilisateur user = UtilisateurDao.getUserById(iduser);
                String tok = res.getString("token");
                Date cr = res.getDate("date_creation");
                Date exp = res.getDate("date_expiration");
                String role = res.getString("role");
                token_result = new Tokenuser(id,user,tok,cr,exp,role);
            }
            return token_result;
        }catch (Exception e){
            return null;
        }finally{
            if(c!=null) {
                c.close();
            }
            if(stmt!=null) {
                stmt.close();
            }
        }
    }

    public String createToken(Long id) throws Exception{
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
