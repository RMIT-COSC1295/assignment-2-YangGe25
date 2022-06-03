package gui;

import java.util.ArrayList;

import fileio.ReadFile;
import fileio.SaveFile;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import myti.User;
import myti.UserExists;

public class ManageUsers extends Application{
	
	final static double CREDIT_LIMIT = 100.0;  // maximum allowed credit
	final static int LEGAL_MULTIPLE = 5; // multiple that can re-charge by
	
	@Override
	public void start(Stage primaryStage) {
		
		// Create a gridpane
	    GridPane gp = new GridPane(); 
	    gp.setAlignment(Pos.TOP_LEFT);
	    gp.setPadding(new Insets(20,20,20,20));
		gp.setHgap(5);
		gp.setVgap(5);		
		
	    // Label for User
	 	Label label1 = new Label("<User>");
	 	gp.add(label1, 0, 1);
	 	
	    // Label for User
	 	Label label2 = new Label("<Remaining Credit>");
	 	gp.add(label2, 0, 2);
		
		// Menu of Users
		Menu userMenu = new Menu("<Please Select>");
		ArrayList<String> userList = new ArrayList<String>();
		for (String id:User.users.keySet()) {
			userList.add(id);
		}
		
		for (String user : userList) {
			MenuItem mi = new MenuItem(user);
			mi.setOnAction(e -> {
				label1.setText(user);
				label2.setText("Remaining Credit: $" + User.getUserCredit(user));
			});
			userMenu.getItems().add(mi);
		}
		MenuBar users = new MenuBar(userMenu);
	    gp.add(users, 0, 0);
	    

		// TextField for inputting recharge amount
		TextField tf1 = new TextField("<Amount>");
		tf1.setPrefWidth(50);
		gp.add(tf1, 0, 3);			    
		
	    // TextField to display message
	    TextField tf2 = new TextField("<message>");
	    tf2.setPrefWidth(200);
		gp.add(tf2, 0, 4, 1 ,4);
		

		
		// Button to recharge
		Button btRecharge = new Button("Add Credit");
		btRecharge.setOnAction(e ->{

			String id = label1.getText();
			// In case the admin dooesn't select a user
			if(id.equals("<User>")) {
				tf2.setText("Please select a user first");
				return;
			}
			
			double amt = 0;
			double credit = User.getUserCredit(id);
			// Get a valid amount
			try{
				amt = Double.parseDouble(tf1.getText());		
			} catch(Exception e1) {tf2.setText("Invalid amount");return;}
			
			if(amt < 0) {
				tf2.setText("Invalid amount");return;
			}else if (credit + amt > CREDIT_LIMIT) {
				tf2.setText("Credit limit is " + CREDIT_LIMIT);
			} else if (amt % LEGAL_MULTIPLE != 0) {
				tf2.setText("Amount must be multiples of " + LEGAL_MULTIPLE);
			} else {
				User.userRecharge(id,amt);
				String message = String.format("$%.2f added to %s ", amt, id);
				tf2.setText(message);
			}		
		});
		gp.add(btRecharge, 1, 3);
		
		
		// Label for ID
		Label label3 = new Label("id:");
		gp.add(label3, 3, 0);
		// TextField for inputting ID
		TextField tf3 = new TextField();
		tf3.setPrefWidth(40);
		gp.add(tf3, 4, 0);
		
		// Label for name
		Label label4 = new Label("name:");
		gp.add(label4, 3, 1);
		// TextField for inputting name
		TextField tf4 = new TextField();
		tf4.setPrefWidth(40);
		gp.add(tf4, 4, 1);
		
		// Label for email
		Label label5 = new Label("email:");
		gp.add(label5, 3, 2);
		// TextField for inputting email
		TextField tf5 = new TextField();
		tf5.setPrefWidth(40);
		gp.add(tf5, 4, 2);
		
		// Label for type
		Label label6 = new Label("type:");
		gp.add(label6, 3, 3);
		// ListView for type
		ObservableList<String> types = FXCollections.observableArrayList("Adult", "Junior", "Senior");
		ListView<String> lv = new ListView<String>(types);
		lv.setMaxSize(80, 50);
		gp.add(lv, 4, 3);
		

		// button to create a user
		Button btCreateUser = new Button("Create User");
		btCreateUser.setOnAction(e ->{
			// get valid user information
			String id = tf3.getText();
			if(id.length() == 0) {
				tf2.setText("Please enter the ID");return;
			}
			
			String name = tf4.getText();
			if(name.length() == 0) {
				tf2.setText("Please enter the name");return;
			}
			
			String email = tf5.getText();
			if(email.length() == 0) {
				tf2.setText("Please enter the email");return;
			}
			
			String type = lv.getSelectionModel().getSelectedItem();
			if(type == null) {
				tf2.setText("Please enter select a type");return;
			}
			try {
				User.addUser(id, name, type, email);
				tf2.setText(id + " successfully added");
			} catch (UserExists e1) { 
				tf2.setText("User ID already exists");
			}
			
		});
		gp.add(btCreateUser, 4, 5);
		
		// button to save user info
		Button btSave = new Button("Save");
		btSave.setOnAction(e-> { 
			SaveFile.saveUser("src/User.txt");
			});
		gp.add(btSave, 3, 6);
		
		// button to quit
		Button btQuit = new Button("Quit");
		gp.add(btQuit, 4, 6);
		btQuit.setOnAction(e-> { 
			System.exit(0);
			});
		
		
		Scene scene = new Scene(gp);
		primaryStage.setTitle("ManageUsers");
		primaryStage.setScene(scene);
		primaryStage.show();		
		
	}


	public static void main(String[] args) throws UserExists  {
		
		ReadFile.readUser(args[0]);
		
		Application.launch(args);

	}

}
