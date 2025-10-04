/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.pojo;


public class User {

    private int userId;
    private int employeeId;
    private String userName;
    private String passwordHash;
    private String role;
    private int isActive;
    private String employeeName;
    private String emailAdd;
    public String resetCode;
    
    public User(){        
    }

    public User(int employeeId, String username, String passwordHash, String role, int isActive) {
        this.employeeId = employeeId;
        this.userName = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isActive = isActive;
    }
        
    public User(int employeeId, String userName, String passwordHash, String role, String emailAdd) {
       
        this.employeeId = employeeId;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.role = role;
        this.emailAdd = emailAdd;
    }
 
    public User( String employeeName, String userName, String role,int userId, String emailAdd ) {
        this.employeeName = employeeName;
        this.userName = userName;
        this.role = role;
        this.userId = userId;
        this.emailAdd = emailAdd;
    }

    public User(String passwordHash, int isActive, String emailAdd, String resetCode, String userName) {
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.emailAdd = emailAdd;
        this.resetCode = resetCode;
        this.userName = userName;
    }
    
    public User(String userName, String passwordHash, String role) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.role = role;
    }
        
    public User(int userId, String userName, String passwordHash) {
        this.userId = userId;
        this.userName = userName;
        this.passwordHash = passwordHash;

    }

    public User(String passwordHash, int isActive, String emailAdd) {
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.emailAdd = emailAdd;
    }
    
    public User(String userName, int userId) {
        this.userId = userId;
        this.userName = userName;
        
    }
      
    public User(int userId, String passwordHash) {
        this.userId = userId;
        this.passwordHash = passwordHash;
        
    }

    public User(String username, String resetCode) {
        this.userName = username;
        this.resetCode = resetCode;
    }
    
    public int getUserId() {
        return userId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public String getResetCode() {
        return resetCode;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", employeeId=" + employeeId + ", userName=" + userName + ", role=" + role + ", isActive=" + isActive + ", employeeName=" + employeeName + ", emailAdd=" + emailAdd + ", resetCode=" + resetCode + '}';
    }
  
    
   
     
}
