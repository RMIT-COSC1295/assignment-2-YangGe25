package myti;

import java.util.HashMap;

public class User {
	
	private String id,name,type,email;
	
	public static HashMap<String, User> users = new HashMap<String, User>();
	public static HashMap<String, String> journeys = new HashMap<String, String> ();
	
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
			if (type.equals("Adult")) {FullMyTi.addTicket(id);}
			else if (type.equals("Junior")) {JuniorMyTi.addTicket(id);}
			else if (type.equals("Senior")) {SeniorMyTi.addTicket(id);}
			}
		else throw new UserExists(null);
	}
	
	// Return the type of a specific user
	public static String getUserType(String id) {
			String type = users.get(id).getType();
			return type;
	}
	
	// Get the remaining credit by user id
	public static double getUserCredit(String id) {
		String type = getUserType(id);
		double credit = 0.0;
		if (type.equals("Adult")) {credit = FullMyTi.getTicketCredit(id);}
		else if (type.equals("Junior")) {credit = JuniorMyTi.getTicketCredit(id);}
		else if (type.equals("Senior")) {credit = SeniorMyTi.getTicketCredit(id);}
		return credit;
	}
	
	// Recharge by user id
	public static void userRecharge(String id, double amt) {
		
		String type = getUserType(id);
		
		if (type.equals("Adult")) {
			FullMyTi.topUp(id,amt);
			}
		else if (type.equals("Junior")) {
			JuniorMyTi.topUp(id,amt);
			}
		else if (type.equals("Senior")) {
			SeniorMyTi.topUp(id,amt);
			}
	}
	
	
	// Record a journey of a user 
	public static void addJourney(String id, String startStation, String endStation, int departureTime, int arrivalTime, String day) {
		
		String journey = journeys.get(id);
		if (journey != null) {
			journey += startStation+":"+endStation+":"+departureTime+":"+arrivalTime+":"+day+"\n";
			//journey += String.format("%10s -> %10s | %4d ->%4d | %3s\n",startStation,endStation,departureTime,arrivalTime,day);
		} else 
			journey = startStation+":"+endStation+":"+departureTime+":"+arrivalTime+":"+day+"\n";
			//journey = String.format("%10s -> %10s | %4d ->%4d | %3s\n",startStation,endStation,departureTime,arrivalTime,day);
		
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
