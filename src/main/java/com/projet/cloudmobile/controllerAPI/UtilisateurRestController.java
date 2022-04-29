package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.*;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Response;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
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
    @GetMapping("/getID")
    public  Utilisateur getID(@RequestHeader("token")String token,@RequestParam("email")String email) throws SQLException {
        TokenUserDao dao = new TokenUserDao();
        Utilisateur u = new Utilisateur();
        try{
            if(dao.isValidTokenUser(token)==true) {
                return new UtilisateurDao().getIDbyEmail(email);
            }else{
                return new UtilisateurDao().getIDbyEmail(email);
            }
        }catch(Exception e){
            return new UtilisateurDao().getIDbyEmail(email);
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
    public Response makeInscription(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("date") String date) throws ParseException {
        UtilisateurDao u = new UtilisateurDao();
        Response response = new Response();
        if(UtilisateurDao.check(username,password,email)==true){
            u.inscription(username,password,email,date);
            response.setStatus("200");
            response.setMessage("Inscription reussie");

            return response;
        }else{
            response.setStatus("400");
            response.setMessage("Inscripiton impossible");
            return response;
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    public Response loginUtilisateur(@RequestParam("email") String email ,@RequestParam("password") String password) throws Exception {
        String token = null;
        UtilisateurDao u = new UtilisateurDao();
        Response response = new Response();

        if(u.checkLoginInformations(email, password)==true){
            Utilisateur user = u.login(email, password);
            token = u.insertTokenUser(user);
            response.setStatus("200");
            response.setMessage("Inscription reussie");
            response.setDatas(token);

            return response;
        }else{
            response.setStatus("401");
            response.setMessage("Mot de passe ou email incorrect");
            //response.setDatas(token);
            return response;
        }
    }

}
