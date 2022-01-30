package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.SignalementRegion;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/signalement")
public class SignalementRestController {
    @CrossOrigin
    @GetMapping("/getSignalements")
    public ResponseEntity<List<Signalement>> getSignalements(@RequestHeader("token") String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getAllSignalement(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/getSignalement")
    public ResponseEntity<Signalement> getSignalement(@RequestHeader("token") String token,@RequestParam(value = "id")String id){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Signalement>(new SignalementDao().getSignalement(Long.valueOf(id)), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/deleteSignalement")
    public ResponseEntity deleteSignalement(@RequestHeader("token")String token,@RequestParam(value = "id")String id){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                new SignalementDao().remove(Long.valueOf(id));
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/getNewSignalement")
    public ResponseEntity<List<Signalement>> getNewSignalement(@RequestHeader("token") String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getNewSignalement(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat1")
    public ResponseEntity<Long> getStatE1(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping("/statEtat2")
    public ResponseEntity<Long> getStatE2(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat3")
    public ResponseEntity<Long> getStatE3(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat1N")
    public ResponseEntity<Long> getStatE1N(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1N(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat2N")
    public ResponseEntity<Long> getStatE2N(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2N(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat3N")
    public ResponseEntity<Long> getStatE3N(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3N(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat1E")
    public ResponseEntity<Long> getStatE1E(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1E(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat2E")
    public ResponseEntity<Long> getStatE2E(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2E(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat3E")
    public ResponseEntity<Long> getStatE3E(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3E(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat1T")
    public ResponseEntity<Long> getStatE1T(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1T(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat2T")
    public ResponseEntity<Long> getStatE2T(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2T(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat3T")
    public ResponseEntity<Long> getStatE3T(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3T(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/SignalementByUtil")
    public ResponseEntity<List<Signalement>> getSignalementByUtil(@RequestHeader("token")String token,@RequestParam(value = "username")String username){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getSignalementByUtil(username), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statistiqueEtat")
    public ResponseEntity<List<Signalement>> getStatistiqueEtat(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getStatEtat(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/signalementRegion")
    public ResponseEntity<List<SignalementRegion>> getSignalementByRegion(@RequestHeader("token")String token, @RequestParam(value="id")String id){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<SignalementRegion>>(new SignalementDao().getSignalementByRegion(Integer.parseInt(id)), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statistique/region")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatReg(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueRegion(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin
    @GetMapping("/statistique/region/encours")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatReg1(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueRegion1(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping("/statistique/region/nouveau")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatReg2(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueRegion2(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping("/statistique/region/termine")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatReg3(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueRegion3(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //STATISTIQUE TYPE
    @CrossOrigin
    @GetMapping("/statistique/type")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatType(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueType(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping("/statistique/type/encours")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatType1(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueTypeCas1(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping("/statistique/type/nouveau")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatType2(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueTypeCas2(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping("/statistique/type/termine")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatType3(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueTypeCas3(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //STATISTIQUE UTILISATEUR
    @CrossOrigin
    @GetMapping("/statistique/utilisateur")
    public ResponseEntity<ArrayList<HashMap<String, Object>>> getStatUtil(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<ArrayList<HashMap<String, Object>>>(new SignalementDao().getStatistiqueUtil(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping("/insertSignalement")
    public boolean insertfile(@RequestParam(value="descripiton") String description, @RequestParam(value="etat") String etat, @RequestParam(value="idtype") String idtype, @RequestParam(value="idutilisateur") String idutilisateur, @RequestParam(value="file") List<MultipartFile> file){

        //Mamindra le sary anaty /Users/macbook/Desktop/S5/Hehe/Projet-Cloud-mobile/src/main/resources/static/images/
        SignalementDao s = new SignalementDao();
        //GET LAST ID INSERTED
        Long ID = s.getLastID();
        //GET DATE NOW
        long millis = System.currentTimeMillis();
        Date dtn = new java.sql.Date(millis);
        //GET LONGITUDE AND LATITUDE AND STORE THE FILE
        double[] valera = s.storeFileAll(file);
        double longitude = valera[0];
        double latitude = valera[1];
        //GET THE EXTENSION OF THE FILE
        String extension = FilenameUtils.getExtension(file.get(0).getOriginalFilename());

        //CHANGEMENT DE NOM
        String urlImg = "signalement"+ID+"Url";


        s.insertSignalement(idtype,idutilisateur,dtn,description,longitude,latitude,etat,urlImg,extension);
        return true;
    }

}
