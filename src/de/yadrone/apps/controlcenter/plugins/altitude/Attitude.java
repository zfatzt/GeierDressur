package de.yadrone.apps.controlcenter.plugins.altitude;

import de.yadrone.apps.controlcenter.ICCPlugin;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Attitude implements ICCPlugin {
	private IARDrone drone;
	

	private AltitudeListener altitudeListener = new AltitudeListener() {

		public void receivedAltitude(int altitude) {
			Platform.runLater(() -> attitudeLabel.setText(String.valueOf(altitude)));
		}

		public void receivedExtendedAltitude(Altitude altitude) {
		}
		

	};
	
	private Label attitudeLabel;

	public void activate(IARDrone drone) {
		this.drone = drone;

		drone.getNavDataManager().addAltitudeListener(altitudeListener);
	}

	public void deactivate() {
		drone.getNavDataManager().removeAltitudeListener(altitudeListener);
	}

	public void setAttitudeLabel(Label attitudelabel) {
		this.attitudeLabel = attitudelabel;

	}
}
