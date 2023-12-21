package com.example.smartfarm.Domain;

public class FutureDemain {
    private String day;
    private String picPath;
    private String status;
    private int highTemp;
    private int LowTemp;


    public FutureDemain(String day, int picPath, String status, int highTemp, int lowTemp) {
        this.day = day;
        this.picPath = String.valueOf(picPath);
        this.status = status;
        this.highTemp = highTemp;
        LowTemp = lowTemp;
    }

    public String getDay() {
        return day;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getStatus() {
        return status;
    }

    public int getHighTemp() {
        return highTemp;
    }

    public int getLowTemp() {
        return LowTemp;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public void setLowTemp(int lowTemp) {
        LowTemp = lowTemp;
    }
}
