package com.example.safari.Models;

public class DistanceNPrice {

    private String oLatitude, oLongitude, dLatitude, dLongitude, distance, price;

    public DistanceNPrice(String distance, String price){
        this.distance = distance;
        this.price = price;
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
