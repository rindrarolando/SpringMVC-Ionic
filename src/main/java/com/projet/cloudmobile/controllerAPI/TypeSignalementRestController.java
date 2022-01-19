package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TypeSignalementDao;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.TypeSignalement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
