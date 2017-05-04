package de.yadrone.apps.controlcenter;

import java.net.URL;
import java.util.ResourceBundle;

import de.yadrone.apps.controlcenter.plugins.battery.BatteryInDecimal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;

public class controlcenterController implements Initializable{
	@FXML
	private ImageView myoImage;
	@FXML
	private Label armActive;
	@FXML
	private ProgressBar progressbarMyo;
	@FXML
	private Label gesturePerformed;
	@FXML
	private Label statusMyo;
	@FXML
	private ImageView droneImage;
	@FXML
	private ProgressBar progressbarDrone;
	@FXML
	private Label heightDrone;
	@FXML
	private Label speedDrone;
	@FXML
	private Label statusVideoLabel;
	@FXML
	private Label statusNavdataLabel;
	@FXML
	private Label statusConnectionLabel;
	@FXML
	private MediaView videoFrame;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BatteryInDecimal bid = new BatteryInDecimal();
		double batteryLVL = bid.getBatterkyLevel();
		progressbarDrone.setProgress(batteryLVL);
		
	}
}
