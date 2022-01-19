package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.UtilisateurDao;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
