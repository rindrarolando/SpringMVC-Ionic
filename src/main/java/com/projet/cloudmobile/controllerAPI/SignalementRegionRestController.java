package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.SignalementRegionDao;
import com.projet.cloudmobile.models.SignalementRegion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signalementregion")
public class SignalementRegionRestController {

    @CrossOrigin
    @GetMapping("/stat")
    public Long getStat(@RequestParam(value = "id")String id){
        SignalementRegionDao s = new SignalementRegionDao();
        return s.getStatReg(Long.valueOf(id));
    }
}
