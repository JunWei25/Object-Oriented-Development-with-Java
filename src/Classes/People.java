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
import GUI.People_menu;
import GUI.Update_People;
import GUI.UserLogin;
import GUI.Personnel_Menu; //change this

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class People extends User{
    
  private String ptype;
  private String phoneNumber;
  private String pvacstatus;

  public static final String People_File = "people.txt";
  public static final String People_Loginf = "login.txt";
  public static final String People_LogInOutR = "plogInOutrecord.txt";
  
  String line;
  ArrayList<String> peoplelist = new ArrayList<String>();
    

    public People() {
    // default constructor
    }

    public People(int id, String name, String password, String pIC, String pDOB, String ptype, String phoneNumber,String status) {
        super(id,name,password,pIC,pDOB);
        this.ptype = ptype;
        this.phoneNumber = phoneNumber;
        this.pvacstatus = status;
    }
    
    public String getPtype() {
        return this.ptype;
    }


    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public String getStatus(){
        return this.pvacstatus;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(String status){
      this.pvacstatus = status;
    }
    
    @Override
    public boolean login(String Username, String Password){
        boolean found = false;
        try{
            BufferedReader br = new BufferedReader(new FileReader(People_Loginf));
            while((line = br.readLine())!= null ){
                 String[] tmp = line.split(",");
                 String pid = tmp[0];
                 String pname = tmp[1];
                 String ppassword = tmp[2];
                 if(Username.equals(pname) && Password.equals(ppassword)){
                    found = true;
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();
                    
                    try{
                        FileWriter lfw = new FileWriter(People_LogInOutR, true);
                        Writer Output = new BufferedWriter(lfw);
                        Output.write("Login"+","+Username+","+dtf.format(now));
                        Output.close();
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Error!");
                    }
                    UserLogin user = new UserLogin();
                    new People_menu().setVisible(true);
                    
                    JOptionPane.showMessageDialog(null,"Successfull Login!");
                    break;
                }
             }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
      
        return found;
    }
    
    @Override
    public void logout(){
        UserLogin user = new UserLogin();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        try{
            FileWriter lfw = new FileWriter(People_LogInOutR, true);
            Writer Output = new BufferedWriter(lfw);
            Output.write(",Logout"+","+user.LoginName+","+dtf.format(now)+"\n");
            Output.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!");
       }
       UserLogin ul = new UserLogin();
       ul.setVisible(true);
    
    }
    
}

