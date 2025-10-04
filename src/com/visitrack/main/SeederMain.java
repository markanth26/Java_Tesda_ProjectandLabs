/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.main;

import com.visitrack.dao.EmployeeDao;
import com.visitrack.dao.UserDao;
import com.visitrack.dao.VisitorDao;
import com.visitrack.io.ReceptionistIo;
import com.visitrack.io.AdminIo;
import com.visitrack.pojo.User;

        
import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.mindrot.jbcrypt.BCrypt;



public class SeederMain {
//    private static final Logger LOGGER = Logger.getLogger(ProjectMain.class.getName());
    public static final String RED     = "\u001B[31m";   
    public static final String BLUE = "\u001B[34m";
    public static final String ITALIC = "\u001B[3m";
    
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        
        int width = 85;
        int spacing = 3;
        VisitorDao visitorDao = new VisitorDao();
        EmployeeDao employeeDao = new EmployeeDao();
        UserDao userDao = new UserDao();
        ReceptionistIo receptionist = new ReceptionistIo();
        AdminIo admin = new AdminIo();
        
       try{
            admin.createUser(userDao, employeeDao);
            System.out.println("Successfuly Created");
       }catch(Exception e){
           System.out.println(e.getMessage());
       }

//        boolean isLogin = false;
//            Header.printHeader();
//            spacing(spacing);
//        do{
//                    
//        System.out.printf(BLUE +"%-21s","\t\t\t\tENTER USERNAME:");
//        String userName = scanner.nextLine();
//        System.out.printf(BLUE +"%-21s","\t\t\t\tENTER PASSWORD:");
//        String password = scanner.nextLine();
//                
//        try{ 
//            Integer userId = userDao.userId(userName);
//            User user = userDao.getLoginDetails(userId);      
//            if(userId != null && user.getIsActive() == 1){
//                boolean isMatch = BCrypt.checkpw(password, user.getPasswordHash());
//                if(isMatch && user.getRole().equals("RECEPTIONIST")){
//                    isLogin = true; 
//                    HashSet<Integer> random = new HashSet<>();
//                    Random number = new Random();
//                    while(true){                       
//                        System.out.println();
//                        int employeeId = user.getEmployeeId();
////                        System.out.println(employeeId);
//                        int choice =  receptionist.dashboardReceptionistChoice();
//                            switch(choice){
//                                case 1:
//                                    int codeNumber;
//                                    do{
//                                        codeNumber = number.nextInt(9000)+1000;
//                                    }while(!random.add(codeNumber));
//                                    receptionist.loginVisitor(employeeId, codeNumber, visitorDao, employeeDao);
//                                    break;
//                                case 2:
//                                    receptionist.checkOutVisitor(visitorDao);
//                                    break;                                    
//                                case 3:
//                                    receptionist.deleteVisitor(visitorDao);
//                                    break;
// 
//                                case 4:
//                                    receptionist.showActiveVisitorLogs(visitorDao);
//                                    break;
//                                
//                                case 5:
//                                    receptionist.receptionistDailyReport(visitorDao);
//                                    break;
//                                
//                                case 6:
//                                    admin.updateUserPassword(userDao);
//                                    break;
//
//                                case 7:
//                                    System.out.println();
//                                    printCentered(RED + "LOGGING OUT", 80);
//                                    scanner.close();
//                                    System.exit(0);                      
//                            }
//                   }
//                }else if(isMatch && user.getRole().equals("ADMINISTRATOR"))  {
//                    
//                    int employeeId = user.getEmployeeId();
//                    System.out.println(employeeId);
//                    System.out.println();
//                    boolean isFinish = false;
//                    while(true){
//                    int choice = admin.dashboardAdminChoice();
//                    System.out.println(choice);   
//                        switch(choice){
//                                
//                            case 1:
//                                admin.createEmployee(employeeDao);                              
//                                break;
//                            case 2: 
//                                admin.showEmployee(employeeDao);
//                                break;
//                            case 3:
//                                admin.updateEmployee(employeeDao);
//                                break;
//                            case 4: 
//                                admin.deleteEmployee(employeeDao);
//                                break;
//                            case 5:
//                                admin.createUser(userDao, employeeDao);
//                                break;
//                            case 6: 
//                                admin.deleteUser(userDao, employeeDao);
//                                break;
//                            case 7:
//                                admin.showAllUser(userDao);
//                                break;
//                            case 8: 
//                                admin.showLoginLogs(visitorDao);                                
//                                break;
//                            case 9:
//                                admin.updateUserPassword(userDao);
//                                break;
//                            case 10: 
//                                System.out.println();
//                                printCentered(RED + "LOGGING OUT", 80);
//                                scanner.close();
//                               System.exit(0);  
//                            }
//                    }                            
//                }else{
//                    System.out.println();
//                    receptionist.printCentered(RED + "Invalid Password", width);
//                    System.out.println();
//                }             
//        }else {
//            
//            System.out.println("Invalid Username");           
//        }
//         }catch(Exception e){
//             System.out.println();
//            receptionist.printCentered(RED + "Invalid Username", width);
//            System.out.println();
//            LOGGER.log(Level.SEVERE, "Exception while login: {0}", e.getMessage());          
//         }                        
//        }
//        while(!isLogin);            
}
          


        


            
//        
//        System.out.println(user);
//        
       
        
    

    
    
    
    
    
    
    
    
    
    
 
 
    
    

      // Helper method
    public static void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            System.out.printf("%" + (padding + text.length()) + "s%n", text);
        } else {
            System.out.println(text); 
        }
    }
    
    public static void spacing(int spacing){
     for(int i = 0; i < spacing; i++){
         System.out.println();
        
    }
  }
}


