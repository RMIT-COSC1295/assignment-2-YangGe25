package Model;

import java.util.HashMap;

public class Station {
	
	private String stationName;
	private int zone;
	private int startCount;
	private int endCount;
	
	static HashMap<String, Station> stations = new HashMap<String, Station>();
	
	Station(String stationName,int zone){
		this.stationName = stationName;
		this.zone = zone;
		this.startCount = 0;
		this.endCount = 0;
	}
	
	public String getStationName() {return this.stationName;}
	public int getZone() {return this.zone;}
	public int getStartCount() {return this.startCount;}
	public int getEndCount() {return this.endCount;}
	
	public void setStartCount(int startCount) {this.startCount=startCount;}
	public void setEndCount(int endCount) {this.endCount=endCount;}
	
	// add a new station, and put it into a HashMap whose key is stationName, and value is station itself
	public static void addStation(String stationName,int zone) {
		Station newstation = new Station(stationName,zone);
		stations.put(stationName, newstation);
	}
	
	// Count the end time of a station
	public static void addStartCount(String stationName) {
		int newStartCount = stations.get(stationName).getStartCount()+1;
		stations.get(stationName).setStartCount(newStartCount);
	}
	// Count the end time of a station
	public static void addEndCount(String stationName) {
		int newEndCount = stations.get(stationName).getEndCount()+1;
		stations.get(stationName).setEndCount(newEndCount);
	}
	
	// Print the information of all stations
	public static void showStationInfo() {
		for (String i : stations.keySet()) {
            System.out.printf("Station: %9s | Start: %d | End: %d\n",i,stations.get(i).getStartCount(),stations.get(i).getEndCount());
        }
	}
	
	public static int getStationZone(String stationName) {
		return stations.get(stationName).getZone();
	}


}
