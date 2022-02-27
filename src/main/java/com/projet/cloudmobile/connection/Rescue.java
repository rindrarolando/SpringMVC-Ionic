package com.projet.cloudmobile.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Rescue {

    static String url="jdbc:postgresql://ec2-3-224-157-224.compute-1.amazonaws.com:5432/dcpib1t8jth58k";
    static String user="cfkbextluneeyz";
    static String password="cd54ebb16c7b0a98d9b07698c56fdf83d17a4f120985bc4339dde5455ebc8b9d";
    
    public static Connection connectToDatabase(){
        try{
            Connection c = null;
            Statement stmt ;
            c = DriverManager.getConnection(url,user,password);
            return c;
        }catch(Exception e){
            return null;
        }
    }
}
