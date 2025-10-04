/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.io;
import com.visitrack.dao.EmployeeDao;
import com.visitrack.dao.VisitorDao;
import com.visitrack.dao.UserDao;
import com.visitrack.pojo.Employee;
import com.visitrack.pojo.User;
import com.visitrack.pojo.Visitor;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;


public class AdminIo {
    private static final String BLUE = "\u001B[34m";
    public static final String GREEN  = "\u001B[32m";
    public static final String RESET  = "\u001B[0m";
    public static final String RED_BOLD = "\u001B[31;1m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED     = "\u001B[31m";
    
    
    private static final Logger LOGGER = Logger.getLogger(AdminIo.class.getName());
    Scanner scanner = new Scanner(System.in);

//--------------------------------------------------------------------//
//---------------------EMPLOYEE TABLE---------------------------------//
//--------------------------------------------------------------------//
    
    public void createEmployee(EmployeeDao employeeDao){
         System.out.println();
            System.out.println("======================");
            System.out.println("ENTER EMPLOYEE DETAILS");
            System.out.println("======================");
            System.out.println();
        System.out.printf(BLUE + "%-20s" ,"EMPLOYEE NAME    : ");
        String fullName = scanner.nextLine();
        System.out.printf(BLUE + "%-20s","EMPLOYEE POSITION: ");
        String position = scanner.nextLine(); 
        System.out.printf(BLUE + "%-20s", "CONTACT NUMBER   : ");
        String contactNumber = scanner.nextLine();
        
        Employee employee = new Employee(fullName,position,contactNumber);
        try {
            int rowAffected = employeeDao.insert(employee);  
        
            if(rowAffected > 0){
                System.out.println(GREEN + "\n✔ " + fullName + " successfully added!" + RESET); 
//                LOGGER.log(Level.INFO, GREEN + "Inserting employee: {0}", employee.getEmployeeName());
           
            }else{
                System.out.println(RED_BOLD + "Failed to add Employee!");
//                LOGGER.log(Level.WARNING, YELLOW + "Insert returned 0 rows for employee: {0}", GREEN + employee.getEmployeeName());
            }
        }catch(Exception e){
            System.out.println(RED_BOLD + "Error occurred while adding employee.");
//            LOGGER.log(Level.SEVERE, "Exception while inserting employee: {0}", e.getMessage());
        }                
    }
 
    public void updateEmployee(EmployeeDao employeeDao){        
           
        System.out.printf(BLUE + "%-20s", "EMPLOYEE NAME: ");
        String fullName = scanner.nextLine();
        Integer idNo = employeeDao.employId(fullName);
        System.out.println(idNo);
        try {
            if(idNo != null){        
                System.out.printf(BLUE + "%-25s", "NEW EMPLOYEE POSITION: ");
                String latestPosition = scanner.nextLine();
                System.out.printf(BLUE + "%-25s", "NEW CONTACT NUMBER   : ");
                String latestContactNo = scanner.nextLine();
                Employee employee = new Employee(idNo,latestPosition,latestContactNo);
       
                int rowAffected = employeeDao.update(employee);
                if(rowAffected > 0){
                    System.out.println(GREEN + "\n✔ " + fullName + " successfully updated!" + RESET); 
//                    LOGGER.log(Level.INFO, GREEN + "Updated employee: {0}", GREEN + employee.getEmployeeId());                    

                }else{
                     System.out.println(RED_BOLD + "Failed to update Employee!");
//                     LOGGER.log(Level.WARNING, YELLOW + "update returned 0 rows for employee: {0}", employee.getEmployeeId());
                }
             
            }else{
                System.out.println(RED_BOLD + "Employee does not exist in database!");
            }
        }catch(Exception e){
            System.out.println(RED_BOLD + "Error occurred while updating employee.");
//            LOGGER.log(Level.SEVERE, RED_BOLD + "Exception while updating employee: {0}", e.getMessage());
        }           
    }   
           
    public void deleteEmployee(EmployeeDao employeeDao){
        System.out.println();
        System.out.println("======================");
        System.out.println("ENTER EMPLOYEE DETAILS");
        System.out.println("======================");
        System.out.println();          
        System.out.printf(BLUE + "%-20s","EMPLOYEE NAME: ");
        String fullName = scanner.nextLine();        
        Integer idNo = employeeDao.employId(fullName);
        System.out.println("");
        try {       
            if(idNo != null){ 
                Employee employee = new Employee(idNo,fullName);           
                int rowAffected = employeeDao.delete(employee);           

                if(rowAffected > 0){
                    System.out.println(GREEN + "\n✔ " + fullName + " successfully deleted!" + RESET); 
//                    LOGGER.log(Level.INFO, GREEN + "delete employee: {0}",BLUE + fullName);
               
                }else{
                    System.out.println(RED_BOLD + "Failed to delete Employee!");
//                    LOGGER.log(Level.WARNING, "delete returned 0 rows for employee: {0}", fullName);
                }

            }else{
                System.out.println(RED_BOLD + "Employee does not exist in database");
            }
        }catch(Exception e){
            System.out.println(RED_BOLD + "Error occurred while deleting employee.");
//            LOGGER.log(Level.SEVERE, "Exception while deleting employee: {0}", e.getMessage());
        }  
    } 
    
    public void showEmployee(EmployeeDao employeeDao){        
        List<Employee> employee = employeeDao.getAll();
        int width = 80;
        System.out.println();
        printCentered(BLUE + "==================", width);
        printCentered(BLUE + " EMPLOYEE SUMMARY ", width);
        printCentered(BLUE + "==================", width);
        System.out.println();
        System.out.printf("%-5s | %-20s | %-25s | %-15s%n", "ID", "EMPLOYEE NAME", "POSITION", "CONTACT NO");
        System.out.println("------+----------------------+---------------------"
                       + "------+-------------"); 
        if(employee != null && !employee.isEmpty()) {
            for (Employee employ : employee) {
            System.out.printf(BLUE + "%-5d | %-20s | %-25s | %-15s%n" ,employ.getEmployeeId(), employ.getEmployeeName(), employ.getPosition(), employ.getContactNumber());
            }
        }else{
            System.out.println(RED_BOLD + "No employees found or unable to connect to the database.");
        }      
    }
                 
    public void showLoginLogs(VisitorDao visitorDao){        
        List<Visitor> visitor = visitorDao.getAll();
        int width = 130;
        System.out.println();
        printCentered(BLUE + "======================", width);
        printCentered(BLUE + " VISISTOR LOGS REPORT ", width);
        printCentered(BLUE + "======================", width); 
        System.out.println();
        System.out.printf("%-5s | %-20s | %-20s | %-15s | %-10s | %-10s | %-20s | %-20s | %-15s%n",
        "ID", "VISITOR NAME", "PURPOSE", "DATE", "TIME IN", "TIME OUT", 
        "RECEPTIONIST", "PERSON VISITED", "POSITION");
        System.out.println("------+----------------------+----------------------+-----------------+----------"
                    + "--+------------+----------------------+----------------------+---------------");

    
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        if (visitor != null && !visitor.isEmpty()) {
            for (Visitor visit : visitor) {
            String checkInDate = visit.getCheckInDate().format(dateFormatter);
            String checkInTime = visit.getCheckInTime().format(timeFormatter);
            String checkOutTime = (visit.getCheckoutTime()!= null) ? visit.getCheckoutTime().format(timeFormatter) : "-";

            System.out.printf(BLUE + "%-5d | %-20s | %-20s | %-15s | %-10s | %-10s | %-20s | %-20s | %-15s%n",
            visit.getVisitId(),
            visit.getVisitorName(),
            visit.getPurposeOfVisit(),
            checkInDate,
            checkInTime,
            checkOutTime,
            visit.getReceptionistName(),
            visit.getVisitedEmployee(),
            visit.getVisitedPosition());
            }
            }else{
            System.out.println(RED_BOLD + "No visitor logs found.");
            }
        }       
 

//----------------------------------------------------------------------//
//-----------------------USER TABLE ------------------------------------//
//----------------------------------------------------------------------//    
    
    public void createUser(UserDao userDao, EmployeeDao employeeDao){        

        System.out.println();
        System.out.println("==================");
        System.out.println("ENTER USER DETAILS");
        System.out.println("==================");
        System.out.println();              
        Employee employee = new Employee();
        System.out.printf(BLUE + "%-20s","EMPLOYEE NAME: ");
        String fullName = scanner.nextLine();
        employee.setEmployeeName(fullName);
//      String employeeName = employee.getEmployeeName();
        Integer employeeId = employeeDao.employId(employee.getEmployeeName());  

        try {
                   
            if(employeeId != null){
//                int id = employeeId;
                employee = employeeDao.get(employeeId);
                if(employee != null && (employee.getPosition().equalsIgnoreCase("Receptionist") || employee.getPosition().equalsIgnoreCase("Administrator"))){
                    System.out.printf(BLUE + "%-21s","ENTER USERNAME: ");
                    String userName = scanner.nextLine();
                    System.out.printf(BLUE + "%-21s","ENTER PASSWORD: ");
                    String password = scanner.nextLine();  
                    System.out.printf(BLUE + "%-21s","EMAIL ADDRESS : ");
                    String emailAdd = scanner.nextLine();  
                    User user = new User(employee.getEmployeeId() , userName , password , employee.getPosition(), emailAdd);                              
                    int rowAffected = userDao.insert(user);
 
                     if(rowAffected > 0){                            
                        System.out.println(GREEN + "\n✔ " + "successfully created user account for: " + fullName   + RESET); 
//                        LOGGER.log(Level.INFO, GREEN +  "Created user: {0} ", fullName);
                        }else{
//                        LOGGER.log(Level.WARNING,YELLOW + "Created returned 0 rows for user: {0} ", fullName + RESET); 
                        }
                 }else{
                    System.out.println(RED_BOLD + "Employee not allowed to be a system user.");            
            }     
            }else{
            System.out.println(RED_BOLD + "Employee " + fullName + " not found in database!");
            
            }
        }catch(Exception e){
            System.out.println(RED_BOLD + "Error occurred while creatin user.");
//            LOGGER.log(Level.SEVERE,RED_BOLD + "Exception while creating user: {0}", e.getMessage());
        }    
   }   

    public void updateUserPassword(UserDao userDao){       
        System.out.println();
        System.out.println("==================");
        System.out.println("ENTER USER DETAILS");
        System.out.println("==================");
        System.out.println();              
        System.out.printf(BLUE + "%-21s","ENTER USERNAME: ");
        String userName = scanner.nextLine();
        System.out.printf(BLUE + "%-21s","ENTER PASSWORD: ");
        String userPassword = scanner.nextLine();

        try {                        
            if(userDao.userId(userName) != null){
                   
                    int userId = userDao.userId(userName);
                    User user = userDao.get(userId);
                    boolean isMatch = BCrypt.checkpw(userPassword, user.getPasswordHash());
                    if(isMatch && user.getUserId() > 0){ 
                        System.out.printf(BLUE + "%-25s","ENTER NEW PASSWORD: ");
                        String newPassword = scanner.nextLine();
                        user = new User(userId, userName, newPassword);
                        int rowAffected= userDao.update(user);
                        if(rowAffected > 0){                                
                            System.out.println(GREEN + "\n✔ " +  GREEN + "Successfully updated your password!"); 
//                            LOGGER.log(Level.INFO, "Created User: {0}", user.getUserId());
                        } else{
                            System.out.println(RED_BOLD + "Failed to update password!");
//                            LOGGER.log(Level.WARNING,YELLOW + "create returned 0 rows for employee: {0}", user.getUserId());
                        }
                    } else {
                        System.out.println(RED_BOLD + "Invalid Password");
                    }
            }else{
                System.out.println(RED_BOLD + "Invalid username");
            }
        }catch(Exception e){
            System.out.println(RED_BOLD + "Error occurred while creating user.");
//            LOGGER.log(Level.SEVERE,RED_BOLD + "Exception while creating user: {0}", e.getMessage());
            }            
    } // In Receptionist Interface 
    
    public void deleteUser(UserDao userDao,EmployeeDao employeeDao){       
        System.out.println();
        System.out.println("==================");
        System.out.println("ENTER USER DETAILS");
        System.out.println("==================");
        System.out.println();   
        System.out.printf(BLUE + "%-20s","EMPLOYEE NAME: ");
        String fullName = scanner.nextLine();        
        Integer idNo = employeeDao.employId(fullName);
//      System.out.println(idNo); // For data flow testing
        
          
                
//      System.out.println(user); //For data flow testing
        try{
            if(idNo == null){                
                System.out.println(RED_BOLD + "No Employee " + BLUE + fullName + RED_BOLD + " found in database");                   
                }
       
            else{ 
                User user = userDao.getUserName(idNo);
                    if(user != null){
                            int rowAffected = userDao.delete(user);
                            if(rowAffected > 0){
                                System.out.println(GREEN + "\n✔ " + "Successfully deleted user for" + BLUE + fullName ); 
//                              LOGGER.log(Level.INFO,GREEN + "Deleted user: {0} ", user.getUserId());
                            }else{
                                System.out.println(RED + "Failed to delete user not Found!");
                            }
//                    LOGGER.log(Level.WARNING,YELLOW + "Deleted returned 0 rows for user: {0} ", user.getUserId());  
                    }else{
                        System.out.println(RED + "User not found in database");
                    }                          
            }
        
        }catch(Exception e){
           System.out.println(RED_BOLD + "Error occurred while deleting user.");
//           LOGGER.log(Level.SEVERE,RED_BOLD + "Exception while deleting user: {0}", e.getMessage());
        }
           
    }  
              
    public void showAllUser(UserDao userDao){
        List<User> users = userDao.getAll();
        int width = 75;
        System.out.println();
        printCentered(BLUE + "======================", width);
        printCentered(BLUE + "  SYSTEM USER SUMMARY ", width);
        printCentered(BLUE + "======================", width);
        System.out.println();
        System.out.printf("%-5s | %-20s | %-15s | %-20s | %-30s%n" , "ID", "EMPLOYEE NAME", "USERNAME", "ROLE", "EMAIL ADDRESS");      
        System.out.println("------+----------------------+-----------------+----------------------+---------------------");
                  
        
        if (users != null && !users.isEmpty()) {
            users.forEach((user) -> {
                System.out.printf(BLUE + "%-5d | %-20s | %-15s | %-20s | %-30s%n" ,
                        user.getUserId(), user.getEmployeeName(), user.getUserName(), user.getRole(), user.getEmailAdd());
            });
        }else{
            System.out.println(RED_BOLD + "no user logs found!");
        }
    }
 
 
//-----------------------------------------------------------------//    
//---------------------DASHBOARD-----------------------------------// 
//----------------------------------------------------------------//
    
    public Integer dashboardAdminChoice(){
          int space = 2;   
         int width = 80;        
         while (true) {
            spacing(space);             
            printCentered(BLUE + "=======================", width);
            printCentered(BLUE + " WELCOME ADMINISTRATOR", width);
            printCentered(BLUE + "=======================", width);
            System.out.println(); 
            System.out.println(RED_BOLD + "\t\t\t[1] " + BLUE + "CREATE EMPLOYEE");
            System.out.println(RED_BOLD + "\t\t\t[2] " + BLUE + "VIEW EMPLOYEE");
            System.out.println(RED_BOLD + "\t\t\t[3] " + BLUE + "UPDATE EMPLOYEE");
            System.out.println(RED_BOLD + "\t\t\t[4] " + BLUE + "DELETE EMPLOYEE");
            System.out.println(RED_BOLD + "\t\t\t[5] " + BLUE + "ADD USER");
            System.out.println(RED_BOLD + "\t\t\t[6] " + BLUE + "DELETE USER");
            System.out.println(RED_BOLD + "\t\t\t[7] " + BLUE + "VIEW USER");
            System.out.println(RED_BOLD + "\t\t\t[8] " + BLUE + "VIEW LOG-IN LOGS");
            System.out.println(RED_BOLD + "\t\t\t[9] " + BLUE + "CHANGE ADMIN PASSWORD");
            System.out.println(RED_BOLD + "\t\t\t[10] " + BLUE + "EXIT");
            System.out.printf(BLUE + "\t\t\t    %-3s", "SELECT CHOICE: " + BLUE);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                if (choice >= 1 && choice <= 10) {
                return choice; // valid choice, return immediately
                } else {
                    System.out.println();
                    spacing(space);
                    System.out.println(RED + "\t\t\t\t⚠️ Please select a valid option (1–10).");
                    
                }
                

            } catch (InputMismatchException e) {
                System.out.println();
                spacing(space);
                System.out.println(RED + "\t\t\t\t⚠️ Enter a valid number!");
                
                scanner.nextLine(); // clear buffer
        }
    }
         
         
}

 
//-----------------------------------------------------------------//    
//---------------------SPACING AND INDENTION---------------------- // 
//----------------------------------------------------------------//

    public void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            System.out.printf("%" + (padding + text.length()) + "s%n", text);
        } else {
            System.out.println(text); 
        }
    }
    
    public static void spacing(int space){
        
        for(int i = 0; i <= space; i++){
            System.out.println();
                       
        }        
    }
       
    
}    



















//String username = "mark";
//String newPassword = 
//
//Visitor visitor = new Visitor(username, newPassword);
//visitorDAO.update(visitor);
//String sql = "UPDATE users SET user_password = ? WHERE username = ?";
//preparedStatement.setString(1, visitor.getPassword());
//preparedStatement.setString(2, visitor.getUsername());
