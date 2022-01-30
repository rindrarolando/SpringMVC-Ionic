package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.dao.TypeSignalementDao;
import com.projet.cloudmobile.dao.UtilisateurDao;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.TypeSignalement;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typesignalement")
public class TypeSignalementRestController {
    @CrossOrigin
    @GetMapping("/getTypeSignalements")
    public ResponseEntity<List<TypeSignalement>> getTypeSignalements(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<TypeSignalement>>(new TypeSignalementDao().getAllTypeSignalement(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/modifier")
    public TypeSignalement getType(@RequestParam("id")String id){
        TypeSignalementDao u = new TypeSignalementDao();
        TypeSignalement ret = u.getTypeSignalement(Integer.parseInt(id));
        return ret;
    }
}
