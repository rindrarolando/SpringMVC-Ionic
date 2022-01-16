package com.projet.cloudmobile.controllerVIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
