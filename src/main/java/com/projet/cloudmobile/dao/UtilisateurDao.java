package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.models.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.security.MessageDigest;
import java.sql.*;
import java.text.Normalizer;
import java.text.ParseException;
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
    public void insertWithMd5(String username, String password, String email,String date) throws ParseException {
        tx.begin();
        Date date1 = java.sql.Date.valueOf(date);
        String query = "insert into utilisateur(id,dtn,email,password,username) values (DEFAULT,:dtn,:email,md5(:password),:username)";
        Query jpqlQuery = em.createNativeQuery(query)
                .setParameter("dtn",date1)
                .setParameter("email",email)
                .setParameter("password",password)
                .setParameter("username",username);
        em.joinTransaction();
        jpqlQuery.executeUpdate();

        tx.commit();
    }

    public static boolean check(String username, String password, String email) {
        if(isValid(email) == true && hasDiacritics(password) == false && password.length() >= 8){

            return true;
        }
        else{
            return false;
        }
    }

    public void inscription(String username, String password, String email,String date) throws ParseException {
        if(UtilisateurDao.check(username,password,email)==true){
            UtilisateurDao u = new UtilisateurDao();
            u.insertWithMd5(username,password,email,date);
        }
    }

    public Utilisateur login(String email, String password) throws SQLException {
        Utilisateur u = null;
        Connection c = null;
        Statement stmt = null;
        try {
            c = Rescue.connectToDatabase();
            stmt = c.createStatement();
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
        }finally{
            if(c!=null) {
                c.close();
            }
            if(stmt!=null) {
                stmt.close();
            }
        }
    }

    public boolean checkLoginInformations(String email, String password) throws SQLException {
        Utilisateur u = null;
        Connection c = null;
        Statement stmt = null;
        try {
            c = Rescue.connectToDatabase();
            stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from utilisateur where email='"+email+"' and password=md5('"+password+"')");
            if(res.next()){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }finally{
            if(c!=null) {
                c.close();
            }
            if(stmt!=null) {
                stmt.close();
            }
        }
    }

    public String insertTokenUser(Utilisateur user) throws SQLException {
        Connection conn = null;
        String token = null;
        PreparedStatement pst = null;
        try {
            UtilisateurDao dao = new UtilisateurDao();
            token = dao.createToken(user.getId());
            java.sql.Date creation = java.sql.Date.valueOf(LocalDate.now());
            java.sql.Date expiration = java.sql.Date.valueOf(LocalDate.now().plusDays(3));
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

        } catch (Exception e) {
            conn.rollback();
            return null;
        }finally{
            if(conn!=null) {
                conn.close();
            }
            if(pst!=null) {
                pst.close();
            }
        }
        return token;
    }

    @Transactional
    public void remove(Long id) {
        Utilisateur t = em.find(Utilisateur.class,id);
        if(t==null)return;
        tx.begin();
        em.remove(t);
        tx.commit();
    }

    public static Utilisateur getUserById(int id) throws SQLException {
        Utilisateur user = null;
        Connection c = null;
        Statement stmt = null;
        try {
            c = Rescue.connectToDatabase();
            stmt = c.createStatement();
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
        }finally{
            if(c!=null) {
                c.close();
            }
            if(stmt!=null) {
                stmt.close();
            }
        }
    }

    public Utilisateur getUsersById(int id) throws SQLException {
        Utilisateur user = null;
        Connection c = null;
        Statement stmt = null;
        try {
            c = Rescue.connectToDatabase();
            stmt = c.createStatement();
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
        }finally{
            if(c!=null) {
                c.close();
            }
            if(stmt!=null) {
                stmt.close();
            }
        }
    }

    public Utilisateur getIDbyEmail(String email) throws SQLException {
        Utilisateur user = null;
        Connection c = null;
        Statement stmt = null;
        try {
            c = Rescue.connectToDatabase();
            stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select * from utilisateur where email='"+email+"'");
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
