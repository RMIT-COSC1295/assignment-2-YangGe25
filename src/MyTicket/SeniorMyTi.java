package MyTicket;

import java.util.HashMap;
import java.util.Set;

public class SeniorMyTi extends Ticket implements Consession{

	public SeniorMyTi(String id) {super(id);}
	
	static HashMap<String,SeniorMyTi> seniorMyTis = new HashMap<String,SeniorMyTi>();
	
	
	public static void addTicket(String id){
		// add a new ticket, and put it into a HashMap whose key is id, and value credit
		SeniorMyTi newSeniorMyTi = new SeniorMyTi(id);
		seniorMyTis.put(id,newSeniorMyTi);
	}
	
	public static void showTicketInfo(String id) {
		//print the information of specific user
		if (seniorMyTis.containsKey(id)) {System.out.println("id: " + id + ", credit: " + seniorMyTis.get(id).getCredit());}
		else {System.out.println("User doesn't exist!");}
	}
	
	public static void main(String[] args) {
	}


	@Override
	public double getDiscountRate() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setDiscountRate(double d) {
		// TODO Auto-generated method stub
		
	}

}
