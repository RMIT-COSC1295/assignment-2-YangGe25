package MyTicket;

import java.util.HashMap;

public class FullMyTi extends Ticket{

	public FullMyTi(String id) {super(id);}
	
	static HashMap<String,FullMyTi> fullMyTis = new HashMap<String,FullMyTi>();
	

	public static void addTicket(String id){
		// add a new ticket, and put it into a HashMap whose key is id, and value credit
		FullMyTi newFullMyTi = new FullMyTi(id);
		fullMyTis.put(id,newFullMyTi);
	}
	
	public static void showTicketInfo(String id) {
		//print the information of specific user
			if (fullMyTis.containsKey(id)) {System.out.println("id: " + id + ", credit: " + fullMyTis.get(id).getCredit());}
			else {System.out.println("User doesn't exist!");}
	}
	
	public static void main(String[] args) {
		
	}

}
