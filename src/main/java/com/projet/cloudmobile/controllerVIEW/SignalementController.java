package com.projet.cloudmobile.controllerVIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignalementController {
    @RequestMapping("/listeSignalement")
    public String exemple(){
        return "listeSignalement";
    }

    @RequestMapping("/NouveauxSignalements")
    public String nouveauxSignalements(){
        return "NouveauxSignalements";
    }

    @RequestMapping("/Signalement")
    public String signalement(HttpServletRequest request,@RequestParam(value = "id")String id){
        request.setAttribute("id",1);
        return "Signalement";
    }
}
