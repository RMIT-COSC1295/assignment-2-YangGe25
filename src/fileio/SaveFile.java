package fileio;

import java.io.PrintWriter;

import myti.TravelPass;
import myti.User;

public class SaveFile {
	
    /*
     *  Rewrite the User.txt file with the updated user info
     */
    public static void saveUser(String pathName) {
    	try {
    		PrintWriter pw = new PrintWriter(pathName);
    		
    		for(User user : User.users.values()) {
    			String id = user.getID();
    			String type = user.getType();
    			String name = user.getName();
    			String email = user.getEmail();
    			String credit = String.valueOf(User.getUserCredit(id));
    			
    			String content = id+":"+type+":"+name+":"+email+":"+credit+"\n";
    			pw.write(content);
    			pw.flush();
    		}
			pw.close();
			} catch (Exception e) {
    		System.out.println("Fail to save User.txt!");
    		}
    	}

    
    /*
     *  Rewrite the Journey.txt file with the updated journeys
     */
    public static void saveJourney(String pathName) {
    	try {
    		PrintWriter pw = new PrintWriter(pathName);
    		
    		for(String user : User.journeys.keySet()) {
    			String record = User.journeys.get(user);
    			
    			String content = "#" + user +"\n";
    			content += record;
    			pw.write(content);
    			pw.flush();
    		}
			pw.close();
			} catch (Exception e) {
    		System.out.println("Fail to save Journey.txt!");
    		}
    	}    
    
    
    /*
     *  Rewrite the Purchase.txt file with the updated purchases
     */
    public static void savePurchase(String pathName) {
    	try {
    		PrintWriter pw = new PrintWriter(pathName);
    		
    		for(String id : TravelPass.purchases.keySet()) {
    			
    			String startTime = String.valueOf(TravelPass.purchases.get(id).getStartTime());
    			String endTime = String.valueOf(TravelPass.purchases.get(id).getEndTime());
    			String day = TravelPass.purchases.get(id).getDay();
    			String period = TravelPass.purchases.get(id).getPeriod();
    			String zones = TravelPass.purchases.get(id).getZones();
    		
    			String content = id+":"+startTime+":"+endTime+":"+day+":"+period+":"+zones+"\n";
    			pw.write(content);
    			pw.flush();
    		}
			pw.close();
			} catch (Exception e) {
    		System.out.println("Fail to save Purchase.txt!");
    		}
    	}   


}
