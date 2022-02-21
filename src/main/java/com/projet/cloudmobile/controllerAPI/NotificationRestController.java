package com.projet.cloudmobile.controllerAPI;

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
    public String insert_notification(@RequestBody Notification notification){
        repository.save(notification);
        return "nety";
    }

    @RequestMapping("/getNotification")
    public ResponseEntity<ArrayList<Notification>> getNotifUser(@RequestParam int id){
        ArrayList<Notification> list = new ArrayList<>();
        list = repository.getNotificationUser(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
