package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.dao.TokenRegionDao;
import com.projet.cloudmobile.dao.TokenUserDao;
import com.projet.cloudmobile.interfaces.NotificationRepository;
import com.projet.cloudmobile.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {
    @Autowired
    private NotificationRepository repository;

    @PostMapping("/insert")
    public ResponseEntity insert_notification(@RequestBody Notification notification,@RequestHeader String token) throws Exception {
        TokenDao admindao = new TokenDao();
        TokenRegionDao regiondao = new TokenRegionDao();
        if(admindao.isValidTokenAdmin(token)==true || regiondao.isValidTokenRegion(token)==true){
            repository.save(notification);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/getNotification")
    public ResponseEntity<ArrayList<Notification>> getNotifUser(@RequestParam int id,@RequestHeader String token) throws Exception {
        ArrayList<Notification> list = new ArrayList<>();
        TokenUserDao dao = new TokenUserDao();
        if(dao.isValidTokenUser(token)){
            list = repository.getNotificationUser(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
    }
}
