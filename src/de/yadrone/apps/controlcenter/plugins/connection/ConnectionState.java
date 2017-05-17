package de.yadrone.apps.controlcenter.plugins.connection;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.CommandException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.exception.NavDataException;
import de.yadrone.base.exception.VideoException;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConnectionState{
	private static final long serialVersionUID = 1L;

	private IARDrone drone;

	private static Image greenIcon;
	private static Image redIcon;

	private IExceptionListener exceptionListener;

	public ConnectionState() {

		greenIcon = new Image(this.getClass().getResourceAsStream("/ img/dot_green.png"));
		redIcon = new Image(this.getClass().getResourceAsStream("img/dot_red.png"));

		Platform.runLater(() -> videoStateLabel.setGraphic(new ImageView(greenIcon)));
		Platform.runLater(() -> videoStateLabel.setText("Video State"));

		Platform.runLater(() -> navadataStateLabel.setGraphic(new ImageView(greenIcon)));
		Platform.runLater(() -> navadataStateLabel.setText("Navdata State"));

		Platform.runLater(() -> commandStateLabel.setGraphic(new ImageView(greenIcon)));
		Platform.runLater(() -> commandStateLabel.setText("Command State"));

		exceptionListener = new IExceptionListener() {

			public void exeptionOccurred(ARDroneException exc) {
				if (exc instanceof CommandException) {
					Platform.runLater(() -> commandStateLabel.setGraphic(new ImageView(redIcon)));
					Platform.runLater(() -> commandStateLabel.setText("Command State"));

				} else if (exc instanceof NavDataException) {
					Platform.runLater(() -> navadataStateLabel.setGraphic(new ImageView(redIcon)));
					Platform.runLater(() -> navadataStateLabel.setText("Navdata State"));

				} else if (exc instanceof VideoException) {
					Platform.runLater(() -> videoStateLabel.setGraphic(new ImageView(greenIcon)));
					Platform.runLater(() -> videoStateLabel.setText("Video State"));
				}
			}
		};

	}

	private Label commandStateLabel;
	private Label navadataStateLabel;
	private Label videoStateLabel;

	public void activate(IARDrone drone) {
		this.drone = drone;

		drone.addExceptionListener(exceptionListener);
	}

	public void deactivate() {
		drone.removeExceptionListener(exceptionListener);
	}

	public void setCommandStateLabel(Label commandStateLabel) {
		this.commandStateLabel = commandStateLabel;
	}

	public void setNavadataStateLabel(Label navadataStateLabel) {
		this.navadataStateLabel = navadataStateLabel;
	}

	public void setVideoStateLabel(Label videoStateLabel) {
		this.videoStateLabel = videoStateLabel;
	}

}
