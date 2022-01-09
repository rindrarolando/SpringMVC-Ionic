package com.projet.cloudmobile;

import com.projet.cloudmobile.dao.AdministrateurDao;
import com.projet.cloudmobile.models.Administrateur;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class CloudMobileApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CloudMobileApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");

		//Signalement s = new Signalement(new TypeSignalement(1,"Test"), new Utilisateur(new Long(1)), new Date("2022-01-06"),"Description",47.516667,-18.933333);

		//Utilisateur u = new Utilisateur("username","password","lol@lol.com",new Date("2001-11-19"));
		//TypeSignalement t = new TypeSignalement("Accident de la route");
		/*Administrateur admin = new Administrateur();
		admin.setId(1);
		admin.setIdentifiant("Administrator");
		admin.setMotDePasse("admin1");*/
		AdministrateurDao dao = new AdministrateurDao();
		dao.initAdministrator1();
		dao.initAdministrator2();

	}

}
