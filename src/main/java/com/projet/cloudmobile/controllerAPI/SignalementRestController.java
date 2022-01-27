package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/signalement")
public class SignalementRestController {
    @CrossOrigin
    @GetMapping("/getSignalements")
    public ResponseEntity<List<Signalement>> getSignalements(@RequestHeader("token") String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getAllSignalement(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/getSignalement")
    public ResponseEntity<Signalement> getSignalement(@RequestHeader("tokekn") String token,@RequestParam(value = "id")String id){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<Signalement>(new SignalementDao().getSignalement(Long.valueOf(id)), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/deleteSignalement")
    public ResponseEntity deleteSignalement(@RequestHeader("token")String token,@RequestParam(value = "id")String id){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                new SignalementDao().remove(Long.valueOf(id));
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/getNewSignalement")
    public ResponseEntity<List<Signalement>> getNewSignalement(@RequestHeader("token") String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getNewSignalement(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statEtat1")
    public long getStatE1(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat1();
    }
    @CrossOrigin
    @GetMapping("/statEtat2")
    public long getStatE2(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat2();
    }
    @CrossOrigin
    @GetMapping("/statEtat3")
    public long getStatE3(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat3();
    }

    @CrossOrigin
    @GetMapping("/statEtat1N")
    public long getStatE1N(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat1N();
    }
    @CrossOrigin
    @GetMapping("/statEtat2N")
    public long getStatE2N(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat2N();
    }
    @CrossOrigin
    @GetMapping("/statEtat3N")
    public long getStatE3N(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat3N();
    }

    @CrossOrigin
    @GetMapping("/statEtat1E")
    public long getStatE1E(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat1E();
    }
    @CrossOrigin
    @GetMapping("/statEtat2E")
    public long getStatE2E(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat2E();
    }
    @CrossOrigin
    @GetMapping("/statEtat3E")
    public long getStatE3E(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat3E();
    }

    @CrossOrigin
    @GetMapping("/statEtat1T")
    public long getStatE1T(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat1T();
    }
    @CrossOrigin
    @GetMapping("/statEtat2T")
    public long getStatE2T(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat2T();
    }
    @CrossOrigin
    @GetMapping("/statEtat3T")
    public long getStatE3T(){
        SignalementDao s = new SignalementDao();
        return s.getStatEtat3T();
    }

    @GetMapping("/SignalementByUtil")
    public ResponseEntity<List<Signalement>> getSignalementByUtil(@RequestHeader("token")String token,@RequestParam(value = "username")String username){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getSignalementByUtil(username), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/statistiqueEtat")
    public ResponseEntity<List<Signalement>> getStatistiqueEtat(@RequestHeader("token")String token){
        TokenDao dao = new TokenDao();
        try{
            if(dao.isAdminToken(token)==true) {
                return new ResponseEntity<List<Signalement>>(new SignalementDao().getStatEtat(), HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/signalementRegion")
    public List<Signalement> getSignalementByRegion(@RequestParam(value="id")String id){
        return new SignalementDao().getSignalementByRegion(Integer.parseInt(id));
    }

}
