package com.projet.cloudmobile.dao;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projet.cloudmobile.connection.Rescue;
import com.projet.cloudmobile.exception.StorageException;
import com.projet.cloudmobile.exception.StorageProperties;
import com.projet.cloudmobile.models.*;
import javaxt.io.Image;
import org.apache.commons.io.FilenameUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sun.misc.Signal;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.List;

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
        return em.createQuery("select c from Signalement c where c.etat='Nouveau'").getResultList();
    }

    public List<Signalement> getSignalementEnCours(String id){
        return em.createQuery("select c from Signalement c join SignalementRegion e on c.id = e.id where c.etat='En cours' and e.region.id="+id).getResultList();
    }

    public List<SignalementRegion> getSignalementByRegion(int id){
        return em.createQuery("select c from SignalementRegion c where c.region.id="+id).getResultList();
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

    public Long getLastID(){
        Long ID = Long.valueOf(0);
        try {
            Connection c = Rescue.connectToDatabase();
            Statement stmt = c.createStatement();
            ResultSet res = stmt.executeQuery("select max(id) as last from signalement");
            while(res.next()){

                ID = res.getLong("last");
            }
            return ID;
        }catch (Exception e){
            return ID;
        }
    }

    public String store(MultipartFile file,int suffixe) {
        StorageProperties properties = new StorageProperties();
        Path rootLocation = Paths.get(properties.getLocation());

        Long ID = this.getLastID();

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            //CHANGEMENT DE NOM
            String uploadedFileName = "signalement"+ID+"Url"+suffixe+ "." + extension;
            //FIN DE CHANGEMENT DE NOM

            Path destinationFile = rootLocation.resolve(
                            Paths.get(uploadedFileName))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);

                final String baseUrl =
                        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

                return uploadedFileName;
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    public double[] storeFileAll(List<MultipartFile> file){

        SignalementDao s = new SignalementDao();
        String nomImage="";
        for (int i = 0; i < file.size(); i++) {

            nomImage = s.store(file.get(i),i);

        }

        String url = "src/main/resources/static/images/"+nomImage;
        double[] longAndLat = this.getMeToo(url);

        return longAndLat;
    }

    public double[] getMeToo(String urlImage){
        Image img = new Image(urlImage);
        double[] value = img.getGPSCoordinate();
        if(value != null){
            return value;
        }
        else{
            double[] values = null;
            assert false;
            values[0] = 47.53033055555555;
            values[1] = -18.979833333333332;
            return values;
        }

    }

    @Transactional
    public void insertSignalement(String idtype,String idutilisateur,Date dtn,String description,double longitude,double latitude,String etat,String urlImg,String extension){
        tx.begin();
        int t = Integer.parseInt(idtype);
        int u = Integer.parseInt(idutilisateur);
        String query = "insert into signalement(id,datesignalement,description,etat,latitude,longitude,idtype,idutilisateur,urlImage,extension) values (DEFAULT,:dtn,:description,:etat,:latitude,:longitude,:type,:util,:url,:ext)";
        Query jpqlQuery = em.createNativeQuery(query)
                .setParameter("dtn",dtn)
                .setParameter("description",description)
                .setParameter("etat",etat)
                .setParameter("latitude",latitude)
                .setParameter("longitude",longitude)
                .setParameter("type",t)
                .setParameter("util",u)
                .setParameter("url",urlImg)
                .setParameter("ext",extension);
        em.joinTransaction();
        jpqlQuery.executeUpdate();

        tx.commit();
    }


}
