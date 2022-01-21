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
}
