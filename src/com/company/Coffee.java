package com.company;

public class Coffee extends Container{
    private coffeeType cType;

    public Coffee() {
        Value = 1000;
    }

    public Coffee(int value) {
        super(value);
        cType = coffeeType.Arabica;
    }

    public Coffee(int value, int maxUsed){
        super(value, maxUsed);
        cType = coffeeType.Arabica;
    }

    public Coffee(int value, coffeeType Coffee){
        super(value);
        cType = Coffee;
    }

    public Coffee(int value, int maxUsed, coffeeType Coffee){
        super(value, maxUsed);
        cType = Coffee;
    }
    
    public coffeeType getType() {
    	return cType;
    }


}
