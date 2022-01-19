package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionRestController {

    @GetMapping("/getRegions")
    public List<Region> getAllRegions(){
        RegionDao r = new RegionDao();
        return r.getAllRegions();
    }
}
