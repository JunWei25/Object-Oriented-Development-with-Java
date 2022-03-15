
package Classes;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JUN WEI
 */
public class Appointment {
    
    private String Date;
    private String ID;
    private ArrayList<String> appointmentlist = new ArrayList<String>();
    private String id;
    private String name;
    private String date;
    private String phoneNumber;
    private String centre;
    private String vaccine;
    
    //Creating HAS-A (aggregation) relationship with centre
    Centre Centre;
    
    public Appointment(){
        //default constructor
    }
    
    public Appointment(String ID,String Date, Centre centre){
        this.Date = Date;
        this.ID = ID;
        this.Centre = centre;
    }
    
    public Appointment(String id, String name, String date, String phoneNumber, String centre, String vaccine) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.centre = centre;
        this.vaccine = vaccine;
    }
    
    public void setID(String ID){
        this.ID = ID;
    }
    
    public String getID(){
        return this.ID;
    }
    
    public void setDate(String Date){
        this.Date = Date;
    }
    
    public String getDate(){
        return this.Date;
    }
    
    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }
    
        public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCENTRE() {
        return centre;
    }

    public void setCENTRE(String centre) {
        this.centre = centre;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Appointment(String data) {
        String[] dataArr = data.split(",");
        this.id = dataArr[0];
        this.name = dataArr[1];
        this.date = dataArr[2];
        this.centre = dataArr[3];
        this.vaccine = dataArr[4];
        this.phoneNumber = dataArr[5];
        
    }
}
