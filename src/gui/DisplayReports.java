package gui;

import fileio.ReadFile;
import fileio.SaveFile;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import myti.User;
import myti.UserExists;

public class DisplayReports extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		// Create a gridpane
		GridPane gp = new GridPane(); 
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(10,10,10,10));
		gp.setHgap(5);
		gp.setVgap(5);
		 
		// textArea to display reports
		TextArea ta = new TextArea();
		gp.add(ta, 0, 1);
		 
		// button to generate reports
		Button btDisplay = new Button("Display Journeys");
		btDisplay.setOnAction(e-> {
			String message = "";
			// generate journeys by user
			for(String id:User.journeys.keySet()) {
				message += "Journeys taken by " + id +":\n";
					
				String[] journey= User.journeys.get(id).split("\n");
				 
				for (String jy:journey) {
					String[] j = jy.split(":");
					String startStation = j[0];
					String endStation = j[1];
					String departureTime = j[2];
					String arrivalTime = j[3];
					String day = j[4];
					message += String.format("%10s -> %10s | %4s ->%4s | %3s\n",startStation,endStation,departureTime,arrivalTime,day);
				 }

				ta.setText(message);
			 }
			});
		 gp.add(btDisplay, 0, 0);
			 
		 // button to save journeys
		 Button btSave = new Button("Save");
		 btSave.setOnAction(e-> {
			SaveFile.saveJourney("src/Journey.txt");
			});
		 gp.add(btSave, 3, 5);
		 
		 // button to quit
		 Button btQuit = new Button("Quit");
		 gp.add(btQuit, 4, 5);
		 btQuit.setOnAction(e-> {
			 System.exit(0);
			 });
		 
		 Scene scene = new Scene(gp);
		 primaryStage.setTitle("DisplayReports");
		 primaryStage.setScene(scene);
		 primaryStage.show();			
	}

	public static void main(String[] args) throws UserExists {
		
		ReadFile.readJourney("src/Journey.txt");
		
		Application.launch(args);

	}

}
