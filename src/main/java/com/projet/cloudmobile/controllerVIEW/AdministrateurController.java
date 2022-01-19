package com.projet.cloudmobile.controllerVIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministrateurController {
    @RequestMapping("/Tables")
    public String tables(){
        return "Tables";
    }
}
