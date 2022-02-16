package com.projet.cloudmobile.controllerVIEW;

import com.projet.cloudmobile.dao.AdministrateurDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.models.Administrateur;
import com.projet.cloudmobile.models.Tokenadmin;
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


    @RequestMapping("/Modifier")
    public String modifier() {
        return "Modifier";
    }


    @RequestMapping("/")
    public String acceuil(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("admin")==null) {
            return "logadmin";
        }else{
            Administrateur admin =(Administrateur) session.getAttribute("admin");
            TokenDao dao = new TokenDao();
            if(dao.isValidTokenAdmin((String) session.getAttribute("token"))==true){
                return "redirect:/listeSignalement";
            }else{
                dao.deleteTokenAdmin((String) session.getAttribute("token"), admin.getId());
                session.removeAttribute("token");
                String token = dao.insertTokenAdmin(admin);
                session.setAttribute("token",token);
                return "redirect:/listeSignalement";
            }
        }
    }

    @RequestMapping(value= "/login", method = RequestMethod.POST)
    public String traitementLogin(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String id = request.getParameter("identifiant");
        String mdp = request.getParameter("mdp");
        AdministrateurDao dao = new AdministrateurDao();
        TokenDao tokdao= new TokenDao();
        if(dao.checkAdmin(id,mdp)!=null){
            Administrateur admin = dao.checkAdmin(id,mdp);
            String token_admin = tokdao.insertTokenAdmin(admin);
            session.setAttribute("admin", admin);
            session.setAttribute("token",token_admin);
            return "redirect:/listeSignalement";
        }else{
            return "redirect:/?error=1";
        }
    }

    @RequestMapping("/logout")
    public String deconnexion(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Administrateur admin = (Administrateur) session.getAttribute("admin");
        TokenDao dao = new TokenDao();
        dao.deleteTokenAdmin((String) session.getAttribute("token"), admin.getId());
        session.invalidate();
        return "redirect:/";

    }
}
