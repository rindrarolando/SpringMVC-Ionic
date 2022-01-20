package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.models.Signalement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/signalement")
public class SignalementRestController {
    @CrossOrigin
    @GetMapping("/getSignalements")
    public List<Signalement> getSignalements(){
        return new SignalementDao().getAllSignalement();
    }

    @CrossOrigin
    @GetMapping("/getSignalement")
    public Signalement getSignalement(@RequestParam(value = "id")String id){
        Signalement s = new Signalement();
        s= new SignalementDao().getSignalement(Long.valueOf(id));
        return s ;
    }

    @CrossOrigin
    @GetMapping("/deleteSignalement")
    public String deleteSignalement(@RequestParam(value = "id")String id){
        SignalementDao s = new SignalementDao();
         s.remove(Long.valueOf(id));
         return "Signalement avec l'ID = "+id+" est effacer ";
    }

    @CrossOrigin
<<<<<<< Updated upstream
    @GetMapping("/getNewSignalement")
    public List<Signalement> getNewSignalement(){
        SignalementDao s = new SignalementDao();
        return s.getNewSignalement();
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

=======
    @GetMapping("/SignalementByUtil")
    public List<Signalement> getSignalementByUtil(@RequestParam(value = "username")String username){

        return new SignalementDao().getSignalementByUtil(username);

    }

    @CrossOrigin
    @GetMapping("/statistiqueEtat")
    public List<Signalement> getStatistiqueEtat(){

        return new SignalementDao().getStatEtat();

    }
>>>>>>> Stashed changes

}
