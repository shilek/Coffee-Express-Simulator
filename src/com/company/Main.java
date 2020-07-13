package com.company;
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
        else if (con.getValid() == Errors.errorGtValue) {System.out.println("Przekroczono dopuszczalna wartosc lub jest za malo skladnika w kontenerze."); return false;}
        else if (con.getValid() == Errors.errorMaxValue) {System.out.println("W kontenerze jest za duzo skladnika."); return false;}
        else if (con.getValid() == Errors.errorLtValue) {System.out.println("Nie mozna podac wartosci mniejszej niz 0."); return false;}
        else return true;
    }
    
    public static int Make(Coffee []coffee, Milk []milk, Container water, Container sugar) throws InterruptedException {
		int tWaterPressure, tMilkPressure, tWaterTemp, tMilkTemp, coffeeType, milkType, tCoffeeValue, tMilkValue, tWaterValue, tSugarValue;
    	boolean valid;

		for (Coffee value : coffee) {
			value.check();
			valid = checkError(value);
			if (!valid) return 0;
		}
		for (Milk value : milk) {
			value.check();
			valid = checkError(value);
			if (!valid) return 0;
		}
		water.check();
		valid = checkError(water);
		if (!valid) return 0;
		sugar.check();
		valid = checkError(sugar);
		if (!valid) return 0;

		do {
			System.out.print("Podaj cisnienie wody od 9 do 18 barow (domyslnie 16): ");
			tWaterPressure = read.nextInt();
			System.out.println();
			waterPump.checkPressure(tWaterPressure);
			valid = checkError(waterPump);
		}while(!valid);

		do {
			System.out.print("Podaj cisnienie mleka od 9 do 18 barow (domyslnie 16): ");
			tMilkPressure = read.nextInt();
			System.out.println();
			milkPump.checkPressure(tMilkPressure);
			valid = checkError(milkPump);
		}while(!valid);

		do {
			System.out.print("Podaj temperature grzalki wody do 250 stopni (domyslnie 250): ");
			tWaterTemp = read.nextInt();
			System.out.println();
			waterCoil.check(tWaterTemp);
			valid = checkError(waterCoil);
		}while(!valid);

		do {
			System.out.print("Podaj temperature grzalki mleka do 250 stopni (domyslnie 50): ");
			tMilkTemp = read.nextInt();
			System.out.println();
			milkCoil.check(tMilkTemp);
			valid = checkError(milkCoil);
		}while(!valid);

		do {
			System.out.println("Podaj typ kawy:");
			for(int i=0; i<coffee.length; i++) {
				System.out.println((i + 1) + " - " +coffee[i].getType());
			}
			System.out.print("Typ: ");
			coffeeType = read.nextInt();
		}while(coffeeType > 6 || coffeeType < 0);

		do {
			System.out.print("Podaj ilosc kawy: ");
			tCoffeeValue = read.nextInt();
			System.out.println();
			coffee[coffeeType-1].checkValue(tCoffeeValue);
			valid = checkError(coffee[coffeeType-1]);
		}while (!valid);

		do {
			System.out.println("Podaj typ mleka:");
			for(int i=0; i<milk.length; i++) {
				System.out.println((i + 1) + " - " +milk[i].getType());
			}
			System.out.print("Typ: ");
			milkType = read.nextInt();
		}while(milkType > 3 || milkType < 0);

		do {
			System.out.print("Podaj ilosc mleka: ");
			tMilkValue = read.nextInt();
			System.out.println();
			milk[milkType-1].checkValue(tMilkValue);
			valid = checkError(milk[milkType-1]);
		}while(!valid);

		do {
			System.out.print("Podaj ilosc wody: ");
			tWaterValue = read.nextInt();
			System.out.println();
			water.checkValue(tWaterValue);
			valid = checkError(water);
		}while(!valid);

		do {
			System.out.print("Podaj ilosc cukru (lyzeczki, max 3): ");
			tSugarValue = read.nextInt();
			System.out.println();
			sugar.checkValue(tSugarValue*5);
			valid = checkError(sugar);
		}while(!valid);

		waterPump.setPressure(tWaterPressure);
		milkPump.setPressure(tMilkPressure);
		grinder.Grind(coffee[coffeeType-1], tCoffeeValue);
		int finalTWaterTemp = tWaterTemp;
		Thread coil1 = new Thread(() -> waterCoil.Heat(finalTWaterTemp));
		int finalTMilkTemp = tMilkTemp;
		Thread coil2 = new Thread(() -> waterCoil.Heat(finalTMilkTemp));
		coil1.start();
		coil2.start();
		coil1.join();
		coil2.join();
		int finalTWaterValue = tWaterValue;
		Thread pump1 = new Thread(() -> waterPump.getFluid(water, finalTWaterValue));
		int finalMilkType = milkType;
		int finalTMilkValue = tMilkValue;
		Thread pump2 = new Thread(() -> milkPump.getFluid(milk[finalMilkType -1], finalTMilkValue));
		pump1.start();
		pump2.start();
		pump1.join();
		pump2.join();
		milkCoil.Heat(tMilkTemp);
		mainPump.mainPump(waterPump, milkPump, grinder);
		System.out.println("Mieszam kawe, wode i mleko.");
		cups.setValue(cups.getValue()-1);
		System.out.println("Podaje kubek.");
		System.out.println("Nalewam.");
		sugar.setValue(sugar.getValue()-(tSugarValue*5));
		System.out.println("Sypie cukier.");


		System.out.println("Kawa zrobiona.");
		System.out.println();
		System.out.println("               _..,----,.._");
		System.out.println("            .-;'-.,____,.-';");
		System.out.println("           (( |    o  o    |");
		System.out.println("            `))    .__.    ;");
		System.out.println("             ` \\          /");
		System.out.println("            .-' `,.____.,' '-.");
		System.out.println("           (     '------'     )");
		System.out.println("            `-=..________..--'");
		System.out.println(" ");
		return 1;

}
    
    public static int makeFromRecipe(Coffee []coffee, Milk []milk, Container water, Container sugar, int[][] recipe, int nr) throws InterruptedException {
    	boolean valid;

			for (Coffee value : coffee) {
				value.check();
				valid = checkError(value);
				if (!valid) return 0;
			}
			for (Milk value : milk) {
				value.check();
				valid = checkError(value);
				if (!valid) return 0;
			}
			water.check();
			valid = checkError(water);
			if (!valid) return 0;
			sugar.check();
			valid = checkError(sugar);
			if (!valid) return 0;

			coffee[recipe[nr][4]].checkValue(recipe[nr][5]);
			valid = checkError(coffee[recipe[nr][4]]);
			if (!valid) return 0;
			milk[recipe[nr][6]].checkValue(recipe[nr][7]);
			valid = checkError(milk[recipe[nr][6]]);
			if (!valid) return 0;
			water.checkValue(recipe[nr][8]);
			valid = checkError(water);
			if (!valid) return 0;
			sugar.checkValue(recipe[nr][9]);
			valid = checkError(sugar);
			if (!valid) return 0;

			waterPump.setPressure(recipe[nr][0]);
			milkPump.setPressure(recipe[nr][1]);
			grinder.Grind(coffee[recipe[nr][4]], recipe[nr][5]);
			Thread coil1 = new Thread(() -> waterCoil.Heat(recipe[nr][2]));
			Thread coil2 = new Thread(() -> milkCoil.Heat(recipe[nr][3]));
			coil1.start();
			coil2.start();
			coil1.join();
			coil2.join();
			Thread pump1 = new Thread(() -> waterPump.getFluid(water, recipe[nr][8]));
			pump1.start();
			Thread pump2 = new Thread(() -> milkPump.getFluid(milk[recipe[nr][6]], recipe[nr][7]));
			pump2.start();
			pump1.join();
			pump2.join();
			mainPump.mainPump(waterPump, milkPump, grinder);
			System.out.println("Mieszam kawe, wode i mleko.");
			cups.setValue(cups.getValue()-1);
			System.out.println("Podaje kubek.");
			System.out.println("Nalewam.");
			sugar.setValue(sugar.getValue()-(recipe[nr][9]*5));
			System.out.println("Sypie cukier.");

		System.out.println("Kawa zrobiona.");
		System.out.println();
		System.out.println("               _..,----,.._");
		System.out.println("            .-;'-.,____,.-';");
		System.out.println("           (( |    o  o    |");
		System.out.println("            `))    .__.    ;");
		System.out.println("             ` \\          /");
		System.out.println("            .-' `,.____.,' '-.");
		System.out.println("           (     '------'     )");
		System.out.println("            `-=..________..--'");
		System.out.println(" ");
		return 1;
    }

    public static void makeRecipe(Coffee []coffee, Milk []milk, Container water, Container sugar, int [][]recipe, int nr){
    	boolean valid;
		do {
			System.out.print("Podaj cisnienie wody od 9 do 18 barow (domyslnie 16): ");
			recipe[nr][0] = read.nextInt();
			System.out.println();
			waterPump.checkPressure(recipe[nr][0]);
			valid = checkError(waterPump);
		}while(!valid);

		do {
			System.out.print("Podaj cisnienie mleka od 9 do 18 barow (domyslnie 16): ");
			recipe[nr][1] = read.nextInt();
			System.out.println();
			milkPump.checkPressure(recipe[nr][1]);
			valid = checkError(milkPump);
		}while(!valid);

		do {
			System.out.print("Podaj temperature grzalki wody do 250 stopni (domyslnie 250): ");
			recipe[nr][2] = read.nextInt();
			System.out.println();
			waterCoil.check(recipe[nr][2]);
			valid = checkError(waterCoil);
		}while(!valid);

		do {
			System.out.print("Podaj temperature grzalki mleka do 250 stopni (domyslnie 50): ");
			recipe[nr][3] = read.nextInt();
			System.out.println();
			milkCoil.check(recipe[nr][3]);
			valid = checkError(milkCoil);
		}while(!valid);

		do {
			System.out.println("Podaj typ kawy:");
			for(int i=0; i<coffee.length; i++) {
				System.out.println((i + 1) + " - " +coffee[i].getType());
			}
			System.out.print("Typ: ");
			recipe[nr][4] = read.nextInt()-1;
		}while(recipe[nr][4] >= coffee.length || recipe[nr][4] < 0);

		do {
			System.out.print("Podaj ilosc kawy: ");
			recipe[nr][5] = read.nextInt();
			System.out.println();
			coffee[recipe[nr][4]].checkValue(recipe[nr][5]);
			valid = checkError(grinder);
		}while (!valid);

		do {
			System.out.println("Podaj typ mleka:");
			for(int i=0; i<milk.length; i++) {
				System.out.println((i + 1) + " - " +milk[i].getType());
			}
			System.out.print("Typ: ");
			recipe[nr][6] = read.nextInt()-1;
		}while(recipe[nr][6] >= milk.length || recipe[nr][6] < 1);

		do {
			System.out.print("Podaj ilosc mleka: ");
			recipe[nr][7] = read.nextInt();
			System.out.println();
			milk[recipe[nr][6]-1].checkValue(recipe[nr][7]);
			valid = checkError(milk[recipe[nr][6]-1]);
		}while(!valid);

		do {
			System.out.print("Podaj ilosc wody: ");
			recipe[nr][8] = read.nextInt();
			System.out.println();
			water.checkValue(recipe[nr][8]);
			valid = checkError(waterPump);
		}while(!valid);

		do {
			System.out.print("Podaj ilosc cukru (lyzeczki, max 3): ");
			recipe[nr][9] = read.nextInt();
			System.out.println();
			sugar.checkValue(recipe[nr][9]*5);
			valid = checkError(sugar);
		}while(!valid);

		System.out.println("Przepis zapisany pod numerem: "+(nr+1));
		System.out.println();
	}

	public static void showRecipe(int [][] recipe, int nr, coffeeType coffee, milkType milk){
    	System.out.println("Receptura nr: "+(nr+1));
    	System.out.println("Cisnienie wody: "+recipe[nr][0]);
		System.out.println("Cisnienie mleka: "+recipe[nr][1]);
		System.out.println("Temperatura grzalki wody: "+recipe[nr][2]);
		System.out.println("Temperatura grzalki mleka: "+recipe[nr][3]);
		System.out.println("Rodzaj kawy: "+coffee);
		System.out.println("Ilosc kawy: "+recipe[nr][5]);
		System.out.println("Rodzaj mleka: "+milk);
		System.out.println("Ilosc mleka: "+recipe[nr][7]);
		System.out.println("Ilosc wody: "+recipe[nr][8]);
		System.out.println("Ilosc lyzeczek cukru: "+recipe[nr][9]);
		System.out.println();
	}

    static Container cups = new Container(50,1);
    static Container Water = new Container(2000, 250);
    static Container Sugar = new Container(2000,15);
    static FluidPump waterPump = new FluidPump();
    static FluidPump milkPump = new FluidPump();
    static MainPump mainPump = new MainPump();
    static Grinder grinder = new Grinder();
    static Coil waterCoil = new Coil();
    static Coil milkCoil = new Coil();
    static int[][] Recipes = new int[10][10];
	static Coffee[] coffee = new Coffee[5];
    static Milk[] milk = new Milk[2];

    public static void main(String[] args) throws InterruptedException {

		coffee[0] = new Coffee(2000);
		coffee[1] = new Coffee(2000, coffeeType.Robusta);
		coffee[2] = new Coffee(2000, coffeeType.Liberica);
		coffee[3] = new Coffee(2000, coffeeType.BlueMountain);
		coffee[4] = new Coffee(2000, coffeeType.Antiqua);
		milk[0] = new Milk(2000);
		milk[1] = new Milk(2000, milkType.lactoseFree);
        int recipeNumber = 100;
        int exit = 0;
		int choice;

        //Reset przepisow
        for (int i=0; i < Recipes.length; i++){
        	Recipes[i][9] = 5;
		}

        //Przepis pierwszy
        Recipes[0][0]= 16; Recipes[0][1]= 16; Recipes[0][2]= 250; Recipes[0][3]= 50; Recipes[0][4]= 0; Recipes[0][5]= 100; Recipes[0][6]= 0; Recipes[0][7]= 50; Recipes[0][8]= 250; Recipes[0][9]= 1;


		while (exit != 1){
			System.out.println();
			System.out.println("Wpisz cyfre inna niz w menu aby wyjsc z ekspresu.");
			System.out.println("Jaka operacje chcialbys wykonac: ");
			System.out.println("1 - Kawa z wlasnego przepisu. ");
			System.out.println("2 - Utworzenie nowego przepisu. ");
			System.out.println("3 - Kawa z zapisanego przepisu. ");
			System.out.println("4 - Przegladanie przepisow. ");
			System.out.print("Wybor: ");
			choice = read.nextInt();

			if (choice == 1) {
				exit = Make(coffee, milk, Water, Sugar);
			}
			else if (choice == 2) {
				for (int i=0; i<Recipes.length; i++){
					if (Recipes[i][9] == 5){
					recipeNumber = i;
					break;
					}
				}
				if (recipeNumber != 100) makeRecipe(coffee, milk, Water, Sugar, Recipes, recipeNumber-1);
				else System.out.println("Nie ma miejsca na nowy przepis");
			}
			else if (choice == 3){
				System.out.print("Podaj numer przepisu (od 1 do 10): ");
				recipeNumber = read.nextInt();
				System.out.println();
				if (Recipes[recipeNumber-1][9] != 5){
					exit = makeFromRecipe(coffee, milk, Water, Sugar, Recipes, recipeNumber-1);
				}
				else System.out.println("Pod tym numerem nie ma zapisanego przepisu.");
			}
			else if (choice == 4){
				System.out.println("Numery pod ktorymi sa zapisane przepisy: ");
				for (int i=0; i<Recipes.length; i++){
					if (Recipes[i][9] != 5){
						System.out.print((i+1)+" ");
					}
				}
				System.out.println();
				System.out.print("Wybierz przepis ktory chcesz przegladac: ");
				recipeNumber = read.nextInt();
				if (Recipes[recipeNumber-1][9] != 5) showRecipe(Recipes, recipeNumber-1, coffee[Recipes[recipeNumber-1][4]].getType(), milk[Recipes[recipeNumber-1][6]].getType());
				else System.out.println("Pod tym numerem nie ma zapisanego przepisu.");
		}
			else exit = 1;
		}
    }
}
