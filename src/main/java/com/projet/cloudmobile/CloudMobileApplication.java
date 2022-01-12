package com.projet.cloudmobile;

import com.projet.cloudmobile.dao.*;
import com.projet.cloudmobile.models.*;
import com.projet.cloudmobile.models.TypeSignalement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class CloudMobileApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(CloudMobileApplication.class, args);
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory( "connection");

		//Signalement s = new Signalement(new TypeSignalement(1,"Test"), new Utilisateur(new Long(1)), new Date("2022-01-06"),"Description",47.516667,-18.933333);

		//Utilisateur u = new Utilisateur("username","password","lol@lol.com",new Date("2001-11-19"));
		//TypeSignalement t = new TypeSignalement("Accident de la route");
		/*Administrateur admin = new Administrateur();
		admin.setId(1);
		admin.setIdentifiant("Administrator");
		admin.setMotDePasse("admin1");*/
		/*AdministrateurDao dao = new AdministrateurDao();
		dao.initAdministrator1();
		dao.initAdministrator2();*/

		/*String dateInString = "09/01/2022";
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(dateInString);


		SignalementDao dao = new SignalementDao();
		Signalement s = new Signalement(new Long(1),new TypeSignalement(1,"Test"), new Utilisateur(new Long(1)), date,"Modification ok",47.516667,-18.933333);
		dao.insert(s);*/

		//TypeSignalement tt = new TypeSignalement(2,"Route en mauvais état");
		//TypeSignalementDao dao = new TypeSignalementDao();

		//dao.remove(3);

		Signalement s = new SignalementDao().getSignalement(1L);
		System.out.println(s.getDescription());

	}

}
