package de.yadrone.apps.controlcenter.plugins.battery;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

import de.yadrone.apps.controlcenter.ICCPlugin;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.BatteryListener;
import javafx.scene.control.ProgressBar;

public class BatteryInDecimal implements ICCPlugin {
	private IARDrone drone;
	private int batteryLevel = 100;
	private int voltageLevel;

	private ProgressBar progressBar;

	public double batteryLVL() {
		return batteryLevel;
	}

	private BatteryListener batteryListener = new BatteryListener() {

		public void batteryLevelChanged(int batteryLevel) {
			if (batteryLevel != BatteryInDecimal.this.getBatteryLevel()) {
				BatteryInDecimal.this.setBatteryLevel(batteryLevel);
			}
			System.out.println("gg ");
		}

		public void voltageChanged(int vbat_raw) {

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

	public void setProgressBar(ProgressBar batteryProgressbarDrone) {
		this.progressBar = batteryProgressbarDrone;
	}

	@Override
	public void activate(IARDrone drone) {
		this.drone = drone;
		drone.getNavDataManager().addBatteryListener(batteryListener);
	}

}
