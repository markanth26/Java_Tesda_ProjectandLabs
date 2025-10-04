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
import java.util.InputMismatchException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class RevisionMain {
    private static final Logger LOGGER = Logger.getLogger(RevisionMain.class.getName());
    public static final String RED     = "\u001B[31m";   
    public static final String BLUE = "\u001B[34m";
    public static final String ITALIC = "\u001B[3m";
    public static final String RESET = "\u001B[0m";
    
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
      
        int width = 85;
        int spacing = 3;
        VisitorDao visitorDao = new VisitorDao();
        EmployeeDao employeeDao = new EmployeeDao();
        User user = new User();
        UserDao userDao = new UserDao();
        ReceptionistIo receptionist = new ReceptionistIo();
        AdminIo admin = new AdminIo();
        Header.printHeader();
        spacing(spacing);
        
         int count = 1;
        do{     
            System.out.printf(BLUE +"%-21s","\t\t\t\tENTER USERNAME:");
            String userName = scanner.nextLine();
            System.out.printf(BLUE +"%-21s","\t\t\t\tENTER PASSWORD:");
            String password = scanner.nextLine();
           
            try {
                  
                    Integer userId = userDao.userId(userName);
                    user = userDao.getLoginDetails(userId);      
                    if(userId != null && user.getIsActive() == 1){
                        boolean isMatch = BCrypt.checkpw(password, user.getPasswordHash());
                        if(isMatch && user.getRole().equals("RECEPTIONIST")){
                            boolean isLogin = true; 
                            HashSet<Integer> random = new HashSet<>();
                            Random number = new Random();
                            while(true){                       
                                System.out.println();
                                int employeeId = user.getEmployeeId();
//                              System.out.println(employeeId);
                                int choice =  receptionist.dashboardReceptionistChoice();
                                    switch(choice){
                                        case 1:
                                            int codeNumber;
                                            do{
                                            codeNumber = number.nextInt(9000)+1000;
                                            }while(!random.add(codeNumber));
                                            receptionist.loginVisitor(employeeId, codeNumber, visitorDao, employeeDao);                      
                                            break;

                                        case 2:
                                            receptionist.checkOutVisitor(visitorDao);
                                            break;                                    

                                        case 3:
                                            receptionist.deleteVisitor(visitorDao);
                                            break;

                                        case 4:
                                            receptionist.showActiveVisitorLogs(visitorDao);
                                            break;

                                        case 5:
                                            receptionist.receptionistDailyReport(visitorDao);
                                            break;    
 
                                        case 6:
                                            admin.updateUserPassword(userDao);
                                            break;

                                        case 7:
                                            System.out.println();
                                            printCentered(RED + "LOGGING OUT", 80);
                                            scanner.close();
                                             System.exit(0);                      
                            }
                        }
                        }else if(isMatch && user.getRole().equals("ADMINISTRATOR")){
                            System.out.println();
                            boolean isFinish = false;
                            while(true){
                                spacing(4);
                                int choice = admin.dashboardAdminChoice();
//                      
                                switch(choice){
                                
                                    case 1:
                                        admin.createEmployee(employeeDao);                              
                                        break;

                                    case 2: 
                                        admin.showEmployee(employeeDao);
                                        break;

                                    case 3:
                                        admin.updateEmployee(employeeDao);
                                        break;

                                    case 4: 
                                        admin.deleteEmployee(employeeDao);
                                        break;
 
                                    case 5:
                                        admin.createUser(userDao, employeeDao);
                                        break;
                                    case 6: 
                                        admin.deleteUser(userDao, employeeDao);
                                        break;

                                    case 7:
                                        admin.showAllUser(userDao);
                                        break;
                                    case 8: 
                                        admin.showLoginLogs(visitorDao);                                
                                        break;

                                    case 9:
                                        admin.updateUserPassword(userDao);
                                        break;

                                    case 10: 
                                        System.out.println();
                                        printCentered(RED + "LOGGING OUT", 80);
                                        scanner.close();
                                        System.exit(0);  
                                }
                            }
                    }else{
                        System.out.println();
                        System.out.println(RED + "Invalid Credentials");
                      
                        count ++;
                        }
                    }else{
                        System.out.println();
                        printCentered(RED + "Locked Account", 80);
                        System.out.println();
//                        LOGGER.log(Level.WARNING,RED + "Account locked due to multiple failed login attempts for username: {0}", user.getUserName());
                        count = 4;
                    } 
                
                }catch (NullPointerException e) {
                    
                     System.out.println();
                     System.out.println(RED + "Invalid Credentials");
//                    LOGGER.log(Level.SEVERE, RED + "Exception while logging in: {0} ", e.getMessage() );
                    count++;
                }
                            
            }while(count < 4);
                    
                    
                    boolean isCorrect = false; 
                    Random number = new Random();
                    String code = "RS";  
                    
                    do{
//                        String resetNumber = String.valueOf(number.nextInt(5000) + 2000);
//                        String resetCode = code.concat("").concat(resetNumber);
////                        System.out.println(user.getUserName());
//                        user = new User(user.getUserName(), resetCode );
////                      System.out.println(user);
//                        int rowAffected = userDao.save(user);
//                
                    System.out.println();        
                    printCentered(RED + "SEVERAL FAIL LOGIN ATTEMPTS" +  RESET + "" , width);
                    printCentered(BLUE + " PLEASE RESET YOUR PASSWORD" + RESET + "" , 82);
                    printCentered("-------------------------", 74);
                    printCentered(RED + "[1]" + BLUE + " RESET YOUR PASSWORD" , width); 
                    printCentered(RED + "[2]" + BLUE + " EXIT", 70);
                    System.out.print(BLUE + "\t\t\t   ENTER CHOICE: " + RESET);
                        try {
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        String resetNumber = String.valueOf(number.nextInt(5000) + 2000);
                        String resetCode = code.concat("").concat(resetNumber);
//                        System.out.println(user.getUserName());
                        user = new User(user.getUserName(), resetCode );
//                      System.out.println(user);
                        int rowAffected = userDao.save(user);
                       switch(choice){
                            case 1:
                                if(rowAffected == 1){
                                    System.out.println();
                                    System.out.println("===================");
                                    System.out.println(BLUE + " ENTER RESET INFO:");
                                    System.out.println("=================== ");
                                    System.out.println();         
                                    System.out.print(BLUE + "EMAIL ADDRESS   : ");       
                                    String emailAdd = scanner.nextLine();  
                                    System.out.println();
                                    System.out.println(BLUE + "RESET CODE : " + RED + user.getResetCode());
                                    System.out.println();
                                    System.out.print(BLUE + "ENTER RESET CODE: ");
                                    String resetCodeInput = scanner.nextLine();
                                    System.out.print(BLUE + "NEW PASSWORD    : ");
                                    String password = scanner.nextLine();
                                    user = new User(password, 1, emailAdd, resetCodeInput, user.getUserName());
//                                    System.out.println(user);
                                    rowAffected = userDao.resetPassword(user);

                                            if(rowAffected == 1){
                                            System.out.println();
                                            System.out.println("Successfully Reset you password you can now login to your account.");
                                            isCorrect = true;
                                        }else{
                                             System.out.println();
                                             System.out.println(RED + "Not a valid email address or reset code mismatch");                                    
                                        }     
                                }else{
                                    resetNumber = String.valueOf(number.nextInt(5000) + 2000);
                                    resetCode = code.concat("").concat(resetNumber);
                                    System.out.println("====================");
                                    System.out.println(BLUE +   "ENTER RESET INFO:");
                                    System.out.println("==================== ");
                                    System.out.print(BLUE + "EMAIL ADDRESS:    ");
                                    String emailAdd = scanner.nextLine();
                                    System.out.println(BLUE + "RESET CODE: " + RED + resetCode);
                                    System.out.print(BLUE + "ENTER RESET CODE : ");
                                    String resetCodeInput = scanner.nextLine();                               
                                                             
                                    if(resetCode.equalsIgnoreCase(resetCodeInput)){
                                        System.out.print(BLUE + "NEW PASSWORD     : ");
                                        String password = scanner.nextLine();
                                        user = new User(password, 1, emailAdd );
                                        rowAffected = userDao.resetPasswordActiveUser(user);
                                            if(rowAffected == 1){
                                                System.out.println();
                                                System.out.println("Successfuly Reset you password you can now login to your account.");
                                                isCorrect = true;
                                            }else{
                                                System.out.println();
                                                System.out.println(RED + "Not a valid email address");
                                            }                                       
                                    }else{
                                        System.out.println();
                                        System.out.println(RED + "Please input correct reset code");                                       
                                    }                                                                 
                                }                           
                            break;

                            case 2:
                                scanner.close();
                                System.exit(0);
                            default:
                                System.out.println();
                                System.out.println("Please select valid choice");
                                break;
                       }
                    }catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Enter A valid number" + e.getMessage());
//                        LOGGER.log(Level.SEVERE, RED + "Exception while resetting in: {0} ", e.getMessage() );
                    }                             
      
              }while(!isCorrect);
    }         

    
 
    
 
  
    
    
    

    
    
    
    
    
//----------------------------------------------------------------//   
//----------------------SPACING AND INDENTION----------------------//
//-----------------------------------------------------------------//

  
    
    
    
    
    
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
