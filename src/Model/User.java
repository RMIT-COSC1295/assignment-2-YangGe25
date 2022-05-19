package Model;

import java.util.HashMap;

import Exceptions.UserExists;
import MyTicket.FullMyTi;
import MyTicket.JuniorMyTi;
import MyTicket.SeniorMyTi;

public class User {
	
	private String id,name,type,email;
	
	static HashMap<String, User> users = new HashMap<String, User>();
	static HashMap<String, String> journeys = new HashMap<String, String> ();
	
	User(String id,String name,String type,String email){
		this.id = id;
		this.name = name;
		this.type = type;
		this.email = email;
	}
	
	public String getID() {return this.id;}
	public String getName() {return this.name;}
	public String getType() {return this.type;}
	public String getEmail() {return this.email;}
	
	
	public static void addUser(String id,String name,String type,String email) throws UserExists{
		/* 
		 * Add a new user, and put it into a HashMap whose key is id, and value is user itself
		 * And automatically create a ticket according to its type
		 */
		if(!users.containsKey(id)) {
			User newUser = new User(id,name,type,email);
			users.put(id, newUser);
			if (type == "Adult") {FullMyTi.addTicket(id);}
			else if (type == "Junior") {JuniorMyTi.addTicket(id);}
			else if (type == "Senior") {SeniorMyTi.addTicket(id);}
			System.out.println(id + " has been added successfully!");}
		else throw new UserExists(null);
	}
	
	// Return the type of a specific user
	public static String getUserType(String id) {
			String type = users.get(id).getType();
			return type;
	}
	
	// Record a journey of a user 
	public static void addJourney(String id, String startStation, String endStation, int departureTime, int arrivalTime, String day) {
		
		
		String journey = journeys.get(id);
		if (journey != null) {
			journey += String.format("%10s -> %10s | %4d ->%4d | %3s\n",startStation,endStation,departureTime,arrivalTime,day);
		} else journey = String.format("%10s -> %10s | %4d ->%4d | %3s\n",startStation,endStation,departureTime,arrivalTime,day);
		
		journeys.put(id,journey);
		
	}
	
	// Print the journey record
	public static void userReport() {
		for(String id:journeys.keySet()) {
			System.out.println("Journeys taken by " + id +":");
			System.out.printf(journeys.get(id));
		}
	}

}
