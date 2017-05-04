package de.yadrone.apps.controlcenter.plugins.battery;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.BatteryListener;
import javafx.scene.control.ProgressBar;

public class BatteryInDecimal {
	private IARDrone drone;
	private int batteryLevel = 100;
	private int voltageLevel;


	private BatteryListener batteryListener = new BatteryListener() {

		public void voltageChanged(int vbat_raw) {

		}

		public void batteryLevelChanged(int batteryLevel) {
			if (batteryLevel != BatteryInDecimal.this.getBatteryLevel()) {
				BatteryInDecimal.this.setBatteryLevel(batteryLevel);
			}
		}
	};

	
	
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
}