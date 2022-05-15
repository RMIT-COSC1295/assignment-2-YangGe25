import java.util.HashMap;

public class TravelPass {
	
	private int startTime,endTime;
	private String day,startStation,endStation;
	
	TravelPass(int startTime,int endTime,String day,String startStation,String endStation){
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		this.startStation = startStation;
		this.endStation = endStation;
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
	public String getStartStation() {return this.startStation;}
	public String getDay() {return this.day;}
	public String getEndStatio() {return this.endStation;}
	
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
	
	public static void purchase(String id, String type, String startStation, String endStation, int startTime, int endTime, String day) {
		String period = computePeriod(startTime,endTime);
		String zones = computeZones(startStation,endStation);
		if (!purchases.containsKey(id)) {}
		
	}
	
	public static String computePeriod(int startTime, int endTime){
		String period;
		if ( endTime - startTime <= 200) {period = "a";}
		else period = "b";
		return period;
	}
	
	public static String computeZones(String startStation, String endStation){
		String zones;
		int startZone = Station.getStationZone(startStation);
		int endZone = Station.getStationZone(endStation);
		if (startZone == 1 && endZone == 1) {zones ="a";}
		else if (startZone == 2 && endZone == 2) {zones ="b";}
		else zones ="c";
		return zones;
	}
	
	
	
	

	public static void main(String[] args) {

		System.out.println(TravelPass.computePeriod(900, 1101));
	}
	
	
	
	

}
