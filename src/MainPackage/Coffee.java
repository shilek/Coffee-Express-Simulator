package MainPackage;

public class Coffee extends Container{
    coffeeType coffee;

    public Coffee() {
        Value = 1000;
    }

    public Coffee(int value){
        super(value);
        coffeeType coffee = coffeeType.arabica;
    }

    public Coffee(int value, coffeeType Coffee){
        super(value);
        coffeeType coffee = Coffee;
    }
    
    public coffeeType getType() {
    	return coffee;
    }


}
