package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionRestController {
    @CrossOrigin
    @GetMapping("/getRegions")
    public List<Region> getAllRegions(@RequestHeader("token") String token){
        return new RegionDao().getAllRegions();
    }

    @CrossOrigin
    @GetMapping("/modifier")
    public Region getRegion(@RequestParam("id")String id){
        RegionDao r = new RegionDao();
        Region ret = r.getRegion(Integer.parseInt(id));
        return ret;
    }
}
