package com.example.safari.Models;

public class Users {
    private int id;
    private String fullname, phoneno, email, dob, driver_id;

    public Users(int id, String fullname, String email, String phoneno, String dob, String driver_id) {
        this.phoneno = phoneno;
        this.email = email;
        this.dob = dob;
        this.fullname = fullname;
        this.driver_id = driver_id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }


    public String getName() {
        return fullname;
    }

    public void setName(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDob(){
        return dob;
    }
    public void setDob(String dob){
        this.dob=dob;
    }

    public String getDriverId(){
        return driver_id;
    }

    public void setDriver_id(String driver_id){
        this.driver_id = driver_id;
    }
}
