package MainPackage;
import java.util.Scanner;

public class Main {

	static Scanner read = new Scanner(System.in);
	
	
    public static boolean checkError(Device dev){
        if (dev.getValid() == Errors.errorEmpty) {System.out.println("Pojemnik jest pusty."); return false;}
        else if (dev.getValid() == Errors.errorGtTemp) {System.out.println("Zostala podana za wysoka temperatura."); return false;}
        else if (dev.getValid() == Errors.errorLtTemp) {System.out.println("Zostala podana za niska temperatura."); return false;}
        else if (dev.getValid() == Errors.errorGtValue) {System.out.println("Przekroczono dopuszczalna wartosc lub jest za malo skladnika w kontenerze."); return false;}
        else if (dev.getValid() == Errors.errorLtValue) {System.out.println("Nie mozna podac wartosci mniejszej niz 0."); return false;}
        else if (dev.getValid() == Errors.errorPressure) {System.out.println("Dopuszczalne cisnienie to od 9 do 18 barow."); return false;}
        else return true;
    }
    
    public static boolean checkError(Container con){
        if (con.getValid() == Errors.errorEmpty) {System.out.println("Pojemnik jest pusty."); return false;}
        else if (con.getValid() == Errors.errorGtTemp) {System.out.println("Zostala podana za wysoka temperatura."); return false;}
        else if (con.getValid() == Errors.errorLtTemp) {System.out.println("Zostala podana za niska temperatura."); return false;}
        else if (con.getValid() == Errors.errorGtValue) {System.out.println("Przekroczono dopuszczalna wartosc lub jest za malo skladnika w kontenerze."); return false;}
        else if (con.getValid() == Errors.errorLtValue) {System.out.println("Nie mozna podac wartosci mniejszej niz 0."); return false;}
        else if (con.getValid() == Errors.errorPressure) {System.out.println("Dopuszczalne cisnienie to od 9 do 18 barow."); return false;}
        else if (con.getValid() == Errors.end) {System.out.println("Pojemnik jest pusty."); return false;}
        else return true;
    }
    
    public static int Make(Coffee []coffee, Milk []milk, Container water, Container sugar){
		int temp;
    	boolean valid;
    	int fluidType;
	/*	valid = checkError(coffee[0]);
		valid = checkError(coffee[1]);
		valid = checkError(coffee[2]);
		valid = checkError(coffee[3]);
		valid = checkError(coffee[4]);
		valid = checkError(milk);
		valid = checkError(sugar);
		valid = checkError(water);
		valid = checkError(waterCoil);
		valid = checkError(milkCoil);
		valid = checkError(waterPump);
		valid = checkError(milkPump);
		valid = checkError(grinder);*/
		
		do {
			System.out.print("Podaj ilosc wody: ");
			temp = read.nextInt();
			System.out.println();
			waterPump.getFluid(water, temp);
			valid = checkError(waterPump);
		}while(valid != true);
			do {
			System.out.println("Podaj typ kawy:");
			System.out.println("1 - Z laktoza");
			System.out.println("2 - Bez laktozy");
			System.out.print("Typ: ");
			fluidType = read.nextInt();
			}while(fluidType < 3 && fluidType > 0);
		do {
			System.out.print("Podaj ilosc mleka: ");
			temp = read.nextInt();
			System.out.println();
			waterPump.getFluid(milk[fluidType], temp);
			valid = checkError(milkPump);
		}while(valid != true);
			do {
			System.out.println("Podaj typ kawy:");
			System.out.println("1 - Arabica");
			System.out.println("2 - Robusta");
			System.out.println("3 - Liberica");
			System.out.println("4 - Blue Mountain");
			System.out.println("5 - Antica");
			System.out.print("Typ: ");
			fluidType = read.nextInt();
			}while(fluidType < 6 && fluidType > 0);
		do {
			System.out.print("Podaj ilosc kawy: ");
			temp = read.nextInt();
			System.out.println();
			grinder.Grind(coffee[fluidType], temp);
			valid = checkError(grinder);
		}while (valid != true);
		
		
		return 1;
}
    
    public int MakeFromRecipe(Coffee []coffee, Milk []milk, Container water, Container sugar, int[][] recipe, int x){
    		boolean valid;
    		valid = checkError(coffee[0]);
    		valid = checkError(coffee[1]);
    		valid = checkError(coffee[2]);
    		valid = checkError(coffee[3]);
    		valid = checkError(coffee[4]);
    		valid = checkError(milk[0]);
    		valid = checkError(milk[1]);
    		valid = checkError(sugar);
    		valid = checkError(water);
    		valid = checkError(waterCoil);
    		valid = checkError(milkCoil);
    		valid = checkError(waterPump);
    		valid = checkError(milkPump);
   			valid = checkError(grinder);
   			
   			
    		return 1;
    }
    
    
    static Container Water = new Container(2000);
    static Container Sugar = new Container(2000,15);
    static FluidPump waterPump = new FluidPump();
    static FluidPump milkPump = new FluidPump();
    static Grinder grinder = new Grinder();
    static Coil waterCoil = new Coil();
    static Coil milkCoil = new Coil();
    static int[][] Recipes = new int[10][8];
    
    public static void main(String[] args) {
        Coffee[] coffee = new Coffee[5];
        Milk[] milk = new Milk[2];
        coffee[0] = new Coffee(2000);
        coffee[1] = new Coffee(2000, coffeeType.robusta);
        coffee[2] = new Coffee(2000, coffeeType.liberica);
        coffee[3] = new Coffee(2000, coffeeType.blueMountain);
        coffee[4] = new Coffee(2000, coffeeType.antiqua);
        milk[0] = new Milk(2000);
        milk[1] = new Milk(2000, milkType.lactoseFree);
       // Przepis pierwszy
        Recipes[1][0]= 250; Recipes[1][1]= 150; Recipes[1][2]= 16; Recipes[1][3]= 16; Recipes[1][4]= 50; Recipes[1][5]= 150; Recipes[1][6]= 50; Recipes[1][7]= 5;
        Make(coffee, milk, Water, Sugar);
    }
}
