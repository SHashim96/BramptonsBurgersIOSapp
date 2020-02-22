package Controller;

import java.util.HashSet;
import java.util.Set;

import DAO.Model;
import Interface.main;
import Model.*;

public class ReceiptGenerator {
	public Burger selectedBurger; 
	Set<Addition> allAdditions = new HashSet<Addition>();
	
	public void setBurgerOption(Burger currentBurger) {
		this.selectedBurger = currentBurger;
	}
	
	public boolean additionCapacityRemaining() {
		return selectedBurger.getBurgerMaxAdditions() < allAdditions.size(); 
	}
	
	public void addToping(Addition currentAddition) throws NonCompatibleAddition {
		if(selectedBurger.getBurgerName().contains("Deluxe") && !currentAddition.isDeluxeCompatible())
			throw new NonCompatibleAddition();
		else
			this.allAdditions.add(currentAddition);
	}
	
	public double totalGenerator() {
		double tmpRunningTotal = 0;
		tmpRunningTotal += selectedBurger.getBurgerCost();
		for(Addition currentAddition : allAdditions) {
			tmpRunningTotal += currentAddition.getAdditionCost();
		}
		return tmpRunningTotal;
	}
	
	public void printOrderTotal() {
		double currentTotal = totalGenerator();
		System.out.println("\n\t\t Your order total is " + currentTotal);
		System.out.println("\t\t Chosen burger: " + selectedBurger.getBurgerName());
		if(allAdditions.size() > 0 )
			System.out.println("\t\t Chosen additions: " + allAdditions.toString());
	
		saveOrderTransaction();
		main.displayMenu();
	}
	
	public void saveOrderTransaction() {
		Model.insertOrder(selectedBurger, allAdditions, totalGenerator());
	}
	
	
	public class NonCompatibleAddition extends Exception {}
}
