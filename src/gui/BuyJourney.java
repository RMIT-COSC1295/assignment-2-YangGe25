package gui;

import javafx.geometry.Insets;



import javafx.scene.control.TextField;

import java.util.ArrayList;

import fileio.ReadFile;
import fileio.SaveFile;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import myti.TravelPass;
import myti.User;
import myti.UserExists;

public class BuyJourney extends Application{
	
	@Override
	public void start(Stage primaryStage) {	
		
	    GridPane gp = new GridPane(); 
	    gp.setAlignment(Pos.TOP_LEFT);
	    gp.setPadding(new Insets(20,20,20,20));
		gp.setHgap(5);
		gp.setVgap(5);
	    
		// Label for User
		Label label1 = new Label("User");
		gp.add(label1, 0, 0);
		// ListView for User
		ArrayList<String> userList = new ArrayList<String>();
		for (String id:User.users.keySet()) {
			userList.add(id);
		}
		ObservableList<String> users = FXCollections.observableArrayList(userList);
		ListView<String> lv1 = new ListView<String>(users);
	    lv1.setMaxSize(80, 120);
	   
	    gp.add(lv1, 0, 1);
	    
	    // Label for startStation
	    Label label2 = new Label("From");
	    gp.add(label2, 1, 0);
		// ListView for startStation
		ObservableList<String> stations = FXCollections.observableArrayList("Central", "Flagstaff", "Richmond", "Lilydale", "Epping");
		ListView<String> lv2 = new ListView<String>(stations);
	    lv2.setMaxSize(80, 120);
	    gp.add(lv2, 1, 1);
		
	    // Label for endStation
	    Label label3 = new Label("To");
	    gp.add(label3, 2, 0);
		// ListView for endStation
		ListView<String> lv3 = new ListView<String>(stations);
	    lv3.setMaxSize(80, 120);
	    gp.add(lv3, 2, 1);
		
	    // Label for Day
	    Label label4 = new Label("Day");
	    gp.add(label4, 3, 0);
		// ListView for Day
		ObservableList<String> days = FXCollections.observableArrayList("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun");
		ListView<String> lv4 = new ListView<String>(days);
	    lv4.setMaxSize(80, 120);
	    gp.add(lv4, 3, 1);
		
	    // Label for startTime
		gp.add(new Label("     Start"), 0, 2);
		// TextField for inputting startTime
		TextField tf1 = new TextField();
		tf1.setPrefWidth(80);
		gp.add(tf1, 1, 2);
		
		// Label for endtTime
		gp.add(new Label("     End"), 2, 2);
		// TextField for inputting endTime
		TextField tf2 = new TextField();
		tf2.setPrefWidth(80);
		gp.add(tf2, 3, 2);
		
		// TextField to display message
		TextField  tf3 = new TextField ("Welcome to MyTi system!");
		gp.add(tf3, 0, 4, 4, 4);		
		
		// Button for purchase
		Button btPurchase = new Button("Purchase");
		btPurchase.setOnAction(e ->{
			
			// In case the admin doesn't select any parameter
			String id = lv1.getSelectionModel().getSelectedItem();
			if(id == null) {
				tf3.setText("Please select a user");return;
			}
			String startStation = lv2.getSelectionModel().getSelectedItem();
			if(startStation == null) {
				tf3.setText("Please select a start station");return;
			}
			String endStation = lv3.getSelectionModel().getSelectedItem();
			if(endStation == null) {
				tf3.setText("Please select an end stationr");return;
			}
			String day = lv4.getSelectionModel().getSelectedItem();
			if(day == null) {
				tf3.setText("Please select a day");return;
			}
			int departureTime = 0;
			int arrivalTime = 0;
			try{
				departureTime = Integer.parseInt(tf1.getText());
				arrivalTime = Integer.parseInt(tf2.getText());
			} catch (Exception e1) {tf3.setText("Please enter a valid time");return;}
			
			if(startStation.equals(endStation)) {
				tf3.setText("StartStation and EndStation can't be the same");
			}
			else if (arrivalTime <= 0 || arrivalTime>2359) {
				tf3.setText("Please enter a valid time");
			}
			else if (departureTime >= arrivalTime) {
				tf3.setText("Departure time must be prior to arrival time");
			}
			else {
				String message = TravelPass.purchase(id, startStation, endStation, departureTime, arrivalTime, day);
				if (message != null) {
					tf3.setText(message);	
				} else tf3.setText("Insufficient Balance");
				
			}
		});
		gp.add(btPurchase, 0, 3);
		
		Button btSave = new Button("Save");
		btSave.setOnAction(e-> { 
			SaveFile.saveUser("src/User.txt");
			SaveFile.saveRecord("src/Record.txt");
			});
		gp.add(btSave, 2, 8);
		
		Button btQuit = new Button("Quit");
		gp.add(btQuit, 3, 8);
		btQuit.setOnAction(e-> { 
			System.exit(0);
			});
	    
		Scene scene = new Scene(gp, 400, 300);
		primaryStage.setTitle("BuyJourney");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws UserExists {
		ReadFile.readPrice("src/Price.txt");
		ReadFile.readStation("src/Station.txt");
		ReadFile.readUser("src/User.txt");

		
		Application.launch(args);

	}

}
