package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.*;
import com.projet.cloudmobile.models.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
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

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity update(@RequestHeader("token") String token, @RequestParam("id")String id) throws SQLException {
        TokenRegionDao dao = new TokenRegionDao();
        try {
            if(dao.isValidTokenRegion(token)){
                //new SignalementDao().updateSignalement(id);
                Signalement s = new SignalementDao().getSignalement(Long.parseLong(id));
                LocalDateTime rightNow = LocalDateTime.now();
                Notification n = new Notification(s.getUtilisateur().getId().intValue(),s.getDescription(),rightNow);
                new NotificationDao().insertNotification(n);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/getSignalementEnCours")
    public ResponseEntity<List<SignalementRegion>> getSignalementsEnCours(@RequestHeader("token") String token, @RequestParam("id")String id){
        TokenRegionDao dao = new TokenRegionDao();
        try{
            if(dao.isValidTokenRegion(token)==true) {
                return new ResponseEntity<List<SignalementRegion>>(new SignalementRegionDao().getSignalementEnCours(id), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/getSignalementsTermines")
    public ResponseEntity<List<SignalementRegion>> getSignalementsTermines(@RequestHeader("token") String token, @RequestParam("id")String id){
        TokenRegionDao dao = new TokenRegionDao();
        try{
            if(dao.isValidTokenRegion(token)==true) {
                return new ResponseEntity<List<SignalementRegion>>(new SignalementRegionDao().getSignalementTermines(id), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/rechercheSignalement")
    public ResponseEntity<List<SignalementRegion>> getRechercheSignalement(@RequestHeader("token") String token,@RequestParam("id")String id, @RequestParam("date")String date,@RequestParam("type")String type,@RequestParam("etat")String etat){
        TokenRegionDao dao = new TokenRegionDao();
        try{
            if(dao.isValidTokenRegion(token)==true) {
                return new ResponseEntity<List<SignalementRegion>>(new SignalementRegionDao().getSignalementFiltre(id,etat,date,type), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
