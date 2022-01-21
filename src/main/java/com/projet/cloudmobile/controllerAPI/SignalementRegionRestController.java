package com.projet.cloudmobile.controllerAPI;


import com.projet.cloudmobile.dao.*;
import com.projet.cloudmobile.models.*;
import org.springframework.web.bind.annotation.*;
import sun.misc.Signal;


import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.SignalementRegionDao;
import com.projet.cloudmobile.models.SignalementRegion;
import org.springframework.web.bind.annotation.*;

import com.projet.cloudmobile.models.Signalement;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@RestController
@RequestMapping("/signalementregion")
public class SignalementRegionRestController {

    @CrossOrigin
    @GetMapping("/stat")
    public Long getStat(@RequestParam(value = "id")String id) {
        SignalementRegionDao s = new SignalementRegionDao();
        return s.getStatReg(Long.valueOf(id));
    }

    @CrossOrigin
    @GetMapping("/SignalementByFilter")
    public List<Signalement> getSignalementByUtil(@RequestParam(value = "idRegion")String idRegion , @RequestParam(value = "idType")String idType , @RequestParam(value = "etat")String etat , @RequestParam(value = "debut")String debut , @RequestParam(value = "fin")String fin) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = formatter.parse(debut);

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date f = formatter2.parse(fin);

        return new SignalementRegionDao().getSignalementByFilter(Long.valueOf(idRegion) ,Long.valueOf(idType) ,etat , d ,f);


    }

}
