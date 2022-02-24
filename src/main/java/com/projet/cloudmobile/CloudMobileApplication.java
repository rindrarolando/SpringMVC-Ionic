package com.projet.cloudmobile;

import com.projet.cloudmobile.dao.NotificationDao;
import com.projet.cloudmobile.models.Notification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import java.time.LocalDateTime;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class CloudMobileApplication{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CloudMobileApplication.class, args);
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");

	}

}
