package MyTicket;

import java.util.HashMap;
import java.util.Set;

import Exceptions.InsufficientCredit;

public class SeniorMyTi extends Ticket implements Consession{

	public SeniorMyTi(String id) {super(id);}
	
	static HashMap<String,SeniorMyTi> seniorMyTis = new HashMap<String,SeniorMyTi>();
	
	
	public static void addTicket(String id){
		// add a new ticket, and put it into a HashMap whose key is id, and value is SeniorMyTi
		SeniorMyTi newSeniorMyTi = new SeniorMyTi(id);
		seniorMyTis.put(id,newSeniorMyTi);
	}
	
	public static double getTicketCredit(String id){
		//print the credit of a specific SeniorMyTi user
		return seniorMyTis.get(id).getCredit();
	}
	
	public static void topUp(String id, double amt) {
		// Top up the ticket of a specific JuniorMyTi user
			double credit= seniorMyTis.get(id).getCredit();
			double newCredit = credit + amt;
			seniorMyTis.get(id).setCredit(newCredit);
	}
	
	public static void buy(String id,double cost) throws InsufficientCredit {
		double credit= seniorMyTis.get(id).getCredit();
		if(cost <= credit) {
			double newCredit = credit - cost;
			seniorMyTis.get(id).setCredit(newCredit);
			
			}
		else throw new InsufficientCredit(null);
	}
	


	public double getDiscountRate() {
		// TODO Auto-generated method stub
		return discountRate;
	}

	@Override
	public void setDiscountRate(double d) {
		// TODO Auto-generated method stub
		
	}
	

}
