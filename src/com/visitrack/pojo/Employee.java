/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.pojo;


public class Employee {
    
    private Integer employeeId;
    private String employeeName;
    private String position;
    private String contactNumber;
    
    public Employee(){        
    } 
    
    public Employee(Integer employeeId, String employeeName, String position, String contactNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.contactNumber = contactNumber;
    }

    public Employee(Integer employeeId, String position, String contactNumber) {
        this.employeeId = employeeId;
        this.position = position;
        this.contactNumber = contactNumber;
    }
    
    
    public Employee(String employeeName, String position, String contactNumber) {
        
        this.employeeName = employeeName;
        this.position = position;
        this.contactNumber = contactNumber;
    }

    public Employee(Integer employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }
 
    public Employee(String position, Integer employeeId) {
        this.employeeId = employeeId;
        this.position = position;
    }
   
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

     
    public int getEmployeeId(){
        
        return employeeId;
    }
    
     public String getEmployeeName(){
        
        return employeeName;
    }
     public String getPosition(){
        
        return position;
    }

    public String getContactNumber() {
        return contactNumber;
    }
     
     
     @Override
    public String toString() {
        return "Employee{" + "employeeName : " + employeeName + ", position :" + position + '}';
    }

    
    
    
}
