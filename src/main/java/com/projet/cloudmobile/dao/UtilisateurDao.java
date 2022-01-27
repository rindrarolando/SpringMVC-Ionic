package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Pattern;

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

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean hasDiacritics(String s) {
        // Decompose any á into a and combining-'.
        String s2 = Normalizer.normalize(s, Normalizer.Form.NFD);
        return s2.matches("(?s).*\\p{InCombiningDiacriticalMarks}.*");
        //return !s2.equals(s);
    }

    @Transactional
    public void insertWithMd5(String username, String password, String email){
        tx.begin();
        long millis = System.currentTimeMillis();
        Date dtn = new java.sql.Date(millis);
        String query = "insert into utilisateur(id,dtn,email,password,username) values (DEFAULT,:dtn,:email,md5(:password),:username)";
        Query jpqlQuery = em.createNativeQuery(query)
                .setParameter("dtn",dtn)
                .setParameter("email",email)
                .setParameter("password",password)
                .setParameter("username",username);
        em.joinTransaction();
        jpqlQuery.executeUpdate();

        tx.commit();
    }

    public static boolean checkInscription(String username, String password, String email) {
        if(isValid(email) == true && hasDiacritics(password) == false && password.length() >= 8){
            UtilisateurDao u = new UtilisateurDao();
            u.insertWithMd5(username,password,email);
            return true;
        }
        else{
            return false;
        }
    }

    public Utilisateur checkLogin(String email, String password){
        Utilisateur u = null;
        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from utilisateur where email='"+email+"' and password=md5('"+password+"')");
            while(res.next()){
                long id = res.getLong("id");
                String emails = res.getString("email");
                String passwords = res.getString("password");
                String username = res.getString("username");
                String date = res.getString("dtn");
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);

                u = new Utilisateur(id,username,passwords,emails,d);
            }
            return u;
        }catch (Exception e){
            return null;
        }
    }

    @Transactional
    public void remove(Long id) {
        Utilisateur t = em.find(Utilisateur.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }

    public static Utilisateur getUserById(int id){
        Utilisateur user = null;
        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from utilisateur where id='"+id+"'");
            while(res.next()){
                int i = res.getInt("id");
                String username = res.getString("username");
                String password = res.getString("password");
                String mail = res.getString("email");
                Date date = res.getDate("dtn");
                user = new Utilisateur((long) i,username,password,mail,date);
            }
            return user;
        }catch (Exception e){
            return null;
        }
    }

    public String createToken(int id) throws Exception{
        String token = null;
        java.sql.Date timest = java.sql.Date.valueOf(LocalDate.now());
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
