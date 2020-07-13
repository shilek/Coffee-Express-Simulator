package com.company;

public class FluidPump extends Device {
    protected int Pressure;

    public FluidPump (){
        Pressure = 16;
    }


    public void checkPressure(int pressure){
        if (pressure < 9 || pressure > 18) Valid = Errors.errorPressure;
        else Valid = Errors.valid;
    }

    public void getFluid(Milk milk, int value){
           Value = value;
           milk.setValue(milk.getValue() - value);
           System.out.println("Pobieram mleko.");
       }

    public void getFluid(Container water, int value){
            Value = value;
            water.setValue(water.getValue() - value);
            System.out.println("Pobieram wode.");
        }

    public void setPressure(int pressure) {
        if (pressure < 9 || pressure > 18) Valid = Errors.errorPressure;
        else  {
            Valid = Errors.valid;
            Pressure = pressure;
        }
    }

}
