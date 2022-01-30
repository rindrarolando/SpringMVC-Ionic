package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/signalement")
public class SignalementRestController {
    @CrossOrigin
    @GetMapping("/getSignalements")
    public ResponseEntity<List<Signalement>> getSignalements(){

                return new ResponseEntity<List<Signalement>>(new SignalementDao().getAllSignalement(), HttpStatus.ACCEPTED);

    }

    @CrossOrigin
    @GetMapping("/getSignalement")
    public ResponseEntity<Signalement> getSignalement(@RequestHeader("token") String token,@RequestParam(value = "id")String id){
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
    public ResponseEntity deleteSignalement(@RequestParam(value = "id")String id){
        SignalementDao s = new SignalementDao();
        s.remove(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.ACCEPTED);


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
    public ResponseEntity<Long> getStatE1(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat2")
    public ResponseEntity<Long> getStatE2(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat3")
    public ResponseEntity<Long> getStatE3(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3(), HttpStatus.ACCEPTED);

    }

    @CrossOrigin
    @GetMapping("/statEtat1N")
    public ResponseEntity<Long> getStatE1N(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1N(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat2N")
    public ResponseEntity<Long> getStatE2N(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2N(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat3N")
    public ResponseEntity<Long> getStatE3N(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3N(), HttpStatus.ACCEPTED);

    }

    @CrossOrigin
    @GetMapping("/statEtat1E")
    public ResponseEntity<Long> getStatE1E(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1E(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat2E")
    public ResponseEntity<Long> getStatE2E(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2E(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat3E")
    public ResponseEntity<Long> getStatE3E(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3E(), HttpStatus.ACCEPTED);

    }

    @CrossOrigin
    @GetMapping("/statEtat1T")
    public ResponseEntity<Long> getStatE1T(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat1T(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat2T")
    public ResponseEntity<Long> getStatE2T(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat2T(), HttpStatus.ACCEPTED);

    }
    @CrossOrigin
    @GetMapping("/statEtat3T")
    public ResponseEntity<Long> getStatE3T(){

                return new ResponseEntity<Long>(new SignalementDao().getStatEtat3T(), HttpStatus.ACCEPTED);

    }

    @GetMapping("/SignalementByUtil")
    public ResponseEntity<List<Signalement>> getSignalementByUtil(@RequestParam(value = "username")String username){

                return new ResponseEntity<List<Signalement>>(new SignalementDao().getSignalementByUtil(username), HttpStatus.ACCEPTED);

    }

    @CrossOrigin
    @GetMapping("/statistiqueEtat")
    public ResponseEntity<List<Signalement>> getStatistiqueEtat(){

                return new ResponseEntity<List<Signalement>>(new SignalementDao().getStatEtat(), HttpStatus.ACCEPTED);

    }

    @CrossOrigin
    @GetMapping("/signalementRegion")
    public List<Signalement> getSignalementByRegion(@RequestParam(value="id")String id){
        return new SignalementDao().getSignalementByRegion(Integer.parseInt(id));
    }

    @CrossOrigin
    @GetMapping("/statistique/region")
    public ArrayList<HashMap<String, Object>> getStatReg(){

        return new SignalementDao().getStatistiqueRegion();

    }
    @CrossOrigin
    @GetMapping("/statistique/region/encours")
    public ArrayList<HashMap<String, Object>> getStatReg1(){

        return new SignalementDao().getStatistiqueRegion1();

    }
    @CrossOrigin
    @GetMapping("/statistique/region/nouveau")
    public ArrayList<HashMap<String, Object>> getStatReg2(){

        return new SignalementDao().getStatistiqueRegion2();

    }
    @CrossOrigin
    @GetMapping("/statistique/region/termine")
    public ArrayList<HashMap<String, Object>> getStatReg3(){

        return new SignalementDao().getStatistiqueRegion3();

    }


    //STATISTIQUE TYPE
    @CrossOrigin
    @GetMapping("/statistique/type")
    public ArrayList<HashMap<String, Object>> getStatType(){

        return new SignalementDao().getStatistiqueType();

    }
    @CrossOrigin
    @GetMapping("/statistique/type/encours")
    public ArrayList<HashMap<String, Object>> getStatType1(){

        return new SignalementDao().getStatistiqueTypeCas1();

    }
    @CrossOrigin
    @GetMapping("/statistique/type/nouveau")
    public ArrayList<HashMap<String, Object>> getStatType2(){

        return new SignalementDao().getStatistiqueTypeCas2();

    }
    @CrossOrigin
    @GetMapping("/statistique/type/termine")
    public ArrayList<HashMap<String, Object>> getStatType3(){

        return new SignalementDao().getStatistiqueTypeCas3();

    }

    //STATISTIQUE UTILISATEUR
    @CrossOrigin
    @GetMapping("/statistique/utilisateur")
    public ArrayList<HashMap<String, Object>> getStatUtil(){

        return new SignalementDao().getStatistiqueUtil();

    }

}
