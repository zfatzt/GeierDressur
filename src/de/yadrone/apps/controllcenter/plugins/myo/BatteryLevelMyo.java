package de.yadrone.apps.controllcenter.plugins.myo;

import com.thalmic.myo.Myo;

public class BatteryLevelMyo {
	
	private int batteryLevel = 100;
	private Myo myo;
	
	
	
	public BatteryLevelMyo() {
		myo.requestBatteryLevel();
		
	}
	

}
