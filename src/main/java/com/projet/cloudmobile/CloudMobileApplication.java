package com.projet.cloudmobile;

import com.projet.cloudmobile.dao.TokenDao;
import com.projet.cloudmobile.models.Administrateur;
import com.projet.cloudmobile.models.Tokenadmin;
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
		TokenDao dao = new TokenDao();
		Administrateur administrateur = new Administrateur(1,"admin","admin");
		Tokenadmin token =dao.getTokenAdmin("c676eb098896c47353a6e029a866cd59e4d97fe5");
		dao.deleteTokenAdmin(token.getToken(),administrateur.getId());
	}

}
