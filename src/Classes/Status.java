/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author JUN WEI
 */
public class Status {
        
        private String id;
        private String name;
        private String citizen;
        private String date;
        private String status;


        public Status() {
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitizen() {
        return citizen;
    }

    public void setCitizen(String citizen) {
        this.citizen = citizen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
        
    
    public Status(String data) {
        String[] dataArr = data.split(",");
        this.id = dataArr[0];
        this.name = dataArr[1];
        this.citizen = dataArr[2];
        this.date = dataArr[3];
        this.status = dataArr[4];
        
    }
}
