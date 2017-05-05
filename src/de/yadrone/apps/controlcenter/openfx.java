package de.yadrone.apps.controlcenter;

import de.yadrone.apps.controlcenter.plugins.battery.BatteryInDecimal;
import de.yadrone.apps.controlcenter.plugins.keyboard.KeyboardCommandManager;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class openfx extends Application {

	private IARDrone ardrone = null;

	public static void main(String[] args) {
		Application.launch(openfx.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ardrone = new ARDrone();
		System.out.println("Connect drone controller");
		ardrone.start();
		KeyboardCommandManager cmdManager = new KeyboardCommandManager(ardrone);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("controlcenter.fxml"));
		Scene scene = new Scene(loader.load());
		
		scene.setOnKeyPressed((event) -> {
			cmdManager.keyPressed(event);
		});
		scene.setOnKeyReleased((event) -> {
			cmdManager.keyReleased(event);
		});
		ControlcenterController c = loader.getController();
		c.setArdrone(ardrone);

		stage.setTitle("Control Center");
		stage.setScene(scene);
		stage.show();
		
		BatteryInDecimal bid = new BatteryInDecimal();
		bid.activate(ardrone);
		bid.setProgressbarDrone(c.getProgressbarDrone());

	}

}
