package MainPackage;

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
}
