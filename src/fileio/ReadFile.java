package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import myti.Station;
import myti.TravelPass;
import myti.User;

public class ReadFile {
	
    /*
     *  Read the User.txt file and load the information
     */
    public static void readUser(String pathName) {
    	try {
    		File fileName = new File(pathName);
    		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
    		BufferedReader br = new BufferedReader(reader);
    		String line = "";
    		line = br.readLine();
    		while(line != null && line.length() != 0) {
    			// seperate the line by ":"
    			String[] user = line.split(":");
    			// add the user to the hashmap
    			User.addUser(user[0], user[2], user[1], user[3]);
    			// set the initial credit for user
    			double amt = Double.parseDouble(user[4]);	
    			User.userRecharge(user[0], amt);
    				
    			line = br.readLine();
    			}
    		br.close();
    		} catch (Exception e) {
    			System.out.println("Fail to read User.txt");
    			}
    	}
    
    /*
     *  Read the Price.txt file and initialize the price
     */
    public static void readPrice(String pathName) {
    	try {
    		File fileName = new File(pathName);
    		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
    		BufferedReader br = new BufferedReader(reader);
    		String line = "";
    		line = br.readLine();
    		while(line != null) {
    			// seperate the line by ":"
				String[] price = line.split(":");
				// set the travel pass rice
				double p = Double.parseDouble(price[2]);
				TravelPass.setPrice(price[0], price[1], p);
					
    			line = br.readLine();
    			}
    		br.close();
    		} catch (Exception e) {
    			System.out.println("Fail to read Price.txt");
    	}   	
    }
    
    /*
     *  Read the Station.txt file and initialize the price
     */
    public static void readStation(String pathName) {
    	try {
    		File fileName = new File(pathName);
    		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
    		BufferedReader br = new BufferedReader(reader);
    		String line = "";
    		line = br.readLine();
    		while(line != null) {
    			// seperate the line by ":"
				String[] station = line.split(":");
				// initialize the stations
				int zone = Integer.parseInt(station[1]);
				Station.addStation(station[0], zone);
					
    			line = br.readLine();
    			}
    		br.close();
    		} catch (Exception e) {
    			System.out.println("Fail to read Station.txt");
    	}   	
    }

    /*
     *  Read the Record.txt file and initialize the price
     */
    public static void readRecord(String pathName) {
    	try {
    		File fileName = new File(pathName);
    		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
    		BufferedReader br = new BufferedReader(reader);
    		String line = "";
    		line = br.readLine();
    		while(line != null && line.length() != 0) {
    			
    			if (line.startsWith("#")) {
    				
    			}
    			
    			line = br.readLine();
    			}
    		br.close();
    		} catch (Exception e) {
    			System.out.println("Fail to read Record.txt");
    	}   	
    }
	public static void main(String[] args) {
		readUser("src/User.txt");
	}

}
