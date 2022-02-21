package com.projet.cloudmobile.interfaces;

import com.projet.cloudmobile.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, Integer> {
}