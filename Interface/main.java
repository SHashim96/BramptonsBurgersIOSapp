package Interface;

import java.io.IOException;
import java.util.Scanner;
import Controller.BurgerMangement;
import DAO.Model;

public class main {
	final static String MESSAGE_INVALID_OPTION = "\n \t\t Invalid option, please try again \n";
	
	public static void main(String[ ] args) {
		System.out.println("Welcome to Brampton Burgers");
		Model.CreateDatabase();
		Model.insertInitialProducts();
		displayMenu();
	}
	
	public static void displayMenu() {
		System.out.println("\t Please select an option");
		System.out.println("\t\t 1. See burger types");
		System.out.println("\t\t 2. See burger add-ons");
		System.out.println("\t\t 3. Build a burger");	
		System.out.println("\t\t 4. Order History");	
		System.out.print("\n\t\t --> ");	
		
		try {
			Scanner sc = new Scanner(System.in);
			gatherUserInput(sc.nextInt());
		}
		catch(Exception ex) {
			displayErrorMessage();
			displayMenu();
		}
	}
	
	public static void gatherUserInput(Integer userSelection) {
		switch(userSelection) {
		case 1:
			BurgerMangement.displayBurgerOptions();
			break;
		case 2:
			BurgerMangement.displayAdditionOptions();
			break;
		case 3:
			BurgerMangement.buildABurger();
			break;
		case 4:
			BurgerMangement.displayOrderHistory();
			break;
		default:
			displayErrorMessage();
			displayMenu();
			break;
		}
	}
	
	public static void displayErrorMessage() {
		try {
			System.in.read(new byte[System.in.available()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(MESSAGE_INVALID_OPTION);
	}
}
