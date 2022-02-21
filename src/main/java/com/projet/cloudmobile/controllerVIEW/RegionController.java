package com.projet.cloudmobile.controllerVIEW;

import com.projet.cloudmobile.dao.*;
import com.projet.cloudmobile.models.Administrateur;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/region")
public class RegionController {
    @RequestMapping("/region/modifier")
    public String modifier(@RequestParam("id")String id,@RequestParam("designation")String designation,@RequestParam("username")String username,@RequestParam("password")String password){
        RegionDao dao = new RegionDao();
        Region r = dao.getRegion(Integer.parseInt(id));
        r.setDesignation(designation);
        r.setUsername(username);
        r.setPassword(password);
        dao.insert(r);
        return "Tables";
    }

    @RequestMapping("/")
    public String login_region(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        if(session.getAttribute("region")==null) {
            return "loginregion";
        }else{
            Region region =(Region) session.getAttribute("region");
            TokenRegionDao dao = new TokenRegionDao();
            if(dao.isValidTokenRegion((String) session.getAttribute("token_region"))==true){
                return "redirect:/region/test_region";
            }else{
                dao.deleteTokenRegion((String) session.getAttribute("token_region"), region.getId());
                session.removeAttribute("token_region");
                String token = dao.insertTokenRegion(region);
                session.setAttribute("token_region",token);
                return "redirect:/region/test_region";
            }
        }
    }

    @RequestMapping(value= "/login" , method = RequestMethod.POST)
    public String traitement_login(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String mdp = request.getParameter("mdp");
        RegionDao dao = new RegionDao();
        TokenRegionDao tokdao= new TokenRegionDao();
        if(dao.checkLoginRegion(username,mdp)!=null){
            Region region = dao.checkLoginRegion(username,mdp);
            String token_region = tokdao.insertTokenRegion(region);
            session.setAttribute("region", region);
            session.setAttribute("token_region",token_region);
            return "redirect:/region/indexRegion";
        }else{
            return "redirect:/region/?error=1";
        }
    }

    @RequestMapping("/indexRegion")
    public String accueil(){
        return "indexRegion";
    }

    @RequestMapping("/listeSignalementRegion")
    public String listeSignalements(){
        return "ListeSignalementRegion";
    }

    @RequestMapping("signalement")
    public String signalement(HttpServletRequest request,@RequestParam(value = "id")String id){
        request.setAttribute("id",id);
        return "SignalementRegion";
    }

    @CrossOrigin
    @GetMapping("/getSignalement")
    public ResponseEntity<Signalement> getSignalement(@RequestHeader("token") String token, @RequestParam(value = "id")String id){
        TokenRegionDao dao = new TokenRegionDao();
        try{
            if(dao.isRegionToken(token)==true) {
                return new ResponseEntity<Signalement>(new SignalementDao().getSignalement(Long.valueOf(id)), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/logout")
    public String deconnexion(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Region region = (Region) session.getAttribute("region");
        TokenRegionDao dao = new TokenRegionDao();
        dao.deleteTokenRegion((String) session.getAttribute("token_region"), region.getId());
        session.invalidate();
        return "redirect:/region/";
    }
}
