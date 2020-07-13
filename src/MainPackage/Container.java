package MainPackage;

public class Container {
    protected Errors Valid;
    protected int Value;
    protected static int maxUsedValue = 150;
    protected static int maxValue = 2000;

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

    public static int getMaxUsedValue() {
        return maxUsedValue;
    }
}
