package Classes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JUN WEI
 */
public abstract class User {
    private String name;
    private String password;
    private String IC;
    private String DOB;
    private int ID;
    
    public User(){
        //default constructor
    }
     
    public User(int ID,String name,String password, String IC, String DOB){
       this.name = name;
       this.password = password;
       this.IC = IC;
       this.DOB = DOB;
       this.ID = ID;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setLName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setIC(String IC){
        this.IC = IC;
    }
    
    public void setDOB(String DOB){
        this.DOB = DOB;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getDOB(){
        return this.DOB;
    }
    
    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    
    public String getIC(){
        return this.IC;
    }
    public abstract boolean login(String name,String password);
    
    public abstract void logout();
    
    public void viewAppointmet(String ID){};
}
