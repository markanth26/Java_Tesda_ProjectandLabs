/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.io;
import com.visitrack.dao.VisitorDao;
import com.visitrack.dao.EmployeeDao;
import com.visitrack.pojo.Visitor;
import com.visitrack.dao.UserDao;
import com.visitrack.pojo.User;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
 
public class ReceptionistIo {
    public static int width = 90;
    public static final String DARKBLUE = "\u001B[38;5;19m";
    public static final String ORANGE = "\u001B[38;5;208m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String ITALIC = "\u001B[3m";
    private static final String BLUE = "\u001B[34m";
    public static final String GREEN  = "\u001B[32m";
    public static final String RESET  = "\u001B[0m";
    public static final String RED_BOLD = "\u001B[31;1m";
    public static final String RED     = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    
  
    

    private static final  DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final  DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm");    
    private static final Logger LOGGER = Logger.getLogger(ReceptionistIo.class.getName());
    static Scanner scanner = new Scanner(System.in);
 

//---------------------------------------------------------------------------//
//----------------------RECEPTIONIST-----------------------------------------//         
//--------------------------------------------------------------------------// 

    
    public void loginVisitor(Integer receptionistEmployeeId, int visitCode,  VisitorDao visitorDao, EmployeeDao employeeDao){        
//          System.out.println("EMPLOYEE ID : " + receptionistEmployeeId + " " +"VISIT CODE: " +  visitCode);     
          if(receptionistEmployeeId != null){
            System.out.println();
            System.out.println("===================");
            System.out.println(BLUE + "ENTER LOGIN DETAILS");
            System.out.println("===================");
            System.out.println();
            System.out.printf(BLUE + "%-22s","VISITOR NAME    : ");
            String visitorName = scanner.nextLine();
            System.out.printf(BLUE + "%-22s","PURPOSE OF VISIT: ");
            String purposeVisit = scanner.nextLine(); 
            System.out.printf(BLUE + "%-22s","CONTACT NUMBER  : ");
            String contactNumber = scanner.nextLine();         
            try {
              
            do{
                 
                System.out.printf(BLUE + "%-22s","VISITED EMPLOYEE: ");
                String visitedEmployee = scanner.nextLine();
                Integer visitedEmployeeId = employeeDao.employId(visitedEmployee);

                if(visitedEmployeeId != null){
                                     
                    Visitor visitor = new Visitor(visitorName,receptionistEmployeeId, visitedEmployeeId, purposeVisit, contactNumber, visitCode);
                    int rowAffected = visitorDao.insert(visitor);
                    if(rowAffected > 0){ 
                        System.out.println();                
                        System.out.println(GREEN + "Successfully Check-in Visitor!");
                        System.out.println(BLUE + "VISIT CODE: " + RED + visitCode);
//                        LOGGER.log(Level.INFO, "check-in success: {0}", visitorName );    
                                                       
                    }else{
                        System.out.println();
                         System.out.println("Failed Check-in Visitor!");
//                         LOGGER.log(Level.WARNING, "Check-in returned 0 rows for visitor: {0}", visitorName );
                        }
                break;

                }else{
                        boolean isChoice = false; 
                   
                    
                    System.out.println();                  
                    System.out.println(RED + "Employee to visit does not exist");
                     do{
                    System.out.println(RED + "[1]" + BLUE + "ENTER EMPLOYEE NAME AGAIN:");
                    System.out.println(RED + "[2]" + BLUE + "EXIT");
                    System.out.print("Enter Choice: ");
                    int choice = -1;
                    try{
                      choice = scanner.nextInt();
                      scanner.nextLine();
                    }catch(InputMismatchException e){
                      scanner.nextLine();
                      System.out.println(); 
                      System.out.println(RED + "Kindly input valid number: " + e.getMessage());
                    }                      
                       if(choice == 1){
                         isChoice = true;                       
                       }
                       else if(choice == 2){
                        return;  
                        }else{
                        System.out.println(); 
                        System.out.println("Invalid choice");                             
                       }  
                                      
//                        switch(choice){
//                                                       
//                            case 1:
//                                isChoice = true;
//                                break;
//                            case 2:
//                                return;                       
//                                default:
//                                System.out.println(RED + "Invalid choice Only 1 and 2!");
//                                                      
//                         }    
                    }while(!isChoice); 
                   }
                    
                }while(true);  

            }catch(Exception e){
                System.out.println(); 
                System.out.println(RED + "Error occurred while adding employee.");
//                LOGGER.log(Level.SEVERE, "Exception while inserting employee: {0} ", e.getMessage());
            }                
            }else{
                System.out.println(); 
                System.out.println(RED + "Invalid Receptionist Id");
            }
                    
    }
      
    public void checkOutVisitor(VisitorDao visitorDao){
        System.out.println();
        System.out.println("=======================");
        System.out.println(BLUE + "ENTER CHECK OUT DETAILS");
        System.out.println("======================= ");
        System.out.println();       
        System.out.printf(BLUE + "%-18s", "VISITOR CODE: ");          
       try{
        int visitorCode = scanner.nextInt();
        scanner.nextLine();
        System.out.printf(BLUE + "%-18s","VISITOR NAME: ");
        String visitorName = scanner.nextLine();           
        Integer visitorId = visitorDao.getVisitorId(visitorName, visitorCode);
                 
                if(visitorId != null){        
                    Visitor visitor = new Visitor(visitorId, visitorName);
                    int rowAffected = visitorDao.update(visitor);
                      
                    if(rowAffected > 0){
                    System.out.println(GREEN + " Successfully Checked out!"); 
//                    LOGGER.log(Level.INFO,GREEN + "Check-out Visitor: {0} ", visitor.getVisitId());                    

                    }else{
                    System.out.println(RED + "Failed to Checked-out Visitor!");
//                    LOGGER.log(Level.WARNING,YELLOW + "Check-out returned 0 rows for employee: {0} ", visitor.getVisitId());
                    }
             
                }else{
                    System.out.println(RED + "Visitor "+ BLUE + visitorName + RED + " does not exist in database or incorrect visitor code!");
                }
            
        }catch(Exception e){
            System.out.println(RED + "Enter valid visitor code error occurred while checking out.");
//            LOGGER.log(Level.SEVERE, "Exception while checking out: {0} ", e.getMessage());
        }           
    }   

    public void deleteVisitor(VisitorDao visitorDao){ 
        
        System.out.println();
        System.out.println("=====================");
        System.out.println(BLUE + "ENTER VISITOR DETAILS");
        System.out.println("===================== ");
        System.out.println();         
        System.out.printf(BLUE + "%-18s","VISITOR CODE: ");  
        try {
        int visitorCode = scanner.nextInt();  
        scanner.nextLine();        
        System.out.printf(BLUE + "%-18s","VISITOR NAME: ");
        String visitorName = scanner.nextLine();           
        Integer visitorId = visitorDao.getVisitorId(visitorName, visitorCode);
         
            if(visitorId != null){        
                Visitor visitor = new Visitor(visitorId, visitorName);
                int rowAffected = visitorDao.delete(visitor);
                      
                if(rowAffected > 0){
                    System.out.println();
                    System.out.println(GREEN + " Successfully Delete Visitor!"); 
//                    LOGGER.log(Level.INFO,GREEN + "Delete Visitor: {0} ", visitor.getVisitId());                    

                }else{
                    System.out.println();
                     System.out.println(RED + "Failed to Delete Visitor!");
//                     LOGGER.log(Level.WARNING,YELLOW + "Check-out returned 0 rows for employee: {0} ", visitor.getVisitId());
                }
             
            }else{
                System.out.println();
                System.out.println(RED + "Visitor "+ BLUE + visitorName + RED + " does not exist in database!");
            }
        }catch(Exception e){
            System.out.println();
            System.out.println(RED + "Enter valid visitor code error occurred while deleting.");
//            LOGGER.log(Level.SEVERE,RED + "Exception while deleting: {0} ", e.getMessage());
        }           
    } 
    
    public void showActiveVisitorLogs(VisitorDao visitorDao){ 

        List<Visitor> visitor = visitorDao.activeVisitorLogs();
        System.out.println();
        printCentered(BLUE + "==========================", width);
        printCentered(BLUE + "CURRENTLY ACTIVE VISITORS", width);
        printCentered(BLUE + "==========================", width);
        System.out.println();
        System.out.printf("%-12s | %-20s | %-20s | %-12s | %-8s | %-8s | %-20s | %-15s%n",
        "VISITOR CODE", "VISITOR NAME", "PURPOSE", "DATE", "TIME IN", "TIME OUT", 
        "PERSON VISITED", "POSITION");
        System.out.println("-------------+----------------------+----------------------+--------------+----------+----------+----------------------+----------------");

        if (visitor != null && !visitor.isEmpty()) {
            visitor.forEach((visit) -> {
            String checkInDate = visit.getCheckInDate().format(DATEFORMATTER);
            String checkInTime = visit.getCheckInTime().format(TIMEFORMATTER);
            String checkOutTime = (visit.getCheckoutTime()!= null) ? visit.getCheckoutTime().format(TIMEFORMATTER) : "-";
                
            System.out.printf(BLUE + "%-12d | %-20s | %-20s | %-12s | %-8s | %-8s | %-20s |%-15s%n",
                visit.getRandomNumber(),
                visit.getVisitorName(),
                visit.getPurposeOfVisit(),
                checkInDate,
                checkInTime,
                checkOutTime,
                visit.getVisitedEmployee(),
                visit.getVisitedPosition());
                });
        }else{
            System.out.println();
            System.out.println(RED + "No visitor logs found.");
        }
    }       
   
    public void receptionistDailyReport(VisitorDao visitorDao){        
        List<Visitor> visitor = visitorDao.dailyVisitorLogs();
        if (visitor != null && !visitor.isEmpty()) {
            System.out.println();
            printCentered(BLUE + "===========================", width);
            printCentered(BLUE + " RECEPTIONIST DAILY REPORT", width);
            printCentered(BLUE + "===========================", width);                        
            System.out.println();
            Visitor firstVisit = visitor.get(0); // take the first visitor for header info
            System.out.println("RECEPTIONIST: " + firstVisit.getReceptionistName());
            System.out.println("DATE        : " + firstVisit.getCheckInDate().format(DATEFORMATTER));  
            System.out.printf("%-12s | %-20s | %-20s | %-12s | %-12s | %-20s | %-15s%n",
                "VISIT ID", "VISITOR NAME", "PURPOSE", "TIME IN", "TIME OUT", 
                "PERSON VISITED", "POSITION");
            System.out.println("-------------+----------------------+----------------------+--------------+--------------+----------------------+----------------");

            visitor.forEach((visit) -> {
 
                String checkInTime = visit.getCheckInTime().format(TIMEFORMATTER);
                String checkOutTime = (visit.getCheckoutTime()!= null) ? visit.getCheckoutTime().format(TIMEFORMATTER) : "-";

                System.out.printf(BLUE + "%-12d | %-20s | %-20s | %-12s | %-12s | %-20s | %-15s%n",
                visit.getVisitId(),
                visit.getVisitorName(),
                visit.getPurposeOfVisit(),
                checkInTime,
                checkOutTime,
                visit.getVisitedEmployee(),
                visit.getVisitedPosition());
            });
        }else{
            System.out.println();
            System.out.println(RED + "No visitor logs found.");
        }
        
    }  
    
//    public void resetPassword(String resetCode, String username, UserDao userdao){
//        
//        
//        System.out.println();
//        System.out.println("=====================");
//        System.out.println(BLUE + "RESET PASSWORD");
//        System.out.println("===================== ");
//        System.out.println();       
//        System.out.printf(BLUE + "%-18s", "ENTER EMAIL ADD: "); 
//        
//        
//        
//    }
    
  
//-----------------------------------------------------------------//    
//---------------------DASHBOARD-----------------------------------// 
//----------------------------------------------------------------//   
    
    
    public Integer dashboardReceptionistChoice(){
            int space = 2;
         while (true) {
            spacing(space);

            printCentered(BLUE + "======================", width); 
            printCentered(BLUE + " WELCOME RECEPTIONIST", width);
            printCentered(BLUE + "======================", width);
            System.out.println();
            System.out.println(RED_BOLD + "\t\t\t[1] " + BLUE + "LOGIN VISITOR");
            System.out.println(RED_BOLD + "\t\t\t[2] " + BLUE + "CHECK OUT VISITOR");
            System.out.println(RED_BOLD + "\t\t\t[3] " + BLUE + "DELETE VISITOR");
            System.out.println(RED_BOLD + "\t\t\t[4] " + BLUE + "DISPLAY ACTIVE VISITOR");
            System.out.println(RED_BOLD + "\t\t\t[5] " + BLUE + "RECEPTIONIST DAILY REPORT");
            System.out.println(RED_BOLD + "\t\t\t[6] " + BLUE + "CHANGE RECEPTIONIST PASSWORD");
            System.out.println(RED_BOLD + "\t\t\t[7] " + BLUE + "EXIT");
            System.out.printf(BLUE + "\t\t\t    %-3s", "SELECT CHOICE: " + BLUE);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                if (choice >= 1 && choice <= 7) {
                return choice; // valid choice, return immediately
                } else {
                    System.out.println();
                    spacing(space);
                    System.out.println(RED + "\t\t\t\t⚠️ Please select a valid option (1–6).");
                    
                }
                

            } catch (InputMismatchException e) {
                System.out.println();
                spacing(space);
                System.out.println(RED + "\t\t\t\t⚠️ Enter a valid number!");
                
                scanner.nextLine(); // clear buffer
        }
    }
}
    
    
//------------------------------------------------------------------// 
//-------------------FOR SPACING AND INDENTION----------------------//  
//------------------------------------------------------------------//  
    
        
    public void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            System.out.printf("%" + (padding + text.length()) + "s%n", text);
        } else {
            System.out.println(text); 
        }
    }
    
    public static void spacing(int space){
        
        for(int i = 0; i <= space ; i++){
            System.out.println();
                       
        }        
    }
   
    
}