/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.main;


public class Header {
 
    public static final String GREEN  = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String DARKBLUE = "\u001B[38;5;19m";
    public static final String ORANGE = "\u001B[38;5;208m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GRAY = "\u001B[90m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String ITALIC = "\u001B[3m";
    public static final String RED     = "\u001B[31m";
    public static final String BLACK = "\u001B[30m";
      
    

    // public static method to print the header
    public static void printHeader() {
        int width = 100;
        int widths = 95;
        int nextWidth = 130;

        // Header border
        printCentered(BLACK + "====================================================================" + RESET ,nextWidth);

        // ASCII LOGO
        printCentered(BLUE + " ██╗  ██╗ ██╗ ███████╗ ██╗ ████████╗██████╗    █████╗   ██████╗ ██╗   ██╗" + RESET, width);
        printCentered(BLUE + " ██║  ██║ ██║ ██╔════╝ ██╚══  ██╔══╝ ██╔══██╗ ██╔══██╗██╔════╝██║ ██╔╝" + RESET, widths);
        printCentered(BLUE + " ██║  ██║ ██║ ███████╗ ██║     ██║      ██████╔╝ ███████║██║        █████╔╝ " + RESET, width);
        printCentered(BLUE + "╚██╗ ██╔ ██║ ╚════██║ ██║     ██║       ██╔═██╗  ██╔══██║██║        ██ ╔═██╗ " + RESET, width);
        printCentered(BLUE + " ╚████╔╝ ██║ ███████║ ██║     ██║      ██║    ██║ ██║   ██║╚██████╗██║    ██╗" + RESET, width);
        printCentered(BLUE + "  ╚═══╝   ╚═╝ ╚══════╝ ╚═╝    ╚═╝       ╚═╝ ╚═╝  ╚═╝  ╚═╝ ╚═════╝╚═╝   ╚═╝" + RESET, width);

        System.out.println();
        
        // Subheading
        printCentered(CYAN + ITALIC + "Your Smart, Secure, & Seamless Visitor Log Management System 🖥️✅." + RESET, nextWidth);
        printCentered(ORANGE + "VisiTrack is a Java-based Visitor Log Management System with" + RESET, nextWidth);
        printCentered(ORANGE + "authentication, role-based access, streamlined visitor tracking," + RESET, nextWidth);
        printCentered(ORANGE + "features and complete CRUD (Create, Read, Update, Delete) functionality" + RESET, nextWidth);
        printCentered(ORANGE + "for managing visitor records, user accounts, and system data." + RESET, nextWidth);

        printCentered(BLACK + "====================================================================" + RESET, nextWidth);
    }
    
    public static void printHeaderTwo() {
        int width = 100;
        int widths = 95;
        int nextWidth = 130;

        // Header border
        printCentered(BLACK + "====================================================================" + RESET ,nextWidth);

        // ASCII LOGO
        printCentered(RED + " ██╗  ██╗ ██╗ ███████╗ ██╗ ████████╗██████╗    █████╗   ██████╗ ██╗   ██╗" + RESET, width);
        printCentered(RED + " ██║  ██║ ██║ ██╔════╝ ██╚══  ██╔══╝ ██╔══██╗ ██╔══██╗██╔════╝██║ ██╔╝" + RESET, widths);
        printCentered(RED + " ██║  ██║ ██║ ███████╗ ██║     ██║      ██████╔╝ ███████║██║        █████╔╝ " + RESET, width);
        printCentered(RED + "╚██╗ ██╔ ██║ ╚════██║ ██║     ██║       ██╔═██╗  ██╔══██║██║        ██ ╔═██╗ " + RESET, width);
        printCentered(RED + " ╚████╔╝ ██║ ███████║ ██║     ██║      ██║    ██║ ██║   ██║╚██████╗██║    ██╗" + RESET, width);
        printCentered(RED + "  ╚═══╝   ╚═╝ ╚══════╝ ╚═╝    ╚═╝       ╚═╝ ╚═╝  ╚═╝  ╚═╝ ╚═════╝╚═╝   ╚═╝" + RESET, width);

        System.out.println();
        
        // Subheading
        printCentered(CYAN + ITALIC + "Your Smart, Secure, & Seamless Visitor Log Management System 🖥️✅." + RESET, nextWidth);
        printCentered(ORANGE + "VisiTrack is a Java-based Visitor Log Management System with" + RESET, nextWidth);
        printCentered(ORANGE + "authentication, role-based access, streamlined visitor tracking," + RESET, nextWidth);
        printCentered(ORANGE + "features and complete CRUD (Create, Read, Update, Delete) functionality" + RESET, nextWidth);
        printCentered(ORANGE + "for managing visitor records, user accounts, and system data." + RESET, nextWidth);

        printCentered(BLACK + "====================================================================" + RESET, nextWidth);
    }
    
    public static void printHeaderThree() {
        int width = 100;
        int widths = 95;
        int nextWidth = 130;

        // Header border
        printCentered(BLACK + "====================================================================" + RESET ,nextWidth);

        // ASCII LOGO
        printCentered(GREEN + " ██╗  ██╗ ██╗ ███████╗ ██╗ ████████╗██████╗    █████╗   ██████╗ ██╗   ██╗" + RESET, width);
        printCentered(GREEN + " ██║  ██║ ██║ ██╔════╝ ██╚══  ██╔══╝ ██╔══██╗ ██╔══██╗██╔════╝██║ ██╔╝" + RESET, widths);
        printCentered(GREEN + " ██║  ██║ ██║ ███████╗ ██║     ██║      ██████╔╝ ███████║██║        █████╔╝ " + RESET, width);
        printCentered(GREEN + "╚██╗ ██╔ ██║ ╚════██║ ██║     ██║       ██╔═██╗  ██╔══██║██║        ██ ╔═██╗ " + RESET, width);
        printCentered(GREEN + " ╚████╔╝ ██║ ███████║ ██║     ██║      ██║    ██║ ██║   ██║╚██████╗██║    ██╗" + RESET, width);
        printCentered(GREEN + "  ╚═══╝   ╚═╝ ╚══════╝ ╚═╝    ╚═╝       ╚═╝ ╚═╝  ╚═╝  ╚═╝ ╚═════╝╚═╝   ╚═╝" + RESET, width);

        System.out.println();
        
        // Subheading
        printCentered(CYAN + ITALIC + "Your Smart, Secure, & Seamless Visitor Log Management System 🖥️✅." + RESET, nextWidth);
        printCentered(ORANGE + "VisiTrack is a Java-based Visitor Log Management System with" + RESET, nextWidth);
        printCentered(ORANGE + "authentication, role-based access, streamlined visitor tracking," + RESET, nextWidth);
        printCentered(ORANGE + "features and complete CRUD (Create, Read, Update, Delete) functionality" + RESET, nextWidth);
        printCentered(ORANGE + "for managing visitor records, user accounts, and system data." + RESET, nextWidth);

        printCentered(BLACK + "====================================================================" + RESET, nextWidth);
    }
     
    
    public static void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            System.out.printf("%" + (padding + text.length()) + "s%n", text);
        } else {
            System.out.println(text); 
        }
    }
    
    
//   public static void blinkingHeader(){
//         try{
//          int space = 2;
//          for(int i = 0; i < color.length - 1; i++){             
//            if(i == 0){
//                printHeader(color ,0);
//                Thread.sleep(2000, 1000);
//            }else if(i == 1){
//                spacing(space);
//              printHeader(color ,i);
//            break;
//            }
//         }
//          
//         } catch(Exception e){
//                  
//                       
//         }
//              
//     }
      public static void spacing(int space){
        
        for(int i = 0; i <= space; i++){
            System.out.println();
                       
        }        
    }      
        
    
       
       
   }
    
    
    
    
    



    

  