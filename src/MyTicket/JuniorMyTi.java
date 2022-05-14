package MyTicket;

import java.util.HashMap;
import java.util.Set;

public class JuniorMyTi extends Ticket implements Consession{

	public JuniorMyTi(String id) {super(id);}
	
	static HashMap<String,JuniorMyTi> juniorMyTis = new HashMap<String,JuniorMyTi>();
	
	public static void addTicket(String id){
		// add a new ticket, and put it into a HashMap whose key is id, and value credit
		JuniorMyTi newJuniorMyTi = new JuniorMyTi(id);
		juniorMyTis.put(id,newJuniorMyTi);
	}
	
	public static void showTicketInfo(String id) {
		//print the information of specific user
		if (juniorMyTis.containsKey(id)) {System.out.println("id: " + id + ", credit: " + juniorMyTis.get(id).getCredit());}
		else {System.out.println("User doesn't exist!");}
	}
	
	public static void main(String[] args) {

	}

	@Override
	public double getDiscountRate() {return discountRate;}

	@Override
	public void setDiscountRate(double d) {
		// TODO Auto-generated method stub
		
	}



}
