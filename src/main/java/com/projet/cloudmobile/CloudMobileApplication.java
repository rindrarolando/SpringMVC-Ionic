package com.projet.cloudmobile;

import com.projet.cloudmobile.dao.NotificationDao;
import com.projet.cloudmobile.interfaces.NotificationRepository;
import com.projet.cloudmobile.models.Notification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class CloudMobileApplication{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CloudMobileApplication.class, args);
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
		LocalDateTime now = LocalDateTime.now();
		Notification notif = new Notification(1,"Test androany alakamisy",now);
		NotificationRepository repository;
		new NotificationDao().insertNotification(notif);
		System.out.println("tafa ny web atsika eh");
	}

}
