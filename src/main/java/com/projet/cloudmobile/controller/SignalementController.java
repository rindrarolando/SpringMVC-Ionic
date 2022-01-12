package com.projet.cloudmobile.controller;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signalement")
public class SignalementController {
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
}
