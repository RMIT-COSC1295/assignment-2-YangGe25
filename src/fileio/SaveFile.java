package fileio;

import java.io.PrintWriter;

import myti.User;

public class SaveFile {
	
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

    public static void saveRecord(String pathName) {
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
    		System.out.println("Fail to save Record.txt!");
    		}
    	}    
    
	public static void main(String[] args) {
		ReadFile.readUser("src/User.txt");
		saveUser("src/User.txt");

	}

}
