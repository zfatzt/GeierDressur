package ch.bbcag.controlcenter.plugins.battery;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.BatteryListener;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class BatteryInDecimal {
	private IARDrone drone;
	private int batteryLevel = 20;

	public BatteryInDecimal(IARDrone drone) {
		this.setDrone(drone);
		drone.getNavDataManager().addBatteryListener(batteryListener);
	}

	private BatteryListener batteryListener = new BatteryListener() {

		public void batteryLevelChanged(int batteryLevel) {
			if (batteryLevel != BatteryInDecimal.this.getBatteryLevel()) {
				BatteryInDecimal.this.setBatteryLevel(batteryLevel);
			}
			final double toSetInBar = (double) batteryLevel / 100d;
			Platform.runLater(() -> progressbarDrone.setProgress(toSetInBar));
		}

		@Override
		public void voltageChanged(int vbat_raw) {
		}

	};
	private ProgressBar progressbarDrone;

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public void setProgressbarDrone(ProgressBar progressbarDrone) {
		this.progressbarDrone = progressbarDrone;
	}

	public IARDrone getDrone() {
		return drone;
	}

	public void setDrone(IARDrone drone) {
		this.drone = drone;
	}

}
