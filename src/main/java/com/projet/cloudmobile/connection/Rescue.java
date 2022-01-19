package com.projet.cloudmobile.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Rescue {
    static String url="jdbc:postgresql://ec2-3-224-157-224.compute-1.amazonaws.com:5432/d6nonnvm58ivpd";
    static String user="uojpoqhycqlmne";
    static String password="b9b7d3b5bc58e3f4425e814e7326dfd6fee7bef2c517a9210ea86b9325883e77";

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
