/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.dbconnection;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DbConnection {
    
  public static Connection getConnection() throws SQLException{
        Properties props = new Properties();

        // Load config.properties from the same folder as the JAR
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        }catch (IOException e){
        throw new RuntimeException("Failed to load config.properties", e);
    }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }  
    

    
    
    
//    private static final String URL = "jdbc:mysql://localhost:3306/visitorLogin";
//    private static final String USER = "mark-lab";
//    private static final String PASSWORD = "mj02262012";
//    
//    public static Connection getConnection()throws SQLException{
//                
//       Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);           
//        return connect;
//        }   
// 
//    
    
    
    
//    public static void main(String[] args) {
//        
//        try(Connection conn = getConnection();){
//            
//            System.out.println("Connected");
//        }catch(SQLException e){
//            System.out.println("Connection failed");
//            
//        }
//   
//    }
    
}  
  
