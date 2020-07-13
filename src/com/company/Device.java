package com.company;

public class Device{
    protected Errors Valid;
    protected int Value = 0;

    public Device(){
        Valid = Errors.valid;
    }
    public void setValue(int value) {
        Value = value;
    }

    public Errors getValid() {
    	return Valid;
    }
    public int getValue() {
        return Value;
    }

}
