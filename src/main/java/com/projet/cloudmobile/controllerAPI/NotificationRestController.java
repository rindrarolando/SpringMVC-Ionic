package com.projet.cloudmobile.controllerAPI;

import com.projet.cloudmobile.interfaces.NotificationRepository;
import com.projet.cloudmobile.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
