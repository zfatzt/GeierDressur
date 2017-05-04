package de.yadrone.apps.controlcenter.plugins.battery;

import de.yadrone.apps.controlcenter.controlcenterController;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.BatteryListener;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class BatteryInDecimal {
	private IARDrone drone;
	private int batteryLevel = 100;
	private int voltageLevel;

	private ProgressBar progressBar;
	
	public double batteryLVL(){
		return batteryLevel;
	}
	
	
	private BatteryListener batteryListener = new BatteryListener() {
		
		public void batteryLevelChanged(int batteryLevel) {
			if (batteryLevel != BatteryInDecimal.this.getBatteryLevel()) {
				BatteryInDecimal.this.setBatteryLevel(batteryLevel);
				Platform.runLater(() -> {
					progressBar.setProgress(batteryLevel);
				});
			System.err.println("Benji sagt BAUM");	
		}
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
}