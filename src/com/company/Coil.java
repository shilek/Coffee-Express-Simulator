package com.company;

public class Coil extends Device{
    protected int maxTemp = 250, currentTemp;
    public Coil(){
        currentTemp = 0;
    }

    public void check(int temp) {
        if (temp > maxTemp) Valid = Errors.errorGtTemp;
        else if (temp < 0) Valid = Errors.errorLtTemp;
        else {
            Valid = Errors.valid;
        }
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }


    public void Heat (int temp){
        currentTemp = temp;
        System.out.println("Rozgrzewam grzalke.");
    }

}
