package com.projet.cloudmobile.controllerVIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignalementController {
    @RequestMapping("/listeSignalement")
    public String exemple(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("admin")!=null){
            return "liste";
        }else {
            return "/";
        }
    }

    @RequestMapping("/NouveauxSignalements")
    public String nouveauxSignalements(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("admin")!=null){
            return "NouveauxSignalements";
        }else {
            return "/";
        }
    }

    @RequestMapping("/stat")
    public String getStatistique(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("admin")!=null){
            return "statistiques";
        }else {
            return "/";
        }
    }

    @RequestMapping("/Signalement")
    public String signalement(HttpServletRequest request,@RequestParam(value = "id")String id){
        request.setAttribute("id",1);
        HttpSession session = request.getSession();
        if(session.getAttribute("admin")!=null){
            return "Signalement";
        }else {
            return "/";
        }
    }
}
