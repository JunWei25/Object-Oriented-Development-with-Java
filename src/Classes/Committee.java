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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import GUI.UserLogin;
import GUI.*;

public class Committee extends User{
    public static final String People_File = "people.txt";
    public static final String People_Loginf = "login.txt";
    public static final String Appointment_File = "appointment.txt";
    public static final String Vaccine_File = "vaccine.txt";
    public static final String Committee_File = "committee.txt";
    public static final String Committee_Loginf = "clogin.txt";
    public static final String Vaccine_lFile = "vaccinelog.txt";
    public static final String Committee_LogInOutR = "clogInOutrecord.txt";
    
    private ArrayList<String> committeelist = new ArrayList<String>();
    
    private String committeeLoginName;
    
    Vaccine vaccine = new Vaccine();
    Centre centre = new Centre();
    People people = new People();
    
    public Committee (){
    //default constructor
    }
    
    public void setCLoginName(String lname){
        this.committeeLoginName = lname;
    }
    
    public String getCLoginName(){
        return committeeLoginName;
    }
    
    public Committee(int ID,String name,  String IC, String DOB, String password){
        super(ID,name,password,IC,DOB);
    }
        
    String searchID[];
    String line,message,id;
    List<String> searchlist = new ArrayList<>();
    ArrayList<String> peoplelist = new ArrayList<String>();
    ArrayList<String> vaccinelist = new ArrayList<String>();
    ArrayList<String> appointmentlist = new ArrayList<String>();
   
    @Override
    public boolean login(String Username, String Password){
        UserLogin user = new UserLogin();
        boolean found = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        
        

        if(Username.equals("Admin") && Password.equals("Admin")){
                JOptionPane.showMessageDialog(null,"Successfull Login!");
                try{
                    FileWriter lfw = new FileWriter(Committee_LogInOutR, true);
                    Writer Output = new BufferedWriter(lfw);
                    Output.write("Login"+","+Username+","+dtf.format(now));
                    Output.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Error!");
                }
                new Personnel_Menu().setVisible(true);
                found = true;
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(Committee_Loginf));
            while((line = br.readLine())!= null && found != true){
                 String[] tmp = line.split(",");
                 String cid = tmp[0];
                 String cname = tmp[1];
                 String cpassword = tmp[2];
                 if(Username.equals(cname) && Password.equals(cpassword)){
                    found = true; 
                    try{
                        FileWriter lfw = new FileWriter(Committee_LogInOutR, true);
                        Writer Output = new BufferedWriter(lfw);
                        this.committeeLoginName = Username;
                        Output.write("Login"+","+Username+","+dtf.format(now));
                        Output.close();
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Error!");
                }
                     
                           
                    JOptionPane.showMessageDialog(null,"Successfull Login!");
                    new Personnel_Menu().setVisible(true);
                    break;
                }
             }
        }catch (Exception e) {
        }  
        return found;
    }
    
    @Override
    public void logout(){
        UserLogin user = new UserLogin();
       
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        try{
            FileWriter lfw = new FileWriter(Committee_LogInOutR, true);
            Writer Output = new BufferedWriter(lfw);
            Output.write(",Logout"+","+dtf.format(now)+"\n");
            Output.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!");
       }
       UserLogin ul = new UserLogin();
       ul.setVisible(true);
    
    }
    
    //--Update People Menu Functions--//
    public String addPeople(int id, String IC, String Name, String Citizenship, String DOB, String status, String Password){
        String pIC,pname,ptype,pDOB,password,pvacstatus,ID;
        ID = String.format("%d", id);
        message = "";
        
        try{
            FileWriter pfw = new FileWriter(People_File, true);
            Writer pOutput = new BufferedWriter(pfw);
            pOutput.write(ID+","+IC+","+Name+","+Citizenship+","+DOB+","+status+"\n");
            pOutput.close();
            FileWriter lfw = new FileWriter(People_Loginf,true);
            Writer lOutput = new BufferedWriter(lfw);
            lOutput.write(ID+","+Name+","+Password+"\n");
            lOutput.close();
            message = "Added Successfully";
        }catch (Exception e) {
            message = "Error! Please check people.txt or login.txt!";
        }
        return message;
    }
    
   
    public String deletePeople(int cID){
        String[] tmp;
        List<String> loginlist = new ArrayList<>();
        boolean found = false;
        String lID, lname,lpassword;
        int checkID,clID;
        String ID = String.format("%d", cID);
        searchlist.clear();
        
        checkID = Integer.parseInt(ID);
        try {
                BufferedReader input = new BufferedReader(new FileReader(People_File));
                while ((line = input.readLine()) != null) {
                searchlist.add(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,e);
            }
            List<String> list = new ArrayList<>();
            for (String element : searchlist) {
                searchID = element.split(",");
                if (!ID.equals(searchID[0])) {
                list.add(element);
                }
            
                else{
                found = true;
                try{
                        BufferedReader br = new BufferedReader(new FileReader(People_Loginf));
                        line = null;
                        while((line = br.readLine())!= null ){
                            tmp = line.split(",");
                            lID = tmp[0];
                            lname = tmp[1];
                            lpassword = tmp[2];
                            clID = Integer.parseInt(lID);
                            if(checkID != clID){
                                loginlist.add(lID+","+lname+","+lpassword);
                                
                            }
                            else{
                                found = true;
                            }
                        }
                        
                    }catch(IOException e){
                        
                }
            }
            }
      
            if (found == true) {
                try {
                    FileWriter fw = new FileWriter(People_File, false);
                    Writer output = new BufferedWriter(fw);
                    int sz = list.size();
                    for (int j = 0; j < sz; j++) {
                        output.write(list.get(j).toString() + "\n");
                    }
                    output.close();
                    
                    FileWriter lfw = new FileWriter(People_Loginf, false);
                    Writer writelogin = new BufferedWriter(lfw);
                    int size = loginlist.size();
                    for (int j = 0; j < size; j++) {
                        writelogin.write(loginlist.get(j).toString() + "\n");
                    }
                    writelogin.close();
                    message = "Record Deleted!";
                } catch (Exception e) {
                    message = "Record Deleted!";
                }
            }else
                message = "ID entered not found!";
        return message;
    
    }
    
    public List <String> ViewPeople(){
        List<String> peopleRecords = new ArrayList<>();
        try {
           //Import people.txt to jTable 
            BufferedReader br = new BufferedReader( new FileReader("people.txt") );   
            while ((line = br.readLine()) != null) {
                peopleRecords.add(line);
                
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Empty File (people.txt)");
        }    
        return peopleRecords;
    }
    
    public List<String> searchPeople(int id){
        int pplID,ID;
        ID = id;
        boolean found=false;
        searchlist.clear();
    
        try {
            BufferedReader input = new BufferedReader(new FileReader("people.txt"));
            while ((line = input.readLine()) != null) {
                searchID = line.split(",");
                pplID = Integer.parseInt(searchID[0]);
                System.out.println(ID==pplID);
                if(ID==pplID){
                    searchlist.add(line);
                    found = true;
                }    
            } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        if (found == false)
            JOptionPane.showMessageDialog(null,"ID entered not found!");
        return searchlist;
    }
    
    
    
    public String editPeople(int ID, String IC, String Name, String Citizenship, String DOB, String status,String password){
        boolean found = false;
        String cpName,cpIC,cpType,cpDOB,cpStatus;
        List<String> list = new ArrayList<>();
        List<String> loginlist = new ArrayList<>();
        String pID,ptype,lname,lpassword,npID,ppassword,pIC,pname,pDOB,pvacstatus;
        int cpID,lID;
        String[] tmp;
        String[] ptmp;
        
        pID = String.format("%d",ID);
        pname = Name;
        pIC = IC;
        ptype = Citizenship;
        pDOB = DOB;
        pvacstatus = status;
        ppassword = password;
        
        int checkID = Integer.valueOf(pID);
        try {
                BufferedReader input = new BufferedReader(new FileReader(People_File));
                while ((line = input.readLine()) != null) {
                    ptmp = line.split(",");
                    cpID = Integer.parseInt(ptmp[0]);
                    cpIC = ptmp[1];
                    cpName = ptmp[2];
                    cpType = ptmp[3];
                    cpDOB = ptmp[4];
                    cpStatus = ptmp[5];
                    if(checkID == cpID){
                        list.add(pID + "," + pIC + "," + pname + "," + ptype+","+pDOB+","+pvacstatus);
                        found = true;
                        try{
                        BufferedReader br = new BufferedReader(new FileReader(People_Loginf));
                        line = null;
                        while((line = br.readLine())!= null ){
                            tmp = line.split(",");
                            lID = Integer.parseInt(tmp[0]);
                            lname = tmp[1];
                            lpassword = tmp[2];
                            System.out.println(checkID==lID);
                            if(checkID == lID){
                                loginlist.add(pID+","+pname+","+ppassword);
                                
                            }
                            else{
                                npID = String.format("%d",lID);
                                loginlist.add(npID+","+lname+","+lpassword);
                            }
                        }
                        
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,e);}
                    }
                    else{
                        npID = String.format("%d",cpID);
                        list.add(npID+","+cpIC+","+cpName+","+cpType+","+cpDOB+","+cpStatus);
                    }
                }
                input.close();
            
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
            }
            if (found == true) {
            try {
                FileWriter fw = new FileWriter(People_File, false);
                Writer output = new BufferedWriter(fw);
                int sz = list.size();
                for (int j = 0; j < sz; j++) {
                output.write(list.get(j).toString() + "\n");
                }
                FileWriter fwlogin = new FileWriter(People_Loginf,false);
                Writer loginfile = new BufferedWriter(fwlogin);
                int size = loginlist.size();
                for(int i = 0; i<size; i++){
                loginfile.write(loginlist.get(i)+ "\n");
                }
                
                output.close();
                loginfile.close();
                message = "Record Modified!";
            } catch (Exception e) {
                message = "Error!";
            }
            } else
                message = "ID entered not found!";
        return message;
    }
 
    
    //--Update Vaccine Functions--//
    public String addVaccine(String ID, String Name, String Quantity, String center){
        String vacID,vacName,vacQuantity,vacCenter;
        vacID = ID;
        vacName = Name;
        vacQuantity = Quantity;
        vacCenter = center;
        try{
            FileWriter pfw = new FileWriter(Vaccine_File, true);
            Writer pOutput = new BufferedWriter(pfw);
            pOutput.write(vacID+","+vacName+","+vacQuantity+","+vacCenter+"\n");
            pOutput.close();
            FileWriter vlfw = new FileWriter(Vaccine_lFile, false);
            Writer vl = new BufferedWriter(vlfw);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            UserLogin user = new UserLogin();
            vl.write("Committee,ADDED,"+vaccine.getVacName()+","+centre.getCentre()+","+vaccine.getVacQuantity()+","+dtf.format(now)+"\n");
            message = "Successfully added";
        }catch (Exception e) {
            message = "Error!";
        }
        return message;
    }
    
    public String deleteVaccine(String id){
        String vacName,vacID,vacQuantity,vacCentre;
        String ID;
        ID = id;
        boolean found = false;
        vaccinelist.clear();
        searchlist.clear();
        try {
            BufferedReader input = new BufferedReader(new FileReader(Vaccine_File));
            while ((line = input.readLine()) != null) {
            searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        List<String> list = new ArrayList<>();
        for (String element : searchlist) {
            searchID = element.split(",");
            vacID = searchID[0];
            vacName = searchID[1];
            vacQuantity = searchID[2];
            vacCentre = searchID[3];
            if (!ID.equals(searchID[0])) {
                list.add(element);
            }
            else{
                found = true;
            }
        }
      
        if (found == true) {
            try {
                FileWriter fw = new FileWriter(Vaccine_File, false);
                Writer output = new BufferedWriter(fw);
                int sz = list.size();
                for (int j = 0; j < sz; j++) {
                    output.write(list.get(j).toString() + "\n");
                }
                FileWriter vlfw = new FileWriter(Vaccine_lFile, false);
                Writer vl = new BufferedWriter(vlfw);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                UserLogin user = new UserLogin();
                vl.write("Committee,DELETE,"+vaccine.getVacName()+","+centre.getCentre()+","+vaccine.getVacQuantity()+","+dtf.format(now)+"\n");
                output.close();
                vl.close();
                message = "Record Deleted!";
            } catch (Exception e) {
                message = "error";
            }
        }else
           message = "ID entered not found!";
        return message;
     }
    
    public List <String> ViewVaccine(){
        List<String> vaccineRecords = new ArrayList<>();
        try {
           //Import people.txt to jTable 
            BufferedReader br = new BufferedReader( new FileReader("vaccine.txt") );   
            while ((line = br.readLine()) != null) {
                vaccineRecords.add(line);
                
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Empty File (vaccine.txt)");
        }    
        return vaccineRecords;
    }
    
    public List<String> searchVaccine(String ID){
        String vacID;
        boolean found=false;
        searchlist.clear();
    
        try {
            BufferedReader input = new BufferedReader(new FileReader("vaccine.txt"));
            while ((line = input.readLine()) != null) {
                searchID = line.split(",");
                vacID = searchID[0];
                if(ID.equals(vacID)){
                    searchlist.add(line);
                    found = true;
                }    
            } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        if (found == false)
            JOptionPane.showMessageDialog(null,"ID entered not found!");
        return searchlist;
    }
   
    //method overloading, edit method for editing vaccine
    public String edit(String vID, String vName, String vQuantity, String vCenter ){
        String Name, Quantity, Centre,vacName,quantity,vacCentre,vid,ID;
        String[] tmp;
        int cid;
        
        boolean found = false;
        vaccinelist.clear();
        ID = vID;
        Name = vName;
        Quantity = vQuantity;
        Centre = vCenter;
        
        vaccine.setVacID(ID);
        vaccine.setVacName(Name);
        vaccine.setVacQuantity(Quantity);
        
        int checkID = Integer.valueOf(ID);
        try{
            BufferedReader br = new BufferedReader(new FileReader(Vaccine_File));
            String line = null;
            while((line = br.readLine())!= null ){
                tmp = line.split(",");
                vid = tmp[0];
                vacName = tmp[1];
                quantity = tmp[2];
                vacCentre = tmp[3];
                
                cid = Integer.parseInt(vid);
                if(checkID == cid){
                    vaccinelist.add(vaccine.getVacID() + "," + vaccine.getVacName() + "," + vaccine.getVacQuantity()+","+Centre);
                    found = true;
            }
            else{
                vaccinelist.add(vid + "," + vacName + "," + quantity+","+vacCentre);
            }

            }
      
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        if(found == true){
            try {
                FileWriter fw = new FileWriter(Vaccine_File, false);
                Writer output = new BufferedWriter(fw);
                int sz = vaccinelist.size();
                for (int j = 0; j < sz; j++) {
                    output.write(vaccinelist.get(j).toString() + "\n");
                }
                FileWriter vlfw = new FileWriter(Vaccine_lFile, false);
                Writer vl = new BufferedWriter(vlfw);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                UserLogin user = new UserLogin();
                vl.write("Committee,EDIT,"+vaccine.getVacName()+","+centre.getCentre()+","+vaccine.getVacQuantity()+","+dtf.format(now)+"\n");
                message = "Succesfully modified!";
                output.close();
                vl.close();
            } catch (Exception e) {
                message = "Error!";
            }
        }else
            message = "ID entered not found!";
  
        return message;
    }
    
    //--Update Appointment Methods--//
    public String addAppointment(String ID, String IC,String Name, String Date, String Centre, String Vaccine, String PhoneNumber){
        message = "";
        String name, ic,date, centre, vaccine, phoneNumber;
        
        id = ID;
        ic = IC;
        name = Name;
        date = Date;
        centre = Centre;
        vaccine = Vaccine;
        phoneNumber = PhoneNumber;
        
        try{
            FileWriter vfw = new FileWriter(Appointment_File, true);
            Writer vOutput = new BufferedWriter(vfw);
            vOutput.write(id+","+ic+","+name+","+date+","+centre+","+vaccine+","+phoneNumber+"\n");
            vOutput.close();
            message = "Appointment Added Successfully";
            
        }catch(Exception e){
            message = "Error! Please check vaccine.txt!";
        }
        
        return message;
    }
    
    public String deleteAppointment(String id){
        String ID,aID;
        ID = id;
        boolean found = false;
        appointmentlist.clear();
        searchlist.clear();
        line = "";
        try{
            BufferedReader input = new BufferedReader(new FileReader(Appointment_File));
            while ((line = input.readLine()) != null) 
            searchlist.add(line);
        }catch(IOException e){
         
         message = "Error! Please Check appointment.txt!";       
        }
         System.out.println(searchlist);
        List<String> list = new ArrayList<>();
        for (String element : searchlist) {
            searchID = element.split(",");
            aID = searchID[0];
            if (ID.equals(aID)) {
                found = true;
            }
            else{
                list.add(element);
            }
        }
        if(found == true){
            try {
                FileWriter fw = new FileWriter(Appointment_File, false);
                Writer output = new BufferedWriter(fw);
                int sz = list.size();
                for (int j = 0; j < sz; j++) {
                    output.write(list.get(j).toString() + "\n");
                }
                output.close();
                message = "Record Deleted!";
            } catch (Exception e) {
                message = "error";
            }
        
        }else
            message = "ID entered not found!";
        
        return message;
    }
    
    public List <String> ViewAppointment(){
        List<String> appointmentlist = new ArrayList<>();
        try {
           //Import people.txt to jTable 
            BufferedReader br = new BufferedReader( new FileReader("appointment.txt") );   
            while ((line = br.readLine()) != null) {
                appointmentlist.add(line);
                
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Empty File (appointment.txt)");
        }    
        return appointmentlist;
    }
    
    public List<String> searchAppointment(String ID){
        String aptID;
        boolean found=false;
        searchlist.clear();
    
        try {
            BufferedReader input = new BufferedReader(new FileReader("appointment.txt"));
            while ((line = input.readLine()) != null) {
                searchID = line.split(",");
                aptID = searchID[0];
                if(ID.equals(aptID)){
                    searchlist.add(line);
                    found = true;
                }    
            } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        if (found == false)
            JOptionPane.showMessageDialog(null,"ID entered not found!");
        return searchlist;
    }
   
    //method overloading, edit method for editing appointment
    public String edit(String ID, String IC,String Name, String Date, String Centre, String Vaccine, String PhoneNumber){
        String appID,appName,appDate,appCentre,appVaccine,appPhoneNumber,aID,aIC,aName, aDate, aCentre, aVaccine, aPhoneNumber,appIC;
        String[] vtmp;
        List<String> list = new ArrayList<>();
        int caID;
        boolean found = false;
        
        
        appID = ID;
        appIC = IC;
        appName = Name;
        appDate = Date;
        appCentre = Centre;
        appVaccine = Vaccine;
        appPhoneNumber = PhoneNumber;
        
        
        int checkID = Integer.valueOf(appID);
        try {
                BufferedReader input = new BufferedReader(new FileReader(Appointment_File));
                while ((line = input.readLine()) != null) {
                    vtmp = line.split(",");
                    aID = vtmp[0];
                    aIC = vtmp[1];
                    aName = vtmp[2];
                    aDate = vtmp[3];
                    aCentre = vtmp[4];
                    aVaccine = vtmp[5];
                    aPhoneNumber = vtmp[6];
                    
                    caID = Integer.parseInt(aID);
                    if(checkID == caID){
                        list.add(appID + "," + appIC+","+appName + "," + appDate + "," + appCentre+","+appVaccine+","+appPhoneNumber);
                        found = true;
                        
                    }
                    else{
                        list.add(aID+","+aIC+","+aName+","+aDate+","+aCentre+","+aVaccine+","+aPhoneNumber);
                    }
                }
                input.close();
            
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
            }
            if (found == true) {
            try {
                FileWriter fw = new FileWriter(Appointment_File, false);
                Writer output = new BufferedWriter(fw);
                int sz = list.size();
                for (int j = 0; j < sz; j++) {
                output.write(list.get(j).toString() + "\n");
                }
                
                output.close();
                message = "Record Modified!";
            } catch (Exception e) {
                message = "Error!";
            }
            } else
                message = "ID entered not found!";
        return message;
    }
    
    //--Update Committee Methods--//
    public String addCommittee(String ID, String IC, String Name, String DOB, String Password){
        String cID,cIC,cName,cDOB,cPassword;
        
        message = "";
        cID = ID;
        cIC = IC;
        cName = Name;
        cDOB = DOB;
        cPassword = Password;
        
        try{
            FileWriter pfw = new FileWriter("committee.txt", true);
            Writer pOutput = new BufferedWriter(pfw);
            pOutput.write(cID+","+cIC+","+cName+","+cDOB+"\n");
            pOutput.close();
            FileWriter lfw = new FileWriter("clogin.txt",true);
            Writer lOutput = new BufferedWriter(lfw);
            lOutput.write(cID+","+cName+","+cPassword+"\n");
            lOutput.close();
            message = "Added Successfully";
        }catch (Exception e) {
            message = "Error! Please check committee.txt!";
        }
        return message;
    }
    
    public List <String> ViewCommittee(){
        List<String> committeelist = new ArrayList<>();
        try {
           //Import people.txt to jTable 
            BufferedReader br = new BufferedReader( new FileReader("committee.txt") );   
            while ((line = br.readLine()) != null) {
                committeelist.add(line);
                
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Empty File (committee.txt)");
        }    
        return committeelist;
    }
    
    public List<String> searchCommittee(int ID){
        int comtID;
        int checkID;
        boolean found=false;
        searchlist.clear();
        line = "";
        checkID = ID;
        
        try {
            BufferedReader input = new BufferedReader(new FileReader("committee.txt"));
            while ((line = input.readLine()) != null) {
                searchID = line.split(",");
                comtID = Integer.parseInt(searchID[0]);
                if(checkID==comtID){
                    searchlist.add(line);
                    found = true;
                }    
            } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        if (found == false)
            JOptionPane.showMessageDialog(null,"ID entered not found!");
        return searchlist;
    }
    
    public String deleteCommittee(int id){
        String ID,clID,clname,clpassword,cname,cid,cIC,cDOB;
        ID = String.format("%d",id);
        boolean found = false;
        String[] tmp;
        List<String> loginlist = new ArrayList<>();
        
        searchlist.clear();
        try{
            BufferedReader input = new BufferedReader(new FileReader("committee.txt"));
            while ((line = input.readLine()) != null) 
            searchlist.add(line);
        }catch(IOException e){
            
            message = "Error! Please Check committee.txt!";       
        }
        List<String> list = new ArrayList<>();
        for (String element : searchlist) {
            searchID = element.split(",");
            if (!ID.equals(searchID[0])) {
                list.add(element);
            }
            else{
                found = true;
                try{
                        BufferedReader Br = new BufferedReader(new FileReader("clogin.txt"));
                        line = null;
                        while((line = Br.readLine())!= null ){
                            tmp = line.split(",");
                            clID = tmp[0];
                            clname = tmp[1];
                            clpassword = tmp[2];
                            if(ID != clID){
                                
                                loginlist.add(clID+","+clname+","+clpassword);
                                
                            }
                            else{
                                found = true;
                            }
                        }
                        
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,e);}
            }
        }
        if(found == true){
            try {
                FileWriter fw = new FileWriter("committee.txt", false);
                Writer output = new BufferedWriter(fw);
                int sz = list.size();
                for (int j = 0; j < sz; j++) {
                    output.write(list.get(j).toString() + "\n");
                }
                FileWriter cfw = new FileWriter("clogin.txt", false);
                Writer coutput = new BufferedWriter(cfw);
                int size = loginlist.size();
                for (int j = 0; j < size; j++) {
                    coutput.write(loginlist.get(j).toString() + "\n");
                }
                output.close();
                coutput.close();
                message = "Record Deleted!";
                
            } catch (Exception e) {
                message = "error";
            }
        
        }else
            message = "ID entered not found!";
        
        return message;
    
    }
    
    //method overloading, edit method for editing committee
    public String edit(String ID,String IC, String Name, String DOB, String Password){
        String cid, cname,cic,cdob,cpassword,clname,clpassword,ncID,cID,cIC,cName,cDOB,cPassword;
        List<String> loginlist = new ArrayList<>();
        String[] tmp;
        int checkID,CID,clID;
        boolean found = false;
        message = "";
        committeelist.clear();
        
        cID = ID;
        cIC = IC;
        cName = Name;
        cDOB = DOB;
        cPassword = Password;
        
        checkID = Integer.valueOf(ID);
        try{
            BufferedReader br = new BufferedReader(new FileReader("committee.txt"));
            String line = null;
            while((line = br.readLine())!= null ){
                tmp = line.split(",");
                cid = tmp[0];
                cic = tmp[1];
                cname = tmp[2];
                cdob = tmp[3];
                
                CID = Integer.parseInt(cid);
                if(checkID == CID){
                    committeelist.add(cID + "," + cIC + "," + cName+","+cDOB);
                    found = true;
                    try{
                        BufferedReader Br = new BufferedReader(new FileReader("clogin.txt"));
                        line = null;
                        while((line = Br.readLine())!= null ){
                            tmp = line.split(",");
                            clID = Integer.parseInt(tmp[0]);
                            clname = tmp[1];
                            clpassword = tmp[2];
                            if(checkID == clID){
                                loginlist.add(cID+","+cName+","+cPassword);
                                
                            }
                            else{
                                ncID = String.format("%d",clID);
                                loginlist.add(ncID+","+clname+","+clpassword);
                            }
                        }
                        
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,e);}
            }
            else{
                committeelist.add(cid + "," + cic + "," + cname+","+cdob);
            }

            }
      
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        if(found == true){
            try {
                FileWriter fw = new FileWriter("committee.txt", false);
                Writer output = new BufferedWriter(fw);
                int sz = committeelist.size();
                for (int j = 0; j < sz; j++) {
                    output.write(committeelist.get(j).toString() + "\n");
                }
                FileWriter fwlogin = new FileWriter("clogin.txt",false);
                Writer cloginfile = new BufferedWriter(fwlogin);
                int size = loginlist.size();
                for(int i = 0; i<size; i++){
                cloginfile.write(loginlist.get(i)+ "\n");
                }
                message = "Succesfully modified!";
                output.close();
                cloginfile.close();
            } catch (Exception e) {
                message = "Error!";
            }
        }else
            message = "ID entered not found!";
  
        return message;
    }
    
    //--Check Duplication functions--//
    
    //checl for duplicate people
    //method overloading
    public boolean checkDuplicatePeople(String ID, String IC){
        boolean found = false;
        String cpID, cpIC,cpName,cpType,cpDOB,id,ic;
        String[] ptmp;
        id = ID;
        ic = IC;
        searchlist.clear();
        try {
            BufferedReader input = new BufferedReader(new FileReader(People_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            ptmp = element.split(",");
            cpID = ptmp[0];
            cpIC = ptmp[1];
            cpName = ptmp[2];
            cpType = ptmp[3];
            cpDOB = ptmp[4];
            if (id.equals(cpID) || ic.equals(cpIC)) {
                found = true;
            }
        }
        return found;
    }
    //method overloading
    public boolean checkDuplicatePeople(String IC){
        boolean found = false;
        String cpID, cpIC,cpName,cpType,cpDOB,id,ic;
        String[] ptmp;
        ic = IC;
        searchlist.clear();
        try {
            BufferedReader input = new BufferedReader(new FileReader(People_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            ptmp = element.split(",");
            cpID = ptmp[0];
            cpIC = ptmp[1];
            cpName = ptmp[2];
            cpType = ptmp[3];
            cpDOB = ptmp[4];
            if (IC.equals(cpIC)) {
                found = true;
            }
        }
        return found;
    }
    
    //Check for dupluicate vaccine
    //method overloading
    public boolean checkDuplicateVaccine(String ID, String Name,String Centre){
        boolean found = false;
        String cvID, cvQuantity,cvName,cvCentre,id, vname, vcentre;
        String[] ptmp;
        id = ID;
        vname = Name;
        vcentre = Centre;
        searchlist.clear();
        
        try {
            BufferedReader input = new BufferedReader(new FileReader(Vaccine_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            ptmp = element.split(",");
            cvID = ptmp[0];
            cvName = ptmp[1];
            cvQuantity = ptmp[2];
            cvCentre = ptmp[3];
            if (id.equals(cvID)) {
                found = true;
            }else if(vname.equals(cvName) && vcentre.equals(cvCentre)){
                found = true;}
        }
        return found;
    }
    
    //method overloading
    public boolean checkDuplicateVaccine(String ID, String Name,String Quantity,String Centre){
        boolean found = false;
        String cvID, cvQuantity,cvName,cvCentre,id, vname, vquantity, vcentre;
        String[] ptmp;
        id = ID;
        vname = Name;
        vquantity = Quantity;
        vcentre = Centre;
        searchlist.clear();
        
        try {
            BufferedReader input = new BufferedReader(new FileReader(Vaccine_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            ptmp = element.split(",");
            cvID = ptmp[0];
            cvName = ptmp[1];
            cvQuantity = ptmp[2];
            cvCentre = ptmp[3];
            if(id.equals(cvID) && vname.equals(cvName) && vname.equals(vcentre) && cvQuantity.equals(vquantity)){
                found = true;}
            
        }
        return found;
    }
    
    //checking duplicate appointment
    public boolean checkDuplicateAppointmentID(String ID ){
        boolean found = false;
        String aID, aName,aDate,aCentre,id,aPhoneNumber, appDate, aVaccine, appPhoneNumber;
        String[] atmp;
        id = ID;
        searchlist.clear();
        
        try {
            BufferedReader input = new BufferedReader(new FileReader(Appointment_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            atmp = element.split(",");
            aID = atmp[0];
            aName = atmp[1];
            aDate = atmp[2];
            aCentre = atmp[3];
            aVaccine = atmp[4];
            aPhoneNumber = atmp[5];
            if (id.equals(aID)) {
                found = true;
            }
        }
        return found;
    }
    
    public boolean checkDuplicateAppointmentIC(String IC ){
        boolean found = false;
        String aID, aName,aDate,aCentre,ic,aPhoneNumber, appDate, aVaccine, appPhoneNumber,aIC;
        String[] atmp;
        ic = IC;
        searchlist.clear();
        
        try {
            BufferedReader input = new BufferedReader(new FileReader(Appointment_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            atmp = element.split(",");
            aID = atmp[0];
            aIC = atmp[1];
            aName = atmp[2];
            aDate = atmp[3];
            aCentre = atmp[4];
            aVaccine = atmp[5];
            aPhoneNumber = atmp[6];
            if (aIC.equals(ic)) {
                found = true;
            }
        }
        return found;
    }
    
    //method overloading
    public boolean checkDuplicateCommittee(String ID,String IC){
        boolean found = false;
        String ccID, ccIC,ccName,ccType,ccDOB,id,ic;
        String[] ctmp;
        id = ID;
        ic = IC;
        searchlist.clear();
        
        try {
            BufferedReader input = new BufferedReader(new FileReader(Committee_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            ctmp = element.split(",");
            ccID = ctmp[0];
            ccIC = ctmp[1];
            ccName = ctmp[2];
            ccDOB = ctmp[3];
            if (id.equals(ccID) || ic.equals(ccIC)) {
                found = true;
            }
        }
        
        return found;
    }
    
    //method overloading
    public boolean checkDuplicateCommittee(String IC){
        boolean found = false;
        String ccID, ccIC,ccName,ccType,ccDOB,id,ic;
        String[] ctmp;
        searchlist.clear();
        
        ic = IC;
        try {
            BufferedReader input = new BufferedReader(new FileReader(Committee_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            ctmp = element.split(",");
            ccID = ctmp[0];
            ccIC = ctmp[1];
            ccName = ctmp[2];
            ccDOB = ctmp[3];
            if (ic == ccIC) {
                found = true;
            }
        }
        
        return found;
    }
    
    //--Appointment checking functions--//
    public boolean checkVaccineAvailability(String Name, String Centre){
        String vID,vName,vQuantity,vCentre,vacName,vacCentre,newVQuantity,lvName,lvCentre;
        int vacQuantity,subtract;
        String[] vtmp;
        lvCentre = "";
        lvName = "";
        vacQuantity = 0;
        boolean found = false;
        subtract = 1;
        
        searchlist.clear();
        vaccinelist.clear();
        vacName = Name;
        vacCentre = Centre;
        try {
            BufferedReader input = new BufferedReader(new FileReader(Vaccine_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            vtmp = element.split(",");
            vID = vtmp[0];
            vName = vtmp[1];
            vQuantity = vtmp[2];
            vCentre = vtmp[3];
            lvName = vName;
            lvCentre = vCentre;
            if (vacName.equals(vName) && vacCentre.equals(vCentre)) {
                found = true;
                vacQuantity = Integer.parseInt(vQuantity);
                if(vacQuantity>=1){
                    vacQuantity -= subtract;
                    newVQuantity = String.format("%d", vacQuantity);
                    vaccinelist.add(vID+","+vName+","+newVQuantity+","+vCentre);
                    found = true;
                }
            }
            else{
                vaccinelist.add(vID+","+vName+","+vQuantity+","+vCentre);
            }
        }
        try {
                FileWriter fw = new FileWriter(Vaccine_File, false);
                Writer output = new BufferedWriter(fw);
                int sz = vaccinelist.size();
                for (int j = 0; j < sz; j++) {
                    output.write(vaccinelist.get(j).toString() + "\n");
                }
                FileWriter lvfw = new FileWriter(Vaccine_lFile,true);
                Writer lvoutput = new BufferedWriter(lvfw);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                UserLogin user = new UserLogin();
                
                lvoutput.write("Committee ,SUBTRACTED,"+lvName+","+lvCentre+","+vacQuantity+","+dtf.format(now)+"\n");
                output.close();
                lvoutput.close();
                message = "Record Deleted!";
            } catch (Exception e) {
                message = "error";
            }
        
        return found;
    }
    
    public boolean checkPeopleExist(String Name,String IC){
        boolean found = false;
        String cpID, cpIC,cpName,cpType,cpDOB,name,ic;
        String[] ctmp;
        searchlist.clear();
        
        name = Name;
        ic = IC;
        try {
            BufferedReader input = new BufferedReader(new FileReader(People_File));
            while ((line = input.readLine()) != null) {
                searchlist.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        for (String element : searchlist) {
            ctmp = element.split(",");
            cpID = ctmp[0];
            cpIC = ctmp[1];
            cpName = ctmp[2];
            cpDOB = ctmp[3];
            if (cpName.equals(name) && cpIC.equals(ic)) {
                found = true;
            }
        }
        
        return found;
    }
}