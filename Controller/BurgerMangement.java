package Controller;

import java.util.Scanner;

import Controller.ReceiptGenerator.NonCompatibleAddition;
import DAO.Model;
import Interface.main;
import Model.*;
import StaticData.data;;


public class BurgerMangement {
	static data currentData = new data();
	static ReceiptGenerator currentConfiguration = new ReceiptGenerator();
	
	// BURGER CONFIGURATION LOGIC
	public static void buildABurger() {
			displayBurgerConfiguration();
			displayBurgerToppingConfigurations();
			
		// RUN APPLICABLE VALIDATION
		// DISPLAY BURGER TOTAL
	}
	
	// BURGER CHOICE CONTROLLER
	public static void displayBurgerConfiguration() {
		System.out.println("\n\t Please select an option");
		for(Burger currentBurger : currentData.getBurgers()) {
			System.out.println("\t\t " + currentBurger.getBurgerName() + " - Option# " + String.format("%.0f", currentBurger.getBurgerIdentifier()) );
		}

		System.out.print("\n\t\t --> ");
		
		Scanner sc = new Scanner(System.in);
		try {
			recordBurgerChoice(sc.nextInt());
		} catch (Exception e) {
			main.displayErrorMessage();
		}
	}
	
	public static void recordBurgerChoice(Integer burgerSelection) throws Exception {
		Burger currentBurgerSelection = new BasicBurger(1); // DEFAULT VALUE WILL BE OVER WRITTEN AT RUNTIME
		switch(burgerSelection) {
		case 1:
			currentBurgerSelection = new BasicBurger(1);
			break;
		case 2:
			currentBurgerSelection = new HealthyBurger(2);
			break;
		case 3:
			currentBurgerSelection = new DeluxeBurger(3);
			break;
			default:
				main.displayErrorMessage();
				break;
		}
		try {
			currentConfiguration.setBurgerOption(currentBurgerSelection);
		}
		catch(Exception ex) {
			throw new Exception("Couldn't find your choice of burger");
		}
	}

	public static void displayBurgerToppingConfigurations() {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < currentConfiguration.selectedBurger.getBurgerMaxAdditions(); i++) {
			System.out.println("\n\t Please select an option");
			for(Addition currentAddition : currentData.getAdditions()) {
				System.out.println("\t\t " + currentAddition.getAdditionName() + " - Option# " + String.format("%.0f", currentAddition.getAdditionIdentifier()));
			}
			
			System.out.print("\t\t Noting else - Option# 7 ");
			System.out.print("\n\t\t --> ");

			try {
				recordBurgerToppingConfigurations(sc.nextInt());
			} catch (Exception e) {
				main.displayErrorMessage();
			}
		}
		
		currentConfiguration.printOrderTotal();
	}
	
	public static void recordBurgerToppingConfigurations(Integer burgerSelection) throws Exception {
		Addition currentAdditionSelection = new Chips(1); // DEFAULT VALUE WILL BE OVER WRITTEN AT RUNTIME
		switch(burgerSelection) {
		case 1:
			currentAdditionSelection = new Chips(1);
			break;
		case 2:
			currentAdditionSelection = new Drink(2);
			break;
		case 3:
			currentAdditionSelection = new Sardines(3);
		case 4:
			currentAdditionSelection = new Carrot(4);
			break;
		case 5:
			currentAdditionSelection = new Tomato(5);
			break;
		case 6:
			currentAdditionSelection = new Lettuce(6);
			break;
		case 7:
			currentConfiguration.printOrderTotal();
			break;
			default:
				main.displayErrorMessage();
				break;
		}
		try {
			currentConfiguration.addToping(currentAdditionSelection);
		}
		catch(NonCompatibleAddition ex) {
			System.out.println("\n\t That option is not availble for the selected burger. Please try again");
			displayBurgerToppingConfigurations();
		}
		catch(Exception ex) {
			System.out.println("\n\t Unable to add topping. Please try again");
			displayBurgerToppingConfigurations();
		}
		
	}

	
	// DISPLAY LOGIC (BURGERS & ADDITIONS & ORDER HISTORY)
	public static void displayAdditionOptions() {		
		System.out.println("\n\t *** BRAMPTON BURGERS ADD-ONS ***");
		for(Addition currentAddition : currentData.getAdditions()) {
			System.out.println(currentAddition.toString());
		}
		System.out.println("\n\t *** YUM YUM YUM ***\n");
		
		main.displayMenu();
	}

	public static void displayBurgerOptions() {	
		System.out.println("\n\t *** BRAMPTON BURGERS ***");
		for(Burger currentBurger : currentData.getBurgers()) {
			System.out.println(currentBurger.toString());
		}
		System.out.println("\n\t *** YUM YUM YUM ***\n");
		
		main.displayMenu();
	}

	public static void displayOrderHistory() {	
		Model.getOrders();
		main.displayMenu();
	}
	
}
