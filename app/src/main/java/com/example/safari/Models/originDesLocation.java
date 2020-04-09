package com.example.safari.Models;

public class originDesLocation {
    private String oLatitude, oLongitude, dLatitude, dLongitude, distance, price;

    public originDesLocation(String oLatitude, String oLongitude, String dLatitude, String dLongitude, String distance, String price){
        this.oLatitude = oLatitude;
        this.oLongitude = oLongitude;
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;
        this.distance = distance;
        this.price = price;
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
    public void setDistance(String distance){
        this.distance = distance;
    }
    public String getDistance(){
        return distance;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return price;
    }

}
