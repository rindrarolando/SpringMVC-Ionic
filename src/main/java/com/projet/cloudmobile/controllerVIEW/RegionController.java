package com.projet.cloudmobile.controllerVIEW;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.models.Region;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
}
