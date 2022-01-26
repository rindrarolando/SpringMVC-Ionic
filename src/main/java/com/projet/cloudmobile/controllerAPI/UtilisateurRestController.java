package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.UtilisateurDao;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.web.bind.annotation.*;

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
    public boolean makeInscription(@RequestParam("username")String username, @RequestParam("password")String password,@RequestParam("email")String email){
       UtilisateurDao u = new UtilisateurDao();
       return u.checkInscription(username,password,email);
    }

    @CrossOrigin
    @GetMapping("/login")
    public Utilisateur loginUtilisateur(@RequestParam("email")String email ,@RequestParam("password")String password){
        UtilisateurDao u = new UtilisateurDao();
        return u.checkLogin(email,password);

        /*
        if -1 utilisateur not there if != -1 utilisateur there
        long i = -1;
        if(u.checkLogin(email,password) != null){
            i = u.checkLogin(email,password).getId();
            return i;
        }
        else{
            return i;
        }*/

    }

}
