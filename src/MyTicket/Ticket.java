package MyTicket;

public abstract class Ticket {
	
	private String id;
	private double credit;
	
	
	Ticket(String id){
		this.id = id;
		this.credit = 0.0;
	}
	
	public String getTicketID() {return this.id;}
	public double getCredit() {return this.credit;}
	public void setCredit(double credit) {this.credit=credit;}
	
	
	public static void addTicket(String id){
		// add a new ticket, and put it into a HashMap whose key is id, and value is Ticket
	}
	
	public static double getTicketCredit(String id) {
		return 0.0;
		//print the credit of a specific user
	}
	
	public static void topUp(String id) {
		//print the credit of a specific user
	}


}
