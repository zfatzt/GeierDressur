package de.yadrone.apps.controlcenter;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

import de.yadrone.apps.controlcenter.plugins.battery.BatteryInDecimal;
import de.yadrone.apps.controlcenter.plugins.keyboard.KeyboardCommandManager;
import de.yadrone.apps.controlcenter.plugins.myo.BatteryListenerMyo;
import de.yadrone.apps.controlcenter.plugins.video.Video;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class openfx extends Application {

	private IARDrone ardrone = null;
	private String currentKey;

	public static void main(String[] args) {
		Application.launch(openfx.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ardrone = new ARDrone();
		System.out.println("Connect drone controller");

		Hub hub = new Hub("com.example.hello-myo");
		System.out.println("Attempting to find a Myo.....");
		Myo myo = hub.waitForMyo(100000);

		if (myo == null || ardrone == null) {
			throw new RuntimeException("Unable to find Myo or no Connection to Drone");
		}

		ardrone.start();
		KeyboardCommandManager cmdManager = new KeyboardCommandManager(ardrone);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("controlcenter.fxml"));
		Scene scene = new Scene(loader.load());
		scene.setOnKeyPressed((event) -> {
			cmdManager.keyPressed(event);
			currentKey = "KEY " + event.getCharacter();
		});
		scene.setOnKeyReleased((event) -> {
			cmdManager.keyReleased(event);
			currentKey = "";
		});
		ControlcenterController c = loader.getController();
		c.setArdrone(ardrone);

		hub.addListener(new BatteryListenerMyo(c.getProgressbarMyo()));
		Runnable runnable = () -> {
			while (true) {
				hub.run(10);
				myo.requestBatteryLevel();
			}
		};
		new Thread(runnable).start();

		stage.setTitle("Control Center");
		stage.setScene(scene);
		stage.show();

		BatteryInDecimal bid = new BatteryInDecimal();
		bid.activate(ardrone);
		bid.setProgressbarDrone(c.getProgressbarDrone());

		Video video = new Video(ardrone, c);
		video.start();
	}

}
