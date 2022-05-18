package MyTicket;

import java.util.HashMap;
import java.util.Set;

import Exceptions.InsufficientCredit;

public class JuniorMyTi extends Ticket implements Consession{

	public JuniorMyTi(String id) {super(id);}
	
	static HashMap<String,JuniorMyTi> juniorMyTis = new HashMap<String,JuniorMyTi>();
	
	public static void addTicket(String id){
		// add a new ticket, and put it into a HashMap whose key is id, and value is JuniorMyTi
		JuniorMyTi newJuniorMyTi = new JuniorMyTi(id);
		juniorMyTis.put(id,newJuniorMyTi);
	}
	
	public static double getTicketCredit(String id) {
		//print the credit of a specific JuniorMyTi user
		return juniorMyTis.get(id).getCredit();
	}
	
	public static void topUp(String id, double amt) {
		// Top up the ticket of a specific JuniorMyTi user
			double credit= juniorMyTis.get(id).getCredit();
			double newCredit = credit + amt;
			juniorMyTis.get(id).setCredit(newCredit);
	}
	
	public static void buy(String id,double cost) throws InsufficientCredit {
		double credit= juniorMyTis.get(id).getCredit();
		if(cost <= credit) {
			double newCredit = credit - cost;
			juniorMyTis.get(id).setCredit(newCredit);
			
			}
		else throw new InsufficientCredit(null);
	}
	


	public double getDiscountRate() {
		return discountRate;
		}

}
