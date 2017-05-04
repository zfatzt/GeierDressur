package de.yadrone.apps.controlcenter;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.scene.control.ProgressBar;

import javafx.scene.image.ImageView;

import javafx.scene.media.MediaView;

import javafx.scene.layout.Pane;

public class controlcenterController {
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

}
