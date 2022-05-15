package MyTicket;

import java.util.HashMap;

public class FullMyTi extends Ticket{

	public FullMyTi(String id) {super(id);}
	
	static HashMap<String,FullMyTi> fullMyTis = new HashMap<String,FullMyTi>();
	
	public static void addTicket(String id){
		// Add a new ticket, and put it into a HashMap whose key is id, and value is FullMyTi
		FullMyTi newFullMyTi = new FullMyTi(id);
		fullMyTis.put(id,newFullMyTi);
	}
	
	public static double getTicketCredit(String id) {
		// Print the credit of a specific FullMyTi user
			return fullMyTis.get(id).getCredit();
	}
	
	public static void topUp(String id, double amt) {
		// Top up the ticket of a specific FullMyTi user
			double credit= fullMyTis.get(id).getCredit();
			double newCredit = credit + amt;
			fullMyTis.get(id).setCredit(newCredit);
	}
	


}
