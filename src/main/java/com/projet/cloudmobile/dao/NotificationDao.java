package com.projet.cloudmobile.dao;

import com.projet.cloudmobile.interfaces.NotificationRepository;
import com.projet.cloudmobile.models.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationDao {
    @Autowired
    NotificationRepository repository;

    public void insertNotification(Notification n){
        this.repository.save(n);
    }
}