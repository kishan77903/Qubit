package com.example.rohittamra.indiahackathron;

/**
 * Created by Rohit Tamra on 24-Mar-18.
 */

public class SupportingClass {
    String customeridGOT,devicename,ratingentered,powerGOT;
    SupportingClass()
    {}
    public SupportingClass(String customeridGOT, String devicename, String ratingentered, String powerGOT) {
        this.customeridGOT = customeridGOT;
        this.devicename = devicename;
        this.ratingentered = ratingentered;
        this.powerGOT = powerGOT;
    }

    public String getCustomeridGOT() {
        return customeridGOT;
    }

    public String getDevicename() {
        return devicename;
    }

    public String getRatingentered() {
        return ratingentered;
    }

    public String getPowerGOT() {
        return powerGOT;
    }

    public void setCustomeridGOT(String customeridGOT) {
        this.customeridGOT = customeridGOT;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public void setRatingentered(String ratingentered) {
        this.ratingentered = ratingentered;
    }

    public void setPowerGOT(String powerGOT) {
        this.powerGOT = powerGOT;
    }
}
