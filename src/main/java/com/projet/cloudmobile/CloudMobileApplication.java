package com.projet.cloudmobile;

import com.projet.cloudmobile.dao.SignalementDao;
import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.dao.TokenUserDao;
import com.projet.cloudmobile.dao.UtilisateurDao;
import com.projet.cloudmobile.models.Administrateur;
import com.projet.cloudmobile.models.Tokenadmin;
import com.projet.cloudmobile.models.Utilisateur;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;

//@SpringBootApplication
@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class CloudMobileApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CloudMobileApplication.class, args);
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");

	}

}
