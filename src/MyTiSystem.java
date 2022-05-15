import java.util.ArrayList;
import java.util.Scanner;

import MyTicket.FullMyTi;
import MyTicket.JuniorMyTi;
import MyTicket.SeniorMyTi;
import Exceptions.UserDoesNotExist;

public class MyTiSystem {
	
	final static double CREDIT_LIMIT = 100.0;  // maximum allowed credit
	final static int LEGAL_MULTIPLE = 5; // multiple that we can re-charge by
	
	static Scanner sc = new Scanner(System.in);// create a new Scanner from standard input
	
	/**
	 * main program for assignment
	 * - this contains the main menu loop
	 */
	public static void main(String[] args) {
		// Add three types of users 
		User.addUser("lc", "Lawrence Cavedon",  "Senior", "lawrence.cavedon@rmit.edu.au");
		User.addUser("vm", "Vu Mai", "Adult", "vuhuy.mai@rmit.edu.au");
		User.addUser("gy","Ge Yang","Junior","s3911292@student.rmit.edu.au");
		// Add five stations as required in the specification
		Station.addStation("Cental", 1);
		Station.addStation("Flagstaff", 1);
		Station.addStation("Richmond", 1);
		Station.addStation("Lilydale", 2);
		Station.addStation("Epping", 2);
		// main menu loop: print menu, then do something depending on selection
		boolean invalidInput = true;
		do {
			printMainMenu();
			
			int option = 0;
			try {
				option = Integer.parseInt(sc.next());
				int[] validInput = new int[]{1,2,3,4,5,6,7,8};
				boolean index = false;
				for(int i=0;i<validInput.length;i++) {if (validInput[i] == option ) {index = true;}}
				if (!index){System.out.println("Please input an integer from 1 to 8!\n");}
				} catch(Exception e) {System.out.println("Please input an integer!\n");}
			// perform correct action, depending on selection	
		switch (option) {
			case 1: purchasePass();
				break;
			case 2: recharge();
				break;
			case 3: showCredit();
				break;
			case 4: printUserReports();
				break;
			case 5: updatePrice();
				break;
			case 6: showStatistics();
				break;
			case 7: addNewUser();
				break;
			case 8: 
				System.out.println("Goodbye!");
				sc.close();
				invalidInput = false;
				break;}
		}while(invalidInput);
	}
	
	/*
	 * Print the main menu
	 */
	static void printMainMenu() {
		System.out.println();
		System.out.println("Select an option:");
		System.out.println("1. Buy a Journey for a User");
		System.out.println("2. Recharge MyTi card for a User");
		System.out.println("3. Show remaining credit for a User");
		System.out.println("4. Print User Reports");
		System.out.println("5. Update price");
		System.out.println("6. Show Station statistics");
		System.out.println("7. Add a new User");
		System.out.println("8. Quit");
		System.out.print("Your option: ");
	}
	
	static void purchasePass() {
		System.out.println("1");
	}
	static void recharge() {
		System.out.println();
		System.out.println("User ID?");
		String id = sc.next().strip();
		double credit = 0.0;
		String type = User.getUserType(id);
		try{
			if (type == "Adult") {credit = FullMyTi.getTicketCredit(id);}
			else if (type == "Junior") {credit = JuniorMyTi.getTicketCredit(id);}
			else if (type == "Senior") {credit = SeniorMyTi.getTicketCredit(id);}
		} catch(Exception e) {System.out.println("User doesn't exist!");}
		
		System.out.println("How much credit do you want to add: ");
		double amt = sc.nextDouble();
		
		if(amt < 0) {
			System.out.println("Please input a positive amount!");
			recharge();
		}else if ((credit + amt) > CREDIT_LIMIT) {
			System.out.println("That takes you over the credit limit. Please enter a smaller amount.");
			recharge();
		} else if (amt % LEGAL_MULTIPLE != 0) {
			System.out.println("Charge amounts must be in multiples of " + LEGAL_MULTIPLE + ".");
			recharge();
		} else { // valid amount --> add to the MyTi credit
			if (type == "Adult") {FullMyTi.topUp(id,amt);}
			else if (type == "Junior") {JuniorMyTi.topUp(id,amt);}
			else if (type == "Senior") {SeniorMyTi.topUp(id,amt);}
			System.out.printf("Successfully added %.2f to %s\n", amt, id);
		}
		
	}
	
	static void showCredit() {
		/*
		 * Display the remaining credit of specific user
		 */
		System.out.println();
		System.out.println("User ID?");
		String id = sc.next().strip();
		try{
			String type = User.getUserType(id);
			double credit = 0.0;
			if (type == "Adult") {credit = FullMyTi.getTicketCredit(id);}
			else if (type == "Junior") {credit = JuniorMyTi.getTicketCredit(id);}
			else if (type == "Senior") {credit = SeniorMyTi.getTicketCredit(id);}
			System.out.printf("%s's remaining credit is $%.2f\n ", id, credit);
		} catch(Exception e) {System.out.println("User doesn't exist!");}
		
	}
	
	static void printUserReports() {
		System.out.println("4");
	}
	static void updatePrice() {
		System.out.println("5");
	}
	static void showStatistics() {
		System.out.println("6");
	}


	/*
	 * Add a new user
	 */
	static void addNewUser() {
		/*
		 * Input id, name, type and email
		 * Type should be "Adult","Junior" or "Senior"
		 */
		System.out.println();
		System.out.println("User ID?");
		String id = sc.next().strip();
		
		System.out.println("Name?");
		String name = sc.next().strip();
		
		System.out.println("Type?(Choose from Adult,Junior and Senior)");
		String type= sc.next().strip();
				
		ArrayList<String> lst = new ArrayList();
		lst.add("Adult");
		lst.add("Senior");
		lst.add("Junior");
		if(lst.contains(type)) {
			System.out.println("Email?");
			String email = sc.next().strip();
			User.addUser(id, name, type, email);}
		else{System.out.println("Wrong Type!");}
		}	
	}
