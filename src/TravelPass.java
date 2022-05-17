import java.util.HashMap;

import MyTicket.FullMyTi;
import MyTicket.JuniorMyTi;
import MyTicket.SeniorMyTi;

public class TravelPass {
	
	private int startTime,endTime;
	private String day,period,zones;
	
	TravelPass(int startTime,int endTime,String day,String period,String zones){
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		this.period = period;
		this.zones = zones;
	}
	
	private static double TWO_HOUR_ZONE1 = 2.50;
	private static double ALL_DAY_ZONE1 = 4.90;
	
	private static double TWO_HOUR_ZONE2 = 2.50;
	private static double ALL_DAY_ZONE2 = 4.90;
	
	private static double TWO_HOUR_ZONES12 = 3.50;
	private static double ALL_DAY_ZONES12 = 6.80;
	
	static HashMap<String,TravelPass> purchases = new HashMap<String,TravelPass>();
	
	public int getStartTime() {return this.startTime;}
	public int getEndTime() {return this.endTime;}
	public String getPeriod() {return this.period;}
	public String getZones() {return this.zones;}
	public String getDay() {return this.day;}
	/*
	 *  Set travel pass price
	 */
	public static void setPrice(String period, String zones, double price) {
		if(period.equals("a") && zones.equals("a")) {TWO_HOUR_ZONE1 = price;
		System.out.printf("The price of Two Hours pass for Zone 1 has been updated to %.2f\n",price);}
		else if(period.equals("b") && zones.equals("a")) {ALL_DAY_ZONE1 = price;
		System.out.printf("The price of All Day pass for Zone 1 has been updated to %.2f\n",price);}
		else if(period.equals("a") && zones.equals("b")) {TWO_HOUR_ZONE2= price;
		System.out.printf("The price of Two Hours pass for Zone 2 has been updated to %.2f\n",price);}
		else if(period.equals("b") && zones.equals("b")) {ALL_DAY_ZONE2 = price;
		System.out.printf("The price of All Day pass for Zone 2 has been updated to %.2f\n",price);}
		else if(period.equals("a") && zones.equals("c")) {TWO_HOUR_ZONES12 = price;
		System.out.printf("The price of Two Hours pass for Zones 1 and 2 has been updated to %.2f\n",price);}
		else if(period.equals("b") && zones.equals("c")) {ALL_DAY_ZONES12 = price;
		System.out.printf("The price of All Day pass for Zones 1 and 2 has been updated to %.2f\n",price);}
		else System.out.println("Update failed!");
	}
	/*
	 * purchase is the main method in this class, it receive order from the menu and decides what to do
	 */
	
	public static void purchase(String id,String type,String startStation,String endStation,int departureTime,int arrivalTime,String day) {
		String period = computePeriod(departureTime,arrivalTime);//a a
		String zones = computeZones(startStation,endStation);//a a
		double price = computePrice(period,zones);
		double rate = computeDiscount(type,day);
		int startTime = 0;int endTime = 0;double cost = price * (1-rate);
		boolean valid = false;boolean purchaseFail = true;
		// if the user hasn't purchased a pass or he purchased a pass on another day, then he needs to buy anew pass
		if ((purchases.containsKey(id) == false) || (!purchases.get(id).getDay().equals(day))) {
			startTime = departureTime;
			if (period.equals("a")) {endTime = startTime + 200;}
			else endTime = 2359;
			TravelPass travelPass = new TravelPass(startTime,endTime,day,period,zones);
			purchases.put(id, travelPass);
			purchaseFail = buyNewPass(id, type, period, zones, cost);
			if(!purchaseFail) {record(id, startStation, endStation, departureTime, arrivalTime, day);}}
		/*
		 *  if the user has purchased a travel pass on this day
		 *   check whether it's valid 
		 *   if not valid, upgrade the pass
		 */
		else {
			valid = checkValidity(id,period,zones,arrivalTime);
			if (valid) {
				System.out.println("Current Travel Pass still valid!");
				record(id, startStation, endStation, departureTime, arrivalTime, day);
				}
			else {
				double oldPrice = computePrice(purchases.get(id).getPeriod(),purchases.get(id).getZones());
				cost = (price - oldPrice)*(1-rate);
				String oldZones = purchases.get(id).getZones();
				String newPeriod =null;
				purchaseFail = upgradePass(id, type, period, zones, rate, arrivalTime);
				int oldEndTime = purchases.get(id).getEndTime();
				if ((oldZones.equals("a")||oldZones.equals("b")) && (zones.equals("c")) && (arrivalTime <= oldEndTime)) {endTime = oldEndTime;newPeriod = "a";}
				else {endTime = 2359;newPeriod = "b";}
				startTime =purchases.get(id).getStartTime();
				if(!purchaseFail) {
					TravelPass travelPass = new TravelPass(startTime,endTime,day,period,zones);
					purchases.put(id, travelPass);
					record(id, startStation, endStation, departureTime, arrivalTime, day);}
			}
		}
	}
	
		
	// get the travel period according to departureTime and arrivalTime
	public static String computePeriod(int departureTime, int arrivalTime){
		String period;
		if ( arrivalTime - departureTime <= 200) {period = "a";}
		else period = "b";
		return period;
	}
	// get the travel zones according to startStation and endStation
	public static String computeZones(String startStation, String endStation){
		String zones;
		int startZone = Station.getStationZone(startStation);
		int endZone = Station.getStationZone(endStation);
		if (startZone == 1 && endZone == 1) {zones ="a";}
		else if (startZone == 2 && endZone == 2) {zones ="b";}
		else zones ="c";
		return zones;
	}
	// compute the original price regardless of any discount
	public static double computePrice(String period, String zones) {
		double price = 0.0;
		if(period.equals("a") && zones.equals("a")) {price = TWO_HOUR_ZONE1;}
		else if(period.equals("b") && zones.equals("a")) {price = ALL_DAY_ZONE1;}
		else if(period.equals("a") && zones.equals("b")) {price = TWO_HOUR_ZONE2;}
		else if(period.equals("b") && zones.equals("b")) {price = ALL_DAY_ZONE2;}
		else if(period.equals("a") && zones.equals("c")) {price = TWO_HOUR_ZONES12;}
		else if(period.equals("b") && zones.equals("c")) {price = ALL_DAY_ZONES12;}
		return price;
	}
	// get the discount of a specific user
	public static double computeDiscount(String type, String day) {
		double rate = 0;
		if (type.equals("Adult")) {rate = 0;}
		else if (type.equals("Junoir")) {JuniorMyTi juniorMyTi = new JuniorMyTi(null);rate = juniorMyTi.getDiscountRate();}
		else if (type.equals("Senior") && day.equals("Sun")) {rate = 1.0;}
		else {SeniorMyTi seniorMyTi = new SeniorMyTi(null);rate = seniorMyTi.getDiscountRate();}
		return rate;
	}
	
	/*
	 *  buyNewPass means if a user hasn't purchased any travel pass on one day, he needs to buy a new travel pass
	 *  For example, if he only purchased a travel pass on Monday, he still needs to purchase a new travel pass on Tuesday
	 */
	public static boolean buyNewPass(String id, String type, String period, String zones, double cost) {
		String pPeriod = printPeriod(period);
		String pZones= printZones(zones);
		boolean purchaseFail = true;
		String message = null;
		if (type.equals("Adult")) {
			message = String.format("%s %s Travel Pass purchased for %s for $%.2f\n",pPeriod,pZones,id,cost);
			purchaseFail = buyPass(id,type,cost,message);
		}
		else if (type.equals("Junoir")) {
			message = String.format("%s %s (Junior) Travel Pass purchased for %s for $%.2f\n",pPeriod,pZones,id,cost);
			purchaseFail = buyPass(id,type,cost,message);
		}
		else if (type.equals("Senior")) {
			message = String.format("%s %s (Senior) Travel Pass purchased for %s for $%.2f\n",pPeriod,pZones,id,cost);
			purchaseFail = buyPass(id,type,cost,message);
		}
		return purchaseFail;
	}
	
	/*
	 *  checkValid will work only if the user has purchased a travel pass on the same day
	 *  It will check if the new journey is during the valid period
	 */
	public static boolean checkValidity(String id,String period,String zones,int arrivalTime) {
		boolean valid = false;
		int endTime = purchases.get(id).getEndTime();
		if (arrivalTime <= endTime) {
			if (purchases.get(id).getZones().equals(zones)) { valid =true;}
			else if (purchases.get(id).getZones().equals("c") && zones.equals("a")) { valid =true;}
			else if (purchases.get(id).getZones().equals("c") && zones.equals("b")) { valid =true;}
		}
		return valid;		
		}		
	
	
	/*
	 *  upgrade the travel pass, which include 9 cases 
	 *  (Period,Zone) 
	 *  (2 Hour,1) -> (All Day,1)/(2 Hour,1&2)/(All Day,1&2) || (2 Hour,2) -> (All Day,2)/(2 Hour,1&2)/(All Day,1&2)
	 *  (All Day,1) -> (All Day,1&2) || (All Day,2) -> (All Day,1&2) || (2 Hour,1&2) -> (All Day,1&2)
	 */
	public static boolean upgradePass(String id, String type, String period, String zones, double rate,int arrivalTime) {
		String oldPeriod = purchases.get(id).getPeriod();String oldZones = purchases.get(id).getZones();
		double cost =0;boolean purchaseFail = true;String message = null;int endTime = purchases.get(id).getEndTime();
		if (oldPeriod.equals("a") && oldZones.equals("a")) {
			if (zones.equals("a")) {
				cost = (ALL_DAY_ZONE1 - TWO_HOUR_ZONE1) * rate;
				message = String.format("2 Hour Zone 1 Pass upgraded to All Day Zone 1 Pass for %s for $%.2f\n",id,cost);
				purchaseFail = buyPass(id,type,cost,message);}
			else if (zones.equals("b") || zones.equals("c")) {
				if (arrivalTime <= endTime) {
					cost = (TWO_HOUR_ZONES12 - TWO_HOUR_ZONE1) * rate;
					message = String.format("2 Hour Zone 1 Pass upgraded to 2 Hour Zones 1&2 Pass for %s for $%.2f\n",id,cost);
					purchaseFail = buyPass(id,type,cost,message);}
				else {
					cost = (ALL_DAY_ZONES12 - TWO_HOUR_ZONE1) * rate;
					message = String.format("2 Hour Zone 1 Pass upgraded to All Day Zones 1&2 Pass for %s for $%.2f\n",id,cost);
					purchaseFail = buyPass(id,type,cost,message);}}}
		else if (oldPeriod.equals("a") && oldZones.equals("b")) {
			if (zones.equals("b")) {
				cost = (ALL_DAY_ZONE2 - TWO_HOUR_ZONE2) * rate;
				message = String.format("2 Hour Zone 2 Pass upgraded to All Day Zone 2 Pass for %s for $%.2f\n",id,cost);
				purchaseFail = buyPass(id,type,cost,message);}
			else if (zones.equals("a") || zones.equals("c")) {
				if (arrivalTime <= endTime) {
					cost = (TWO_HOUR_ZONES12 - TWO_HOUR_ZONE2) * rate;
					message = String.format("2 Hour Zone 2 Pass upgraded to 2 Hour Zones 1&2 Pass for %s for $%.2f\n",id,cost);
					purchaseFail = buyPass(id,type,cost,message);}
				else {
					cost = (ALL_DAY_ZONES12 - TWO_HOUR_ZONE2) * rate;
					message = String.format("2 Hour Zone 2 Pass upgraded to All Day Zones 1&2 Pass for %s for $%.2f\n",id,cost);
					purchaseFail = buyPass(id,type,cost,message);}}}
		else if (oldPeriod.equals("b")) {
			if (zones.equals("a")) {
				cost = (ALL_DAY_ZONES12 - ALL_DAY_ZONE1) * rate;
				message = String.format("ALL DAY Zone 1 Pass upgraded to All Day Zones 1&2 Pass for %s for $%.2f\n",id,cost);
				purchaseFail = buyPass(id,type,cost,message);}
			else if (zones.equals("b")) {
				cost = (ALL_DAY_ZONES12 - ALL_DAY_ZONE2) * rate;
				message = String.format("ALL DAY Zone 2 Pass upgraded to All Day Zones 1&2 Pass for %s for $%.2f\n",id,cost);
				purchaseFail = buyPass(id,type,cost,message);}}
		else if (oldZones.equals("c")) {
				cost = (ALL_DAY_ZONES12 - TWO_HOUR_ZONES12) * rate;
				message = String.format("2 Hour Zones 1&2 Pass upgraded to All Day Zones 1&2 Pass for %s for $%.2f\n",id,cost);
				purchaseFail = buyPass(id,type,cost,message);}
		return purchaseFail;}
	
	/*
	 *  Buy a new travel pass on one day
	 */
	public static boolean buyPass(String id, String type,double cost,String message) {
		boolean purchaseFail = true;
		if (type.equals("Adult")) {
			try{FullMyTi.buy(id,cost);System.out.printf(message);showRemainingCredit(id,type);purchaseFail = false;
			} catch (Exception e) {System.out.printf("%s doesn't have enough credit!\n",id);}
		}
		else if (type.equals("Junoir")) {
			try{JuniorMyTi.buy(id,cost);System.out.printf(message);showRemainingCredit(id,type);purchaseFail = false;
			} catch (Exception e) {System.out.printf("%s doesn't have enough credit!\n",id);}
		}
		else if (type.equals("Senior")) {
			try{SeniorMyTi.buy(id,cost);System.out.printf(message);showRemainingCredit(id,type);purchaseFail = false;
			} catch (Exception e) {System.out.printf("%s doesn't have enough credit!\n",id);}
		}
		return purchaseFail;
	}
	
	public static String printPeriod(String period) {
		String pPeriod = null;
		if (period.equals("a")) {pPeriod = "2 Hour";}
		else if (period.equals("b")) {pPeriod = "All Day";}
		return pPeriod;
	}
	
	public static String printZones(String zones) {
		String pZones = null;
		if (zones.equals("a")) {pZones = "Zone 1";}
		else if (zones.equals("b")) {pZones = "Zone 2";}
		else if (zones.equals("c")) {pZones = "Zones 1 and 2";}
		return pZones;
	}
	
	/*
	 *  add record to the user report and station statistics if purchased successfully 
	 */
	public static void record(String id,String startStation,String endStation,int departureTime,int arrivalTime,String day) {
		User.addJourney(id, startStation, endStation, departureTime, arrivalTime, day);
		Station.addStartCount(startStation);Station.addEndCount(endStation);
	}
	
	/*
	 * Display the remaining credit of specific user
	 */
	public static void showRemainingCredit(String id,String type) {
		
		double credit = 0.0;
		if (type == "Adult") {credit = FullMyTi.getTicketCredit(id);}
		else if (type == "Junior") {credit = JuniorMyTi.getTicketCredit(id);}
		else if (type == "Senior") {credit = SeniorMyTi.getTicketCredit(id);}
		System.out.printf("%s's remaining credit is $%.2f\n ", id, credit);
		
	}
	
	
	
	

}
