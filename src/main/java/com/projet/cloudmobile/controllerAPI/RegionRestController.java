package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.dao.TokenRegionDao;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.Tokenregion;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    @CrossOrigin
    @PostMapping("/login_region")
    public ResponseEntity loginRegion(@RequestParam("username")String username ,@RequestParam("password")String password) throws SQLException {
        RegionDao dao = new RegionDao();
        Region region = dao.checkLoginRegion(username,password);
        if(region != null){
            TokenRegionDao tokdao = new TokenRegionDao();
            String token= tokdao.insertTokenRegion(region);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
