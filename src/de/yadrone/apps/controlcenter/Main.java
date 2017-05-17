package de.yadrone.apps.controlcenter;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.LockingPolicy;

import de.yadrone.apps.controlcenter.plugins.altitude.Attitude;
import de.yadrone.apps.controlcenter.plugins.battery.BatteryInDecimal;
import de.yadrone.apps.controlcenter.plugins.connection.ConnectionState;
import de.yadrone.apps.controlcenter.plugins.keyboard.KeyboardCommandManager;
import de.yadrone.apps.controlcenter.plugins.myo.BatteryListenerMyo;
import de.yadrone.apps.controlcenter.plugins.speed.SpeedLabel;
import de.yadrone.apps.controlcenter.plugins.video.Video;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {

	private IARDrone ardrone = null;
	private String currentKey;

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ardrone = new ARDrone();
		System.out.println("Connect drone controller");

		Hub hub = new Hub("com.example.hello-myo");
		System.out.println("Attempting to find a Myo.....");

		Myo myo = hub.waitForMyo(100);

		if (myo == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Connection");
			alert.setHeaderText("No connection to Myo.");
			alert.setContentText("Connect Myo and than restart Programm!");
			alert.showAndWait();
		}

		ardrone.start();
		hub.setLockingPolicy(LockingPolicy.LOCKING_POLICY_NONE);
		KeyboardCommandManager cmdManager = new KeyboardCommandManager(ardrone);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));

		// Key Listener
		Scene scene = new Scene(loader.load());
		scene.setOnKeyPressed((event) -> {
			cmdManager.keyPressed(event);
			setCurrentKey("KEY " + event.getCharacter());
		});
		scene.setOnKeyReleased((event) -> {
			cmdManager.keyReleased(event);
			setCurrentKey("");
		});

		// Controller
		Controlcenter c = loader.getController();
		c.setArdrone(ardrone);

		hub.addListener(new BatteryListenerMyo(c.getProgressbarMyo(), c.getGesturePerformed(), c.getArmActive(),
				c.getStatusConnectionLabelMyo(), c.getStatusPairLabelMyo(), c.getStatusWarmupLabelMyo(), ardrone));

		Runnable runnable = () -> {
			while (true) {
				hub.run(10);
				myo.requestBatteryLevel();
			}
		};
		new Thread(runnable).start();

		// Battery
		BatteryInDecimal bid = new BatteryInDecimal();
		bid.activate(ardrone);
		bid.setProgressbarDrone(c.getProgressbarDrone());

		// Attitude
		Attitude attitude = new Attitude();
		attitude.activate(ardrone);
		attitude.setAttitudeLabel(c.getHeightDrone());

		// Speed
		SpeedLabel speed = new SpeedLabel();
		speed.activate(ardrone);
		speed.setSpeedLabel(c.getSpeedDrone());

		// Connection State
		ConnectionState cs = new ConnectionState();
		cs.activate(ardrone);
		cs.setNavadataStateLabel(c.getStatusNavdataLabelDrone());
		cs.setVideoStateLabel(c.getStatusVideoLabelDrone());
		cs.setCommandStateLabel(c.getStatusConnectionLabelDrone());

		// Main
		stage.setTitle("Control Center");
		stage.setScene(scene);
		stage.show();

		Video video = new Video(ardrone, c);
		video.start();
	}

	public String getCurrentKey() {
		return currentKey;
	}

	public void setCurrentKey(String currentKey) {
		this.currentKey = currentKey;
	}

}
