package gui;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import myti.UserExists;

public class DisplayReports extends Application {
	
	@Override
	public void start(Stage primaryStage) {	
		
		 GridPane gp = new GridPane(); 
		 gp.setAlignment(Pos.CENTER);
		 gp.setPadding(new Insets(10,10,10,10));
		 gp.setHgap(5);
		 gp.setVgap(5);
		 
		 Button btDisplay = new Button("Display Journeys");
		 gp.add(btDisplay, 0, 0);
		 
		 TextArea ta = new TextArea();
		 gp.add(ta, 0, 1);
		 
		 Button btSave = new Button("Save");
		 gp.add(btSave, 3, 5);
		 
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
		
		Application.launch(args);

	}

}
