package Model;

public class Addition {
	Integer Id;
	String additionName;
	Double additionCost;
	final Boolean isDeluxeCompatible;
	
	public double getAdditionCost() {
		return this.additionCost;
	}
	
	public boolean isDeluxeCompatible() {
		return this.isDeluxeCompatible;
	}
	
	public String getAdditionName() {
		return this.additionName;
	}
	
	public double getAdditionIdentifier() {
		return this.Id;
	}
	
	public Addition(String additionName, Double additionCost, Boolean isDeluxeCompatible, Integer Id) {
		this.additionName = additionName;
		this.additionCost = additionCost;
		this.isDeluxeCompatible = isDeluxeCompatible; 
		this.Id = Id; 
	}
	
	public Addition() {
		this.additionName = "Random Addition";
		this.additionCost = 0.50;
		this.isDeluxeCompatible = false; 
	}
	
	@Override 
	public String toString() {
		return "\n\t\t" + this.additionName +
				" \n\t\t Cost: $" + this.additionCost + " \n";
	}
}
