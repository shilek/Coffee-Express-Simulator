package MainPackage;

public class FluidPump extends Device {
    protected int Pressure;

    public FluidPump (){
        Pressure = 9;
    }
    
    public void getFluid(Milk milk, int value){
       if(value < 0) Valid = Errors.errorLtValue;
       else if(value > milk.getMaxUsedValue()) Valid = Errors.errorGtValue;
       else if(value > milk.getValue()) Valid = Errors.errorGtValue;
       else {
           Valid = Errors.valid;
           Value = value;
           milk.setValue(milk.getValue() - value);
           System.out.println("Pobieram mleko.");
       }
    }
    public void getFluid(Container water, int value){
        if(value < 0) Valid = Errors.errorLtValue;
        else if(value > water.getMaxUsedValue()) Valid = Errors.errorGtValue;
        else if(value > water.getValue()) Valid = Errors.errorGtValue;
        else {
            Valid = Errors.valid;
            Value = value;
            water.setValue(water.getValue() - value);
            System.out.println("Pobieram wode.");
        }
    }

    public void setPressure(int pressure) {
        if (pressure < 9 || pressure > 18) Valid = Errors.errorPressure;
        else  {
            Valid = Errors.valid;
            Pressure = pressure;
        }
    }

}
