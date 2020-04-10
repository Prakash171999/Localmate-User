package com.example.safari.Models;

public class originLocation {

    private String oLatitude, oLongitude, dLatitude, dLongitude, distance, price;

    public originLocation(String oLatitude, String oLongitude){
        this.oLatitude = oLatitude;
        this.oLongitude = oLongitude;
    }
    public void setOLatitude(String oLatitude){
        this.oLatitude=oLatitude;
    }
    public String getoLatitude(){
        return oLatitude;
    }
    public void setOLongitude(String oLongitude){
        this.oLongitude=oLongitude;
    }
    public  String getoLongitude(){
        return oLongitude;
    }
}
