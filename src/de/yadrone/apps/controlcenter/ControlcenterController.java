package de.yadrone.apps.controlcenter;

import de.yadrone.base.IARDrone;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ControlcenterController {
	
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
	private Label statusVideoLabel;
	@FXML
	private Label statusNavdataLabel;
	@FXML
	private Label statusConnectionLabel;
	@FXML
	private Pane videoPane;
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
	public Label getStatusVideoLabel() {
		return statusVideoLabel;
	}
	public void setStatusVideoLabel(Label statusVideoLabel) {
		this.statusVideoLabel = statusVideoLabel;
	}
	public Label getStatusNavdataLabel() {
		return statusNavdataLabel;
	}
	public void setStatusNavdataLabel(Label statusNavdataLabel) {
		this.statusNavdataLabel = statusNavdataLabel;
	}
	public Label getStatusConnectionLabel() {
		return statusConnectionLabel;
	}
	public void setStatusConnectionLabel(Label statusConnectionLabel) {
		this.statusConnectionLabel = statusConnectionLabel;
	}
	public Pane getVideoPane() {
		return videoPane;
	}
	public void setVideoPane(Pane videoPane) {
		this.videoPane = videoPane;
	}

	public IARDrone getArdrone() {
		return ardrone;
	}

	public void setArdrone(IARDrone ardrone) {
		this.ardrone = ardrone;
	}
	

}
