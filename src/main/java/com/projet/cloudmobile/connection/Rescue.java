package com.projet.cloudmobile.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Rescue {

    static String url="jdbc:postgresql://ec2-3-227-15-75.compute-1.amazonaws.com:5432/d94mdnn40hksii";
    static String user="tikvojgnnszhur";
    static String password="16c2954095f5da500573be77c53062975101443590a0fbe5cd8cccc7df2ad99d";
    
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
