package de.yadrone.apps.controlcenter.plugins.myo;

import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.FirmwareVersion;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.enums.Arm;
import com.thalmic.myo.enums.WarmupResult;
import com.thalmic.myo.enums.WarmupState;
import com.thalmic.myo.enums.XDirection;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class BatteryListenerMyo implements DeviceListener {

	private ProgressBar progressbarMyo;
	
	public BatteryListenerMyo(ProgressBar progressbarMyo2) {
		setProgressbarMyo(progressbarMyo2);
	}

	@Override
	public void onPair(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {

	}

	@Override
	public void onUnpair(Myo myo, long timestamp) {

	}

	@Override
	public void onConnect(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {

	}

	@Override
	public void onDisconnect(Myo myo, long timestamp) {

	}

	@Override
	public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection, float rotation,
			WarmupState warmupState) {

	}

	@Override
	public void onArmUnsync(Myo myo, long timestamp) {

	}

	@Override
	public void onUnlock(Myo myo, long timestamp) {
		System.out.println("Unock");
	}

	@Override
	public void onLock(Myo myo, long timestamp) {
		System.out.println("Lock");
	}

	@Override
	public void onPose(Myo myo, long timestamp, Pose pose) {

	}

	@Override
	public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {

	}

	@Override
	public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {
		// System.out.println("accelerometer");
	}

	@Override
	public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {
		// System.out.println("gyroscope");

	}

	@Override
	public void onRssi(Myo myo, long timestamp, int rssi) {

	}

	@Override
	public void onBatteryLevelReceived(Myo myo, long timestamp, int level) {
		float toSetInProgressbar = level / 100f;
		Platform.runLater(() -> progressbarMyo.setProgress(toSetInProgressbar));
		System.out.println(toSetInProgressbar);
	}

	@Override
	public void onEmgData(Myo myo, long timestamp, byte[] emg) {
		System.out.println("emgdata");
	}

	@Override
	public void onWarmupCompleted(Myo myo, long timestamp, WarmupResult warmupResult) {
		System.out.println("Warmed up");
	}
	
	public void setProgressbarMyo(ProgressBar progressBarMyo){
		this.progressbarMyo = progressBarMyo;
	}

}
