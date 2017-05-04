package de.yadrone.apps.controlcenter;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class openfx extends Application {
	  
	public static void main(String[] args) {
	        Application.launch(openfx.class, args);
	    }
	    
	    @Override
	    public void start(Stage stage) throws Exception {
	        AnchorPane root = FXMLLoader.load(getClass().getResource("controlcenter.fxml"));
	        stage.setTitle("Control Center");
	        stage.setScene(new Scene(root));
	        stage.show();
	    }
	}

 