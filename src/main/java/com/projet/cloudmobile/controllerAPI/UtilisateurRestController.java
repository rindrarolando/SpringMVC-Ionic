package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.UtilisateurDao;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurRestController {
    @CrossOrigin
    @GetMapping("/getUtilisateurs")
    public List<Utilisateur> getUtilisateurs(){
        UtilisateurDao u = new UtilisateurDao();
        return u.getAllUtilisateur();
    }

    @CrossOrigin
    @GetMapping("/modifier")
    public Utilisateur getUtilisateur(@RequestParam("id")String id){
        UtilisateurDao u = new UtilisateurDao();
        Utilisateur ret = u.getUtilisateur(Long.parseLong(id));
        return ret;
    }

    @CrossOrigin
    @GetMapping("/inscription")
    public ResponseEntity makeInscription(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("email")String email){
       UtilisateurDao u = new UtilisateurDao();
       if(UtilisateurDao.check(username,password,email)==true){
           u.inscription(username,password,email);
           return new ResponseEntity(HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
       }
    }

    @CrossOrigin
    @GetMapping("/login")
    public ResponseEntity loginUtilisateur(@RequestParam("email")String email ,@RequestParam("password")String password) throws Exception {
        UtilisateurDao u = new UtilisateurDao();
        if(u.checkLoginInformations(email, password)==true){
            Utilisateur user = u.login(email, password);
            u.insertTokenUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
