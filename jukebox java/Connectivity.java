package org.example;
import java.sql.*;

public class Connectivity {
    public static Connection connect;
    public static void connectivity(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","root");
        }
        catch (Exception e){
            System.out.println("Connection error"+e);
        }
    }
}
