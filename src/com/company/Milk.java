package com.company;

public class Milk extends Container {
        milkType Milk;
    public Milk(int value){
        super(value);
        Milk = milkType.lactose;
    }
    public Milk(int value, milkType milk){
        super(value);
        Milk = milk;
    }

    public milkType getType() {
        return Milk;
    }
}
