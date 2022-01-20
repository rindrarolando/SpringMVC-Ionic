package com.projet.cloudmobile.controllerVIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignalementController {
    @RequestMapping("/listeSignalement")
    public String exemple(){
        return "exemple";
    }

    @RequestMapping("/NouveauxSignalements")
    public String nouveauxSignalements(){
        return "NouveauxSignalements";
    }

    @RequestMapping("/stat")
    public String getStatistique(){
        return "statistiques";
    }

    @RequestMapping("/Signalement")
    public String signalement(HttpServletRequest request,@RequestParam(value = "id")String id){
        request.setAttribute("id",1);
        return "Signalement";
    }
}
