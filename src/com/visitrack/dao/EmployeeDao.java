/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.dao;
import com.visitrack.dbconnection.DbConnection;
import com.visitrack.pojo.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author use
 */
public class EmployeeDao implements DAO<Employee> {
    
  
    
    
    public Integer employId(String fullName){
       
        String sql = "SELECT employees_id FROM tblemployee WHERE employee_name = ?";
        try (Connection conn = DbConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql);
            ){

//          System.out.println(fullname);   //For dataflow testing             

            pstmt.setString(1, fullName);
            try (ResultSet rs = pstmt.executeQuery()){
            if(rs.next()){
                
                return rs.getInt("employees_id");
                }            
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        
        return null;
    }
    
//--------------------------------------------------------------//   
//------------------OVERRIDE METHOD-----------------------------// 
//--------------------------------------------------------------//
          
    @Override
    public Employee get(int id){
        String sql = "SELECT employees_id, position FROM tblemployee WHERE employees_id = ?";
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
//          System.out.println(id);   //For dataflow testing             
            pstmt.setInt(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
              
                int employeeId = rs.getInt("employees_id");
                String position = rs.getString("position"); 
                return new Employee(position, employeeId);                        
                }
            }             
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error on fetching data");
        }
       return null;
    }

    @Override
    public List<Employee> getAll(){
     List<Employee> employees = new ArrayList<>();
     String sql = "SELECT employees_id, employee_name, position, contact_no FROM tblemployee";
        try (Connection conn = DbConnection.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){
            while (rs.next()) {
                int id = rs.getInt("employees_id");
                String fullName = rs.getString("employee_name");
                String position = rs.getString("position");
                String contactNo = rs.getString("contact_no");
            
                 
                employees.add(new Employee(id, fullName, position, contactNo));
        }
        
        return employees;               
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on getting employee");
        }
        
       return null;
    }

    @Override
    public int save(Employee employee) throws SQLException {
        Connection conn = DbConnection.getConnection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Employee employee){
    String sql = "INSERT INTO tblemployee (employee_name, position, contact_no)"
                + "VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)){

//          System.out.println(employee.getEmployeeName() + " " + employee.getPosition() + " " + employee.getContactNumber());   //For dataflow testing             

            pstmt.setString(1, employee.getEmployeeName());
            pstmt.setString(2, employee.getPosition());
            pstmt.setString(3, employee.getContactNumber());
            
            return pstmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        
        return 0;
    }

    @Override
   
    public int update(Employee employee){
        String sql = "UPDATE tblemployee SET position = ?, contact_no = ? WHERE employees_id = ?";
      try (Connection conn = DbConnection.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql)){

//          System.out.println(employee.getPosition() + " " + getContactNumber() + " " + employee.getEmployeeId());   //For dataflow testing             

            pstmt.setString(1, employee.getPosition());
            pstmt.setString(2, employee.getContactNumber());
            pstmt.setInt(3, employee.getEmployeeId());
            
            return pstmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        
        return 0;
    }

    @Override
    public int delete(Employee employee){
        String sql = "DELETE FROM tblemployee WHERE employees_id = ? AND employee_name = ?";
        try (Connection conn = DbConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)){

//          System.out.println(employee.getEmployeeId() + " " + employee.getEmployeeName() +);   //For dataflow testing                         
            pstmt.setInt(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getEmployeeName());

            
            return pstmt.executeUpdate();
           
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Error Occured" + e.getMessage());
        }
        
        return 0;
    }

}
