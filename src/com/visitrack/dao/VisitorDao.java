/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.dao;

import com.visitrack.dbconnection.DbConnection;
import com.visitrack.pojo.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class VisitorDao implements DAO<Visitor>{
  

    

    
    public Integer getVisitorId(String visitorName, int randomNo){
            String sql = "SELECT visit_id FROM tblvisitor WHERE visitor_name = ? AND random_no = ?";
           
             try (Connection conn = DbConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql);){
           
//          System.out.println(visitorName + " " +  randomNo );   //For dataflow testing             

            pstmt.setString(1, visitorName);
            pstmt.setInt(2, randomNo );

            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                
                return rs.getInt("visit_id");
                }            
            }
            }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching visitorId");
        }
        
        return null;
    }

    
    
//------------------------------------------------------------------//
//-----------------------UNIMPLEMENTED METHOD----------------------//
//-----------------------------------------------------------------//
                
    @Override
    public Visitor get(int id) throws SQLException {
        Connection conn = DbConnection.getConnection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      
    @Override
    public int save(Visitor visitor) throws SQLException {
        Connection conn = DbConnection.getConnection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
//-----------------------------------------------------------------//
//-------------------------ADMIN REPORT-------------------------- //
//----------------------------------------------------------------//      
    
    @Override
    public List<Visitor> getAll() {
        List<Visitor> visitors = new ArrayList<>();
        String sql = "SELECT v.visit_id, v.visitor_name, v.check_in_date, v.check_in_time, " +
                 "v.check_out_time, v.purpose_of_visit, " +
                 "r.employee_name AS receptionist_name, " +
                 "e.employee_name AS visited_name, " +
                 "e.position AS visited_position " +  
                 "FROM tblvisitor v " +
                 "LEFT JOIN tblemployee r ON v.receptionist_employee_id = r.employees_id " +
                 "LEFT JOIN tblemployee e ON v.visited_employee_id = e.employees_id " +
                 "ORDER BY v.check_in_date ASC, v.check_in_time ASC";
        
        try (Connection conn = DbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

             while (rs.next()) {
           
                int visitId = rs.getInt("visit_id");
                String visitorName = rs.getString("visitor_name");
                LocalDate checkInDate = rs.getDate("check_in_date").toLocalDate();
                String receptionistName = rs.getString("receptionist_name");
                String visitedName = rs.getString("visited_name");               
                LocalDateTime checkInTime = rs.getTimestamp("check_in_time").toLocalDateTime();
                Timestamp checkOut = rs.getTimestamp("check_out_time");
                LocalDateTime checkOutTime = (checkOut != null) ? checkOut.toLocalDateTime() : null;
                String employeePosition = rs.getString("visited_position");
                String purposeOfVisit = rs.getString("purpose_of_visit");
            
                visitors.add(new Visitor(visitId,visitorName,checkInDate,checkInTime,checkOutTime,purposeOfVisit,visitedName,receptionistName,employeePosition));
            }
             return visitors;
        } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error fetching visitor logs.");
    }

    return null;
}

    
//-----------------------------------------------------------------//
//-------------------------OVERRIDE METHOD------------------------ //
//----------------------------------------------------------------//      
 
    
    @Override
    public int insert(Visitor visitor){
         String sql =  "INSERT INTO tblvisitor(visitor_name, receptionist_employee_id, visited_employee_id, " +
                       "check_in_date, check_in_time, check_out_time, contact_no, random_no, is_active, purpose_of_visit)" 
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(Connection conn = DbConnection.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                LocalDate today = LocalDate.now();   // no java.util.Date needed
                LocalDateTime now = LocalDateTime.now();
//                System.out.println(visitor.getVisitorName()+ " " + visitor.getReceptionistEmployeeId() + " " + visitor.getVisitedEmployeeId() + " "
//                        + today + " "  + now + " " + visitor.getContactNo() + " " + visitor.getRandomNumber() + " " + visitor.getPurposeOfVisit()); // Flow Testing 
                    pstmt.setString(1, visitor.getVisitorName());
                    pstmt.setInt(2, visitor.getReceptionistEmployeeId());
                    pstmt.setInt(3, visitor.getVisitedEmployeeId());
                    pstmt.setObject(4, today);                                  // check_in_date as DATETIME
                    pstmt.setObject(5, now);                                    // check_in_time as DATETIME
                    pstmt.setObject(6, null);                                   // checkoutTime is null for check-in
                    pstmt.setString(7, visitor.getContactNo());
                    pstmt.setInt(8, visitor.getRandomNumber());
                    pstmt.setInt(9, 1);                                         // isActive = 1
                    pstmt.setString(10, visitor.getPurposeOfVisit());
                    
                    return pstmt.executeUpdate();
                
                }catch(SQLException e){
                     e.printStackTrace();
                    System.out.println("Error Occured");
                }      
           
            return 0;          
    }

    @Override
    public int update(Visitor visitor){
        String sql = "UPDATE tblvisitor SET check_out_time = ?, is_active = 0 WHERE visitor_name = ? AND visit_id = ?";
         try(Connection conn = DbConnection.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            LocalDateTime now = LocalDateTime.now();
//            System.out.println(visitor.getVisitorName() + " " + visitor.getVisitId()); // Data flow Testing
          pstmt.setObject(1, now);
          pstmt.setString(2, visitor.getVisitorName());
          pstmt.setInt(3, visitor.getVisitId());
   
         return pstmt.executeUpdate();
                
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error occured while checking out!");
        }      
           
    return 0;          
    }
    
    @Override
    public int delete(Visitor visitor){
        String sql = "DELETE FROM tblvisitor WHERE visitor_name = ? AND visit_id = ?";
       try(Connection conn = DbConnection.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql)){
           
//      System.out.println(visitor.getVisitorName() + " " + visitor.getVisitId()); // Data flow Testing

          pstmt.setString(1, visitor.getVisitorName());
          pstmt.setInt(2, visitor.getVisitId());
 
          return pstmt.executeUpdate();
                
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error occured while checking out!");
        }      
           
    return 0;          
    }

    

//-----------------------------------------------------------------//
//-------------------------RECEPTIONIST REPORT-------------------- //
//----------------------------------------------------------------//  

    public List<Visitor> activeVisitorLogs(){
         List<Visitor> visitors = new ArrayList<>();
        String sql = "SELECT v.random_no, v.visitor_name, v.check_in_date, v.check_in_time, " +
                 "v.check_out_time, v.purpose_of_visit, " +
                 "e.employee_name AS visited_name, " +
                 "e.position AS visited_position " +  
                 "FROM tblvisitor v " +
                 "LEFT JOIN tblemployee r ON v.receptionist_employee_id = r.employees_id " +
                 "LEFT JOIN tblemployee e ON v.visited_employee_id = e.employees_id " +
                 "WHERE is_active = 1 AND check_in_date = CURRENT_DATE() ORDER BY v.check_in_date ASC, v.check_in_time ASC";
        
       try (Connection conn = DbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            int randomNo = rs.getInt("random_no");
            String visitorName = rs.getString("visitor_name");
            LocalDate checkInDate = rs.getDate("check_in_date").toLocalDate();
            LocalDateTime checkInTime = rs.getTimestamp("check_in_time").toLocalDateTime();
            Timestamp checkOut = rs.getTimestamp("check_out_time");
            LocalDateTime checkOutTime = (checkOut != null) ? checkOut.toLocalDateTime() : null;
            String visitedName = rs.getString("visited_name");
            String visitedPosition = rs.getString("visited_position");
            String purposeOfVisit = rs.getString("purpose_of_visit");

            visitors.add(new Visitor(visitorName, checkInDate , checkInTime, checkOutTime, purposeOfVisit,
                                     visitedName, visitedPosition, randomNo));
        }
        return visitors;
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error fetching active visitor logs.");
    }

    return null;
} 
           
    public List<Visitor> dailyVisitorLogs(){
          List<Visitor> visitors = new ArrayList<>();
        String sql = "SELECT v.visit_id, v.visitor_name, v.check_in_date, v.check_in_time, " +
                 "v.check_out_time, v.purpose_of_visit, " +
                 "r.employee_name AS receptionist_name, " +
                 "e.employee_name AS visited_name, " +
                 "e.position AS visited_position " +  
                 "FROM tblvisitor v " +
                 "LEFT JOIN tblemployee r ON v.receptionist_employee_id = r.employees_id " +
                 "LEFT JOIN tblemployee e ON v.visited_employee_id = e.employees_id " +
                 " WHERE check_in_date = CURRENT_DATE() ORDER BY v.check_in_date ASC, v.check_in_time ASC";
        
        try (Connection conn = DbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

             while (rs.next()) {
           
                int visitId = rs.getInt("visit_id");
                String visitorName = rs.getString("visitor_name");
                LocalDate checkInDate = rs.getDate("check_in_date").toLocalDate();
                String receptionistName = rs.getString("receptionist_name");
                String visitedName = rs.getString("visited_name");               
                LocalDateTime checkInTime = rs.getTimestamp("check_in_time").toLocalDateTime();
                Timestamp checkOut = rs.getTimestamp("check_out_time");
                LocalDateTime checkOutTime = (checkOut != null) ? checkOut.toLocalDateTime() : null;
                String employeePosition = rs.getString("visited_position");
                String purposeOfVisit = rs.getString("purpose_of_visit");
            
                visitors.add(new Visitor(visitId,visitorName,checkInDate,checkInTime,checkOutTime,purposeOfVisit,visitedName,receptionistName,employeePosition));
            }
             
             return visitors;
        } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error fetching visitor logs.");
    }

    return null;
}
        
 
}
    
    
    

