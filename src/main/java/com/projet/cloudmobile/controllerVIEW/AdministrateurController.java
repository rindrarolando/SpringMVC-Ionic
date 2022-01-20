package com.projet.cloudmobile.controllerVIEW;

import com.projet.cloudmobile.dao.AdministrateurDao;
import com.projet.cloudmobile.models.Administrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdministrateurController {

    @RequestMapping("/Tables")
    public String tables() {
        return "Tables";
    }

    @RequestMapping("/")
    public String acceuil(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        if(session.getAttribute("admin")==null) {
            return "logadmin";
        }else{
            return "redirect:/listeSignalement";
        }
    }

    @GetMapping("/login")
    public String traitementLogin(HttpServletRequest request , HttpSession session){
        session = request.getSession(true);
        String id = request.getParameter("identifiant");
        String mdp = request.getParameter("mdp");
        AdministrateurDao dao = new AdministrateurDao();
        if(dao.checkAdmin(id,mdp)!=null){
            Administrateur admin = dao.checkAdmin(id,mdp);
            session.setAttribute("admin", admin);
            return "redirect:/listeSignalement";
        }else{
            return "redirect:/?error=1";
        }
    }

    @RequestMapping("/logout")
    public String deconnexion(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";

    }
}
