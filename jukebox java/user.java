package org.example;

import java.sql.*;

public class user {

public user(){
Connectivity.connectivity();
}

public void registerNewUser(String username,String password){

    try(PreparedStatement ps=Connectivity.connect.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")){
ps.setString(1,username);
ps.setString(2,password);
int roweffected=ps.executeUpdate();
        if(roweffected>0)
        {
            System.out.println("Registereg Successfully");
        }

    }
    catch (SQLException e){
        System.out.println("registration failed"+e.getMessage());
    }
}
public boolean userExists(String username){
    try (PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT username FROM users WHERE username = ?")) {
        ps.setString(1, username);
        ResultSet resultSet = ps.executeQuery();
        return resultSet.next();
    } catch (SQLException e) {
        System.out.println("Error checking user existence"+e.getMessage());
    }
    return false;
}

}
