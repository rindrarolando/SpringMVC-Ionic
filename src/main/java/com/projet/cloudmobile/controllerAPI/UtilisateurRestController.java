package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.dao.UtilisateurDao;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurRestController {
    @CrossOrigin
    @GetMapping("/getUtilisateurs")
    public  ResponseEntity<List<Utilisateur>> getUtilisateurs(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Utilisateur>>(new UtilisateurDao().getAllUtilisateur(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/modifier")
    public Utilisateur getUtilisateur(@RequestParam("id")String id){
        UtilisateurDao u = new UtilisateurDao();
        Utilisateur ret = u.getUtilisateur(Long.parseLong(id));
        return ret;
    }

    @CrossOrigin
    @PostMapping("/inscription")
    public ResponseEntity makeInscription(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("date") String date) throws ParseException {
        UtilisateurDao u = new UtilisateurDao();
        if(UtilisateurDao.check(username,password,email)==true){
            u.inscription(username,password,email,date);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> loginUtilisateur(@RequestParam("email") String email ,@RequestParam("password") String password) throws Exception {
        String token = null;
        UtilisateurDao u = new UtilisateurDao();
        if(u.checkLoginInformations(email, password)==true){
            Utilisateur user = u.login(email, password);
            token = u.insertTokenUser(user);
            return new ResponseEntity<String>(token,HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

}
