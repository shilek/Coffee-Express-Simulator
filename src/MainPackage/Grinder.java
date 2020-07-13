package MainPackage;

public class Grinder extends Device{
        public void Grind (Coffee coffee, int value) {

          if (value < 0) Valid = Errors.errorLtValue;
          else if (value > coffee.getMaxUsedValue()) Valid = Errors.errorGtValue;
          else if(value > coffee.getValue()) Valid = Errors.errorGtValue;
          else {
              Valid = Errors.valid;
              coffee.setValue(coffee.getValue() - value);
              System.out.println("Miele kawe.");
          }
        }
}
