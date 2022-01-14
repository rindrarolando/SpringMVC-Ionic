package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signalement")
public class SignalementRestController {
    @CrossOrigin
    @GetMapping("/getSignalements")
    public List<Signalement> getSignalements(){
        return new SignalementDao().getAllSignalement();
    }

    @CrossOrigin
    @GetMapping("/getSignalement")
    public Signalement getSignalement(@RequestParam(value = "id")String id){
        Signalement s = new Signalement();
        s= new SignalementDao().getSignalement(Long.valueOf(id));
        return s ;
    }

    @CrossOrigin
    @GetMapping("/deleteSignalement")
    public String deleteSignalement(@RequestParam(value = "id")String id){
        SignalementDao s = new SignalementDao();
         s.remove(Long.valueOf(id));
         return "Signalement avec l'ID = "+id+" est effacer ";
    }
}
