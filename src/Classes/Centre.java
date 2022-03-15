/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JUN WEI
 */
package Classes;

public class Centre {
    private String Centre;
    
    //Creating HAS-A (aggregation) relationship with vaccine class
    Appointment appointment;
    
    public Centre(){
    //default constructor
    }
   
   public Centre(String centre, Appointment app){
       this.Centre = centre;
       this.appointment = app;
       
   }
   
    public void setCentre(String centre){
        this.Centre = centre;
    }
    
    public String getCentre(){
        return this.Centre;
    }
}
