// TODO FIXME XXX RENAME THE FUCKING CLASSES
package de.yadrone.apps.controlcenter.plugins.battery;

import de.yadrone.apps.controlcenter.ICCPlugin;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.BatteryListener;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class BatteryInDecimal implements ICCPlugin {
	private IARDrone drone;
	private int batteryLevel = 20;
	private int voltageLevel;

	private BatteryListener batteryListener = new BatteryListener() {

		public void batteryLevelChanged(int batteryLevel) {
			if (batteryLevel != BatteryInDecimal.this.getBatteryLevel()) {
				BatteryInDecimal.this.setBatteryLevel(batteryLevel);
			}
			// TODO: rename vars
			final double toSetInBar = (double)batteryLevel/100d;
			System.out.println(batteryLevel + " / " + toSetInBar);
			Platform.runLater(() -> progressbarDrone.setProgress(toSetInBar));
		}

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

	public int getVoltageLevel() {
		return voltageLevel;
	}

	public void setVoltageLevel(int voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	@Override
	public void activate(IARDrone drone) {
		this.drone = drone;
		drone.getNavDataManager().addBatteryListener(batteryListener);
	}

	public void setProgressbarDrone(ProgressBar progressbarDrone) {
		this.progressbarDrone = progressbarDrone;
	}

}
