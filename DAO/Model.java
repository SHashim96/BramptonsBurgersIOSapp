package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Model.*;
import StaticData.data;

public class Model {
	private final static String DB_Directory = "jdbc:sqlite://localhost/";
	public static void CreateDatabase() {
		try {
	        Connection conn = DriverManager.getConnection(DB_Directory);
	        
	        Statement statement = conn.createStatement();
	        statement.execute(getBurgerModelCreationQuery());
	        statement.execute(getAdditionModelCreationQuery());
	        statement.execute(getOrderModelCreationQuery());
	
	        statement.close();
	        conn.close();

    } catch (SQLException e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }
	}
	
	public static String getBurgerModelCreationQuery() {
		return
				 "CREATE TABLE Burger ("
				+ "burgerId Id, "
				+ "isHealthyBurger Boolean"
				+ "isDeluxeBurger Boolean"
				+ "isBasicBurger Boolean";
	}
	
	public static String getAdditionModelCreationQuery() {
		return
				 "CREATE TABLE Addition ("
				+ "additionId Id, "
				+ "isCarrot Boolean"
				+ "isChips Boolean"
				+ "isDrink Boolean"
				+ "isLettuce Boolean"
				+ "isSardines Boolean"
				+ "isTomato Boolean";
	}
	
	public static String getOrderModelCreationQuery() {
		return
				 "CREATE TABLE Order ("
				+ "saleId Id, "
				+ "burgerId Burger"
				+ "addition1 Addition"
				+ "addition2 Addition"
				+ "addition3 Addition"
				+ "addition4 Addition"
				+ "addition5 Addition"
				+ "addition6 Addition"
				+ "orderTotal Number";
	}
	
	public static void getOrders() {
		
		try {
	        Connection conn = DriverManager.getConnection("jdbc:sqlite:localhost");
	        conn.setAutoCommit(false);
	        Statement statement = conn.createStatement();
			statement.execute("SELECT * FROM Orders");
	        ResultSet results = statement.getResultSet();
	        while(results.next()) {
	        	System.out.println("BurgerId: " + results.getString("burgerId"));
	        	System.out.println("First addition: " + results.getString("addition1"));
	        	System.out.println("Secondary addition: " + results.getString("addition2"));
	        	System.out.println("Third addition: " + results.getString("addition3"));
	        	System.out.println("Fourth addition: " + results.getString("addition4"));
	        	System.out.println("Five addition: " + results.getString("addition5"));
	        	System.out.println("Six addition: " + results.getString("addition6"));
	        	System.out.println("Total Cost: " + results.getString("orderTotal"));
	        }
	
	        results.close();
	
	        statement.close();
	        conn.close();
		}
		catch(Exception ex) {
			
		}
	}
	
	public static void insertInitialProducts() {
		try {
            Connection conn = DriverManager.getConnection(DB_Directory);
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            
            // INSERT BURGERS
            for(String currentBurgerInsertQuery : data.burgerInsertionQueries()) {
            	statement.execute(currentBurgerInsertQuery);
            }
            
            // INSERT ADDITIONS
            for(String currentAdditionInsertQuery : data.additionInsertionQueries()) {
            	statement.execute(currentAdditionInsertQuery);
            }
            
            statement.close();
            conn.close();


        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
	}
	
	public static void insertOrder(Burger selectedBurger, Set<Addition> theAdditions, Double orderTotal) {
		System.out.println("Debugger hit");
		Integer currentBurgerId = null; // COULD BE MADE DYNAMIC FOR SCALABILITY LATERS - HARDCODED TEMPORARILY
		List<Integer> currentAdditions = new ArrayList<Integer>();
		switch(selectedBurger.getBurgerName()) {
		case "Basic Burger":
			currentBurgerId = 0;
			break;
		case "Healthy Burger":
			currentBurgerId = 1;
			break;
		case "Deluxe Burger":
			currentBurgerId = 2;
			break;
		}
		
		for(Addition currentAddition : theAdditions ) {
			switch(currentAddition.getAdditionName()){
			case "Carrot":
				currentAdditions.add(0);
				break;
			case "Chips":
				currentAdditions.add(1);
				break;
			case "Drink":
				currentAdditions.add(2);
				break;
			case "Lettuce":
				currentAdditions.add(3);
				break;
			case "Sardines":
				currentAdditions.add(4);
				break;
			case "Tomato":
				currentAdditions.add(5);
				break;
			}
		}
		

		
		String orderInsertionQuery = "INSERT INTO Order";
		orderInsertionQuery += " (Burger, addition1, addition2, addition3, addition4,addition5,addition6,orderTotal)";
		orderInsertionQuery += " VALUES (";
		orderInsertionQuery += currentBurgerId + ", ";
		
		Integer currentIndex;
		for(Integer i=0; i<=6; i ++) {
			currentIndex = i+1;
			try {
				orderInsertionQuery += "addition" + currentIndex;
				orderInsertionQuery += " " + currentAdditions.get(i) + ", ";
			}
			catch(Exception e) {
				orderInsertionQuery += "addition" + currentIndex;
				orderInsertionQuery += "null, ";
			}
		}
		
		orderInsertionQuery += orderTotal ;
		orderInsertionQuery += ")";
		
		try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\database\\music\\testDB.db");
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute(orderInsertionQuery);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
	}
}
