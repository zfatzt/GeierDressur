package de.yadrone.apps.controlcenter.plugins.myo;

import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.StreamEmgType;

public class HelloMyo {
	static Hub hub = new Hub("com.example.HelloMyo");
	static Myo myo = hub.waitForMyo(10000);

	public static boolean connectToMyo() {

		System.out.println("Attempting to find a Myo...");

		if (myo == null) {
			throw new RuntimeException("Unable to find a Myo!");
		}
		return true;
	}

	public void showEMGData(){
		DeviceListener dataCollector = new DataCollector();
		hub.addListener(dataCollector);
		
		myo.setStreamEmg(StreamEmgType.STREAM_EMG_ENABLED);
		DeviceListener EmgDataCollecter = new EmgDataCollector();
		hub.addListener(EmgDataCollecter);

		
		while (true) {
			hub.run(1000 / 20);
			System.out.println(EmgDataCollecter);
			System.out.print(dataCollector);
		}	
	}
}
