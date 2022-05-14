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
	
	
	public static void addTicket(String id){
		// add a new ticket, and put it into a HashMap whose key is id, and value credit
	}
	
	public static void showTicketInfo(String id) {
		//print the information of specific user
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
