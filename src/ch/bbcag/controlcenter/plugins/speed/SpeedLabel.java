package ch.bbcag.controlcenter.plugins.speed;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class SpeedLabel {
	private IARDrone drone;
	private Label speedLabel;

	public SpeedLabel(IARDrone drone) {
		this.drone = drone;
		Platform.runLater(() -> speedLabel.setText(String.valueOf(drone.getSpeed())));
		drone.addSpeedListener(speedListener);
	}

	private ARDrone.ISpeedListener speedListener = new ARDrone.ISpeedListener() {

		public void speedUpdated(int speed) {
			if (speed != drone.getSpeed()) {
				Platform.runLater(() -> speedLabel.setText(String.valueOf(speed)));
			}
			Platform.runLater(() -> speedLabel.setText(String.valueOf(drone.getSpeed())));
			System.out.println(drone.getSpeed());
		}
	};

	public void setSpeedLabel(Label speedLabel) {
		this.speedLabel = speedLabel;
	}

}
