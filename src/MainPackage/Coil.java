package MainPackage;

public class Coil extends Device{
    protected int maxTemp = 250, currentTemp;
    public Coil(){
        currentTemp = 0;
    }

    public void Heat (int temp){
        if (temp > maxTemp) Valid = Errors.errorGtTemp;
        else if (temp < 0) Valid = Errors.errorLtTemp;
        else {
            Valid = Errors.valid;
            currentTemp = temp;
            System.out.println("Podgrzewam.");
        }
    }
}
