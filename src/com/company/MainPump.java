package com.company;

public class MainPump extends Device {

    public void mainPump(FluidPump water, FluidPump milk, Grinder grinder){
        Value = water.getValue() + milk.getValue() + grinder.getValue();
        water.setValue(0);
        milk.setValue(0);
        grinder.setValue(0);
    }
}
