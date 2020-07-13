package com.company;

public class Grinder extends Device{


        public void Grind (Coffee coffee, int value) {
            Value = value;
              coffee.setValue(coffee.getValue() - value);
              System.out.println("Miele kawe.");
          }

        }

