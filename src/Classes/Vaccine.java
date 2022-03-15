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

public class Vaccine {
    private String VacName;
    private String VacID;
    private String VacQuantity;
    
    //Creating HAS-A (aggregation) relationship with centre class
    Centre vacCentre;
    
    public Vaccine() {
    // default constructor
    }
    public Vaccine(String ID, String Name, String Quantity, Centre vacCentre){
        this.VacID = ID;
        this.VacName = Name;
        this.VacQuantity = Quantity;
        this.vacCentre = vacCentre;
    
    }
    
    public void setVacName(String name){
        this.VacName = name;
    }
    
    public void setVacID(String ID){
        this.VacID = ID;
    }
    
    public void setVacQuantity(String quantity){
        this.VacQuantity = quantity;
    }
    
    public String getVacName(){
        return this.VacName;
    }
    
    public String getVacID(){
        return this.VacID;
    }
    
    public String getVacQuantity(){
        return this.VacQuantity;
    }
    
}
