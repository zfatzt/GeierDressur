package ch.bbcag.controlcenter.plugins.altitude;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.AltitudeListener;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Altitude {
	private IARDrone drone;

	public Altitude(IARDrone drone) {
		this.setDrone(drone);

		drone.getNavDataManager().addAltitudeListener(altitudeListener);
	}

	private AltitudeListener altitudeListener = new AltitudeListener() {

		public void receivedAltitude(int altitude) {
			Platform.runLater(() -> attitudeLabel.setText(String.valueOf(altitude)));
		}

		@Override
		public void receivedExtendedAltitude(de.yadrone.base.navdata.Altitude d) {

		}

	};

	private Label attitudeLabel;

	public void setAttitudeLabel(Label attitudelabel) {
		this.attitudeLabel = attitudelabel;

	}

	public IARDrone getDrone() {
		return drone;
	}

	public void setDrone(IARDrone drone) {
		this.drone = drone;
	}
}
