package com.projet.cloudmobile.interfaces;

import com.projet.cloudmobile.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;

public interface NotificationRepository extends MongoRepository<Notification, Integer> {
    @Query("{'iduser':?0}")
    ArrayList<Notification> getNotificationUser(int id);
}