
import java.util.HashMap;

import MyTicket.FullMyTi;
import MyTicket.JuniorMyTi;
import MyTicket.SeniorMyTi;
import MyTicket.Ticket;

public class User {
	
	private String id,name,type,email;
	
	static HashMap<String, User> users = new HashMap<String, User>();
	
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
	
	
	public static void addUser(String id,String name,String type,String email){
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
		else {System.out.println("User ID already exists!");}
	}
	
	static String getUserType(String id) {
		// Return the type of a specific user
			String type = users.get(id).getType();
			return type;
	}
	

	
	
	

	}
