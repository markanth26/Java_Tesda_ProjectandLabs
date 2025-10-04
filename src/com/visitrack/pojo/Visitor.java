/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Visitor {
    
    private Integer visitId;
    private String visitorName;
    private int receptionistEmployeeId;
    private int visitedEmployeeId;
    private LocalDate checkInDate; 
    private LocalDateTime checkInTime;
    private LocalDateTime checkoutTime;
    private String purposeOfVisit;
    private String visitedEmployee;
    private String receptionistName;
    private String visitedPosition;
    private String contactNo;
    private int isActive;
    private int randomNumber;
    
    
    public Visitor(){       
    }
    
    public Visitor(int visitId, String visitorName, int receptionistEmployeeId, int visitedEmployeeId, LocalDate checkInDate, LocalDateTime checkInTime, LocalDateTime checkoutTime, String purposeOfVisit) {
        this.visitId = visitId;
        this.visitorName = visitorName;
        this.receptionistEmployeeId = receptionistEmployeeId;
        this.visitedEmployeeId = visitedEmployeeId;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkoutTime = checkoutTime;
        this.purposeOfVisit = purposeOfVisit;
    }
   
    public Visitor(int visitId, String visitorName, LocalDate checkInDate, LocalDateTime checkInTime, LocalDateTime checkoutTime, String purposeOfVisit, String visitedEmployee, String receptionistName, String visitedPosition) {
        this.visitId = visitId;
        this.visitorName = visitorName;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkoutTime = checkoutTime;
        this.purposeOfVisit = purposeOfVisit;
        this.visitedEmployee = visitedEmployee;
        this.receptionistName = receptionistName;
        this.visitedPosition = visitedPosition;
    }

    public Visitor(String visitorName, LocalDate checkInDate, LocalDateTime checkInTime, LocalDateTime checkoutTime, String purposeOfVisit, String visitedEmployee, String visitedPosition, int randomNumber) {
        this.visitorName = visitorName;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkoutTime = checkoutTime;
        this.purposeOfVisit = purposeOfVisit;
        this.visitedEmployee = visitedEmployee;
        this.visitedPosition = visitedPosition;
        this.randomNumber = randomNumber;
    }
      
    public Visitor(String visitorName, int receptionistEmployeeId, int visitedEmployeeId, String purposeOfVisit, String contactNo, int randomNumber) {
        this.visitorName = visitorName;
        this.receptionistEmployeeId = receptionistEmployeeId;
        this.visitedEmployeeId = visitedEmployeeId;
        this.purposeOfVisit = purposeOfVisit;
        this.contactNo = contactNo;
        this.randomNumber = randomNumber;
    }

    public Visitor(Integer visitId, String visitorName) {
        this.visitId = visitId;
        this.visitorName = visitorName;
    }
    
    public Visitor(int visitId) {
        this.visitId = visitId;
    }

    public int getVisitId() {
        return visitId;
    }
    
    public String getVisitorName() {
        return visitorName;
    }

    public int getReceptionistEmployeeId() {
        return receptionistEmployeeId;
    }
 
    public int getVisitedEmployeeId() {
        return visitedEmployeeId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }
 
    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public String getVisitedEmployee() {
        return visitedEmployee;
    }

    public String getReceptionistName() {
        return receptionistName;
    }

    public String getVisitedPosition() {
        return visitedPosition;
    }

    public int getIsActive() {
        return isActive;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override
    public String toString() {
        return "Visitor{" + "visitId=" + visitId + ", visitorName=" + visitorName + ", receptionistEmployeeId=" + receptionistEmployeeId + ", visitedEmployeeId=" + visitedEmployeeId + ", checkInDate=" + checkInDate + ", checkInTime=" + checkInTime + ", checkoutTime=" + checkoutTime + ", purposeOfVisit=" + purposeOfVisit + ", visitedEmployee=" + visitedEmployee + ", receptionistName=" + receptionistName + ", visitedPosition=" + visitedPosition + ", contactNo=" + contactNo + ", isActive=" + isActive + ", randomNumber=" + randomNumber + '}';
    }

    
    
    }

    
    
    
  

