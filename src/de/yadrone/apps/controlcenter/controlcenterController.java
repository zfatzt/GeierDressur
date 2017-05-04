package de.yadrone.apps.controlcenter;

import java.net.URL;
import java.util.ResourceBundle;

import de.yadrone.apps.controlcenter.plugins.battery.BatteryInDecimal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;

public class controlcenterController implements Initializable {
	@FXML
	private TextField title;
	@FXML
	private ImageView droneImage;
	@FXML
	private ProgressBar batteryProgressbarDrone;
	@FXML
	private TextField batterlyLevelDrone;
	@FXML
	private TextField statusDroneText;
	@FXML
	private ImageView myoImage;
	@FXML
	private MediaView VideoFrame;
	@FXML
	private ProgressBar batteryProgressbarMyo;
	@FXML
	private TextField batteryLevelMyo;
	@FXML
	private TextField gestures;
	@FXML
	private Pane statusText;
	@FXML
	private TextField gesturePerfomed;
	@FXML
	private TextField statusMyoText;
	@FXML
	private Pane statusMyo;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BatteryInDecimal bid = new BatteryInDecimal();
		bid.setProgressBar(batteryProgressbarDrone);
		double batteryLVL = bid.getBatteryLevel();
		batteryProgressbarDrone.setProgress(batteryLVL);

	}
}
