package com.projet.cloudmobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;

//@SpringBootApplication
@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class CloudMobileApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(CloudMobileApplication.class, args);
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");
	}

}
