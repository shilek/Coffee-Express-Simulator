package com.company;

public class Container {
    protected Errors Valid;
    protected int Value;
    protected int maxUsedValue = 150;
    protected int maxValue = 2000;

    public Container(){
    	Valid = Errors.valid;
        Value = 1000;
    }

    public Container(int value){
    	Valid = Errors.valid;
        Value = value;
    }

    public Container(int value, int maxUsed){
    	maxUsedValue = maxUsed;
    	Valid = Errors.valid;
        Value = value;
    }

    public void checkValue(int value){
        if (value <= 0) Valid = Errors.errorLtValue;
        else if (value > Value) Valid = Errors.errorGtValue;
        else if (value > maxUsedValue) Valid = Errors.errorGtValue;
        else Valid = Errors.valid;
    }

    public void check() {
        if (Value <= 0) Valid = Errors.errorEmpty;
        else if (Value > maxValue) Valid = Errors.errorGtValue;
        else Valid = Errors.valid;
    }
    
    public Errors getValid() {
    	return Valid;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

}
