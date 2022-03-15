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
public class Program{
            
        private String id;
        private String name;
        private String phoneNumber;
        private String email;

        public Program() {
        }
        
        public Program(String id, String name, String phoneNumber, String email) {
            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    /**
     *
     * @param data
     */
    public Program(String data) {
        String[] dataArr = data.split(",");
        this.id = dataArr[0];
        this.name = dataArr[1];
        this.phoneNumber = dataArr[2];
        this.email = dataArr[3];
    }
        
}
