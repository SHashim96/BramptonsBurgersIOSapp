package StaticData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Model.Addition;
import Model.BasicBurger;
import Model.Burger;
import Model.Carrot;
import Model.Chips;
import Model.DeluxeBurger;
import Model.Drink;
import Model.HealthyBurger;
import Model.Lettuce;
import Model.Sardines;
import Model.Tomato;

public class data {

	Set<Addition> allAdditions = new HashSet<Addition>();
	Set<Burger> allBurgers = new HashSet<Burger>();
	
	public Set<Addition> getAdditions(){
		return this.allAdditions;
	} 
	
	public Set<Burger> getBurgers(){
		return this.allBurgers;
	} 
	
	public data() {
		// POPULATE BURGERS
		allBurgers.add(new BasicBurger(1));
		allBurgers.add(new HealthyBurger(2));
		allBurgers.add(new DeluxeBurger(3));
		
		// POPULATE OPTIONS
		allAdditions.add(new Chips(1));
		allAdditions.add(new Drink(2));
		allAdditions.add(new Sardines(3));
		allAdditions.add(new Carrot(4));
		allAdditions.add(new Tomato(5));
		allAdditions.add(new Lettuce(6));
	}
	
	public static String[] burgerInsertionQueries(){
		return new String[] {
			"INSERT INTO Burger (isBasicBurger, isHealthyBurger, isDeluxeBurger) VALUES(true, false, false)",
			"INSERT INTO Burger (isBasicBurger, isHealthyBurger, isDeluxeBurger) VALUES(false, true, false)",
			"INSERT INTO Burger (isBasicBurger, isHealthyBurger, isDeluxeBurger) VALUES(false, false, true)"
		};

	}
	
	public static String[] additionInsertionQueries(){
		return new String[] {
			"INSERT INTO Addition (isCarrot, isChips, isDrink, isLettuce, isSardines, isTomato) VALUES(true, false, false,true,false,false)",
			"INSERT INTO Addition (isCarrot, isChips, isDrink, isLettuce, isSardines, isTomato) VALUES(false, true, false,false,false,false)",
			"INSERT INTO Addition (isCarrot, isChips, isDrink, isLettuce, isSardines, isTomato) VALUES(false, false, true,false,false,false)",
			"INSERT INTO Addition (isCarrot, isChips, isDrink, isLettuce, isSardines, isTomato) VALUES(false, false, false,true,false,false)",
			"INSERT INTO Addition (isCarrot, isChips, isDrink, isLettuce, isSardines, isTomato) VALUES(false, false, false,false,true,false)",
			"INSERT INTO Addition (isCarrot, isChips, isDrink, isLettuce, isSardines, isTomato) VALUES(false, false, false,false,false,true)"
		};
	}
}
