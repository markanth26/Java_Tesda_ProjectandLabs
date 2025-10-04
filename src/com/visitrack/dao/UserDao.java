/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.dao;
import com.visitrack.dbconnection.DbConnection;
import com.visitrack.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;


public class UserDao implements DAO<User> {
    public static final String RED     = "\u001B[31m";
    

    
    public Integer userId(String username){
       
        String sql = "SELECT user_id FROM tbluser WHERE username = ?";
        try (Connection conn = DbConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql);
            ){
//          System.out.println(username);   //For dataflow testing 
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()){
            if(rs.next()){
                
                return rs.getInt("user_id");
                }            
            }
        } catch (SQLException e) {
//           e.printStackTrace();
            System.out.println(RED + "Error Occured " + e.getSQLState() + e.getLocalizedMessage());
        }
        
        return null;
    }
        
    public User getUserName(int id) {
         String sql = "SELECT user_id, username FROM tbluser WHERE employee_id = ?";
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
//          System.out.println(id);     // For dataflow testing         

            pstmt.setInt(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
              
                int userId = rs.getInt("user_id");
                String username = rs.getString("username"); 
                return new User(username, userId );                     
                }
            }             
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error on fetching data");
        }
       return null;
    
    }

    
    
//-----------------------------------------------------//
//----------------MAIN LOGIN---------------------------//    
//-----------------------------------------------------//

    public User getLoginDetails(int id) {
         String sql = "SELECT employee_id, username, password_hash, role, is_active  FROM tbluser WHERE user_id = ?";
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
//         System.out.println(id);     // For dataflow testing                
            
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
              
                int employeeId = rs.getInt("employee_id");
                String userName = rs.getString("username");
                String passwordHash = rs.getString("password_hash");
                String role = rs.getString("role");
                int isActive = rs.getInt("is_active");
                return new User(employeeId, userName, passwordHash, role, isActive);                     
                }
            }             
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error on fetching data");
        }
       return null;
    }
 
//----------------------------------------------------//   
//------------------OVERRIDE METHOD-------------------//
//----------------------------------------------------//
    
    @Override
    public User get(int id) {
         String sql = "SELECT user_id, password_hash FROM tbluser WHERE user_id = ?";
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
//          System.out.println(id);     // For dataflow testing                
            pstmt.setInt(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
              
                int userId = rs.getInt("user_id");
                String passwordHash = rs.getString("password_hash"); 
                return new User(userId, passwordHash);                     
                }
            }             
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error on fetching data");
            System.out.println();
            System.out.println();
        }
       return null;
    }
                    
    @Override
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.user_id , u.username, u.role, u.email_address, " +
                 "r.employee_name " + 
                 "FROM tbluser u " +
                 "JOIN tblemployee r ON u.employee_id = r.employees_id " +
                 "WHERE u.is_active = 1 " +   
                 "ORDER BY u.user_id ASC, r.employee_name ASC";
        
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                          
            int userId = rs.getInt("user_id");
            String userName =  rs.getString("username");
            String role =   rs.getString("role");
            String emailAdd = rs.getString("email_address");
            String employeeName = rs.getString("employee_name");
            
            
            users.add(new User(employeeName, userName, role, userId, emailAdd ));                  

            }
             return users;
            }catch(SQLException e){
             e.printStackTrace();
             System.out.println("Failed to fetch user information");
             System.out.println();

             
            }
        return null;
    }
        
    @Override
    public int save(User user){
      String sql = "UPDATE tbluser SET password_reset_token = ?, is_active = 0 WHERE username = ?";
            try (Connection conn = DbConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                 
 //                System.out.println(user.getResetCode() + user.getUserName());
                
                pstmt.setString(1, user.getResetCode());
                pstmt.setString(2, user.getUserName());
               
                return pstmt.executeUpdate();
                         
             }catch(SQLException e){
                 System.out.println();
                 System.out.println(RED + "Database error: " + e.getMessage());
                 
             } 
            return 0;
    }

    @Override
    public int insert(User user){
        
        String sql = "INSERT INTO tbluser (employee_id, username, password_hash, role, email_address)"
                     + "VALUES (?, ?, ?, ?, ?)";
              
        try(Connection conn = DbConnection.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql);){
//          System.out.println(user.getPasswordHash() + " " + 
//          user.getUserId() + " " + user.getUserName() + " " + user.getRole()); // For data flow testing                                  
            String hashedPassword = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
               
            pstmt.setInt(1, user.getEmployeeId());
            pstmt.setString(2, user.getUserName().trim());
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, user.getRole().trim().toUpperCase());
            pstmt.setString(5, user.getEmailAdd().trim());
            
            
            return pstmt.executeUpdate();
        }catch(SQLException e){
            
            e.printStackTrace();
            System.out.println("Insertion Failed" + e.getMessage());
            System.out.println();
       }
        
        return 0;     
    }

    @Override
    public int update(User user){
        String sql = "UPDATE tbluser SET password_hash = ? WHERE user_id = ? AND username = ?";
             try (Connection conn = DbConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)){

//              System.out.println(user.getPasswordHash() + " " 
//               + user.getUserId() + " " + user.getUserName()); // For data flow testing                 

                String hashedPassword = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
                pstmt.setString(1, hashedPassword);
                pstmt.setInt(2,  user.getUserId());
                pstmt.setString(3, user.getUserName());
                
                return pstmt.executeUpdate();
             
             }catch(SQLException e){
              e.printStackTrace();
                 System.out.println("Error occured while updating user password!");
             }
        return 0;
    }

    @Override
    public int delete(User user) {
      String sql = "UPDATE tbluser SET is_active = 0 WHERE user_id = ? AND username = ?";
             try (Connection conn = DbConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                 
//              System.out.println(user.getUserId() +" " + user.getUserName());  // For dataflow Testing   
                pstmt.setInt(1,  user.getUserId());
                pstmt.setString(2, user.getUserName());
                
                return pstmt.executeUpdate();
             
             }catch(SQLException e){
              e.printStackTrace();
                 System.out.println("Error occured while deleting user!");
             }
        return 0;
    }
    
    
//--------------------------------------------------------------//
//----------------RESETTING PASSWORD----------------------------//
//-------------------------------------------------------------//
    
    public String resetCode(String emailAdd){
        String sql = "SELECT password_reset_token FROM tbluser WHERE email_address = ?";
        try (Connection conn = DbConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql);
            ){
            
             pstmt.setString(1, emailAdd);
            try (ResultSet rs = pstmt.executeQuery()){
            if(rs.next()){
                
                return rs.getString("password_reset_token");
                }            
            }
                   
        }catch(SQLException e){
          e.printStackTrace();
                     
        }
        
        return null;
    }    
       
    public int resetPassword(User user){
        String sql = "UPDATE tbluser SET password_hash = ?, is_active = ?, password_reset_token = null WHERE password_reset_token = ? AND email_address = ?";
             try (Connection conn = DbConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                 
//                System.out.println(user.getPasswordHash() + user.getIsActive() + user.getResetCode() + user.getEmailAdd());
                String hashedPassword = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
                pstmt.setString(1, hashedPassword);
                pstmt.setInt(2, user.getIsActive());
                pstmt.setString(3, user.getResetCode());
                pstmt.setString(4, user.getEmailAdd());  
                 
                 
              return pstmt.executeUpdate();
        
        
 
    }  catch(SQLException e){
                 e.printStackTrace();
                 System.out.println();
                 System.out.println(RED + "Invalid reset code or email address");
                 System.out.println();
     
    }
        return 0;
         
    }
    
    public int resetPasswordActiveUser(User user){
        String sql = "UPDATE tbluser SET password_hash = ?, is_active = ? password_reset_token = null WHERE email_address = ?";
             try (Connection conn = DbConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                 
                String hashedPassword = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
                pstmt.setString(1, hashedPassword);
                pstmt.setInt(2, user.getIsActive());
                pstmt.setString(3, user.getEmailAdd());  
                 
                 
              return pstmt.executeUpdate();
        
        
 
    }  catch(SQLException e){
                 System.out.println();
                 System.out.println(RED + "Invalid email address");
                 System.out.println();
     
    }
        return 0;
         
    }


}

