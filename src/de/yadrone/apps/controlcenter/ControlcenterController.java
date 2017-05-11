package de.yadrone.apps.controlcenter;

import java.net.URL;
import java.util.ResourceBundle;

import de.yadrone.base.IARDrone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ControlcenterController implements Initializable {

	private IARDrone ardrone = null;

	@FXML
	private BorderPane borderPane;
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
	private Label statusVideoLabelDrone;
	@FXML
	private Label statusNavdataLabelDrone;
	@FXML
	private Label statusConnectionLabelDrone;
	@FXML
	private ImageView video;
	@FXML
	private Label statusPairLabelMyo;
	@FXML
	private Label statusWarmupLabelMyo;
	@FXML
	private Label statusConnectionLabelMyo;

	
	public BorderPane getBorderPane() {
		return borderPane;
	}

	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}

	public ImageView getMyoImage() {
		return myoImage;
	}

	public void setMyoImage(ImageView myoImage) {
		this.myoImage = myoImage;
	}

	public Label getArmActive() {
		return armActive;
	}

	public Label getStatusVideoLabelDrone() {
		return statusVideoLabelDrone;
	}

	public void setStatusVideoLabelDrone(Label statusVideoLabelDrone) {
		this.statusVideoLabelDrone = statusVideoLabelDrone;
	}

	public void setArmActive(Label armActive) {
		this.armActive = armActive;
	}

	public ProgressBar getProgressbarMyo() {
		return progressbarMyo;
	}

	public void setProgressbarMyo(ProgressBar progressbarMyo) {
		this.progressbarMyo = progressbarMyo;
	}

	public Label getGesturePerformed() {
		return gesturePerformed;
	}

	public void setGesturePerformed(Label gesturePerformed) {
		this.gesturePerformed = gesturePerformed;
	}

	public Label getStatusMyo() {
		return statusMyo;
	}

	public void setStatusMyo(Label statusMyo) {
		this.statusMyo = statusMyo;
	}

	public ImageView getDroneImage() {
		return droneImage;
	}

	public void setDroneImage(ImageView droneImage) {
		this.droneImage = droneImage;
	}

	public ProgressBar getProgressbarDrone() {
		return progressbarDrone;
	}

	public void setProgressbarDrone(ProgressBar progressbarDrone) {
		this.progressbarDrone = progressbarDrone;
	}

	public Label getHeightDrone() {
		return heightDrone;
	}

	public void setHeightDrone(Label heightDrone) {
		this.heightDrone = heightDrone;
	}

	public Label getSpeedDrone() {
		return speedDrone;
	}

	public void setSpeedDrone(Label speedDrone) {
		this.speedDrone = speedDrone;
	}

	public Label getStatusNavdataLabelDrone() {
		return statusNavdataLabelDrone;
	}

	public void setStatusNavdataLabelDrone(Label statusNavdataLabelDrone) {
		this.statusNavdataLabelDrone = statusNavdataLabelDrone;
	}

	public Label getStatusConnectionLabelDrone() {
		return statusConnectionLabelDrone;
	}

	public void setStatusConnectionLabelDrone(Label statusConnectionLabelDrone) {
		this.statusConnectionLabelDrone = statusConnectionLabelDrone;
	}

	public Label getStatusPairLabelMyo() {
		return statusPairLabelMyo;
	}

	public void setStatusPairLabelMyo(Label statusPairLabelMyo) {
		this.statusPairLabelMyo = statusPairLabelMyo;
	}

	public Label getStatusWarmupLabelMyo() {
		return statusWarmupLabelMyo;
	}

	public void setStatusWarmupLabelMyo(Label statusWarmupLabelMyo) {
		this.statusWarmupLabelMyo = statusWarmupLabelMyo;
	}

	public Label getStatusConnectionLabelMyo() {
		return statusConnectionLabelMyo;
	}

	public void setStatusConnectionLabelMyo(Label statusConnectionLabelMyo) {
		this.statusConnectionLabelMyo = statusConnectionLabelMyo;
	}

	public IARDrone getArdrone() {
		return ardrone;
	}

	public void setArdrone(IARDrone ardrone) {
		this.ardrone = ardrone;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void draw(Image image) {
		video.setImage(image);
	}

}
