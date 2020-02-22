package Model;

public abstract class Burger {
	Integer Id;
	String burgerName;
	Double burgerCost;
	final Integer burgerMaximumToppings;
	
	public double getBurgerCost() {
		return this.burgerCost;
	}
	
	public double getBurgerIdentifier() {
		return this.Id;
	}
	
	public String getBurgerName() {
		return this.burgerName;
	}
	
	public Integer getBurgerMaxAdditions() {
		return this.burgerMaximumToppings;
	}
	
	public Burger(String burgerName, Double burgerCost, Integer burgerMaximumToppings, Integer Id) {
		this.burgerName = burgerName;
		this.burgerCost = burgerCost;
		this.burgerMaximumToppings = burgerMaximumToppings; 
		this.Id = Id; 
	}
	
	public Burger() {
		this.burgerName = "Random Burger";
		this.burgerCost = 10.50;
		this.burgerMaximumToppings = 1; 
	}
	
	@Override 
	public String toString() {
		return "\n\t\t" + this.burgerName +
				" \n\t\t\t Cost: $" + this.burgerCost +
				" \n\t\t\t Maximum toppings: " + this.burgerMaximumToppings + " \n";
	}
}
