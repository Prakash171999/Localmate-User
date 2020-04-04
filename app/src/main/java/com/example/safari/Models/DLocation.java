package com.example.safari.Models;

public class DLocation {
    private String latitude, longitude;

    public DLocation(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude(){
        return latitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public String getLongitude(){
        return latitude;
    }
    public void setLongitude(String longitude){
        this.latitude = longitude;
    }

}

