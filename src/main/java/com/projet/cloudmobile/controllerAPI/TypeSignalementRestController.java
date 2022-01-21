package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TypeSignalementDao;
import com.projet.cloudmobile.dao.UtilisateurDao;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.TypeSignalement;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typesignalement")
public class TypeSignalementRestController {
    @CrossOrigin
    @GetMapping("/getTypeSignalements")
    public List<TypeSignalement> getTypeSignalements(){
        TypeSignalementDao s = new TypeSignalementDao();
        return s.getAllTypeSignalement();
    }

    @CrossOrigin
    @GetMapping("/modifier")
    public TypeSignalement getType(@RequestParam("id")String id){
        TypeSignalementDao u = new TypeSignalementDao();
        TypeSignalement ret = u.getTypeSignalement(Integer.parseInt(id));
        return ret;
    }
}
