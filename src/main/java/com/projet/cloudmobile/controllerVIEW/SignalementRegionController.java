package com.projet.cloudmobile.controllerVIEW;

import com.projet.cloudmobile.dao.RegionDao;
import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.SignalementRegionDao;
import com.projet.cloudmobile.dao.TypeSignalementDao;
import com.projet.cloudmobile.models.Region;
import com.projet.cloudmobile.models.Signalement;
import com.projet.cloudmobile.models.SignalementRegion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignalementRegionController {
    @CrossOrigin
    @GetMapping("/signalement/attribuer")
    public String attribuer(@RequestParam(value = "idSignalement")String idSignalement, @RequestParam(value = "idRegion")String idRegion){
        TypeSignalementDao t = new TypeSignalementDao();
        SignalementDao sDao = new SignalementDao();
        Signalement s = sDao.getSignalement(Long.parseLong(idSignalement));
        s.setEtat("En cours");
        new SignalementDao().insert(s);
        Region r = new RegionDao().getRegion(Integer.parseInt(idRegion));
        SignalementRegion add = new SignalementRegion(s,r,false);
        SignalementRegionDao srDao = new SignalementRegionDao();
        srDao.insert(add);
        return "NouveauxSignalements";
    }
}
