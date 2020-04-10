package com.example.safari.Models;

public class destinationLocation {

    private String dLatitude, dLongitude;

    public destinationLocation(String dLatitude, String dLongitude){
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;
    }
    public void setDLatitude(String dLatitude){
        this.dLatitude=dLatitude;
    }
    public String getDLatitude(){
        return dLatitude;
    }
    public void setDLongitude(String dLongitude){
        this.dLongitude=dLongitude;
    }
    public String getDLongitude(){
        return dLongitude;
    }

}
