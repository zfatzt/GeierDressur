package de.yadrone.apps.controlcenter.plugins.myo;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.FirmwareVersion;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.enums.Arm;
import com.thalmic.myo.enums.PoseType;
import com.thalmic.myo.enums.VibrationType;
import com.thalmic.myo.enums.WarmupResult;
import com.thalmic.myo.enums.WarmupState;
import com.thalmic.myo.enums.XDirection;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class GestureListener implements DeviceListener{
	private Pose currentPose;
	private Label poseLabel;
	
	public GestureListener(Label poseLabel2){
		setPoseLabel(poseLabel2);
	}
	
	
	@Override
	public void onPose(Myo myo, long timestamp, Pose pose) {
		currentPose = pose;
		String poseTypeString = currentPose.getType().toString();
		Platform.runLater(() -> this.poseLabel.setText(poseTypeString));
		System.out.println(poseTypeString);
		if (currentPose.getType() == PoseType.FIST) {
			myo.vibrate(VibrationType.VIBRATION_MEDIUM);
		}
	}

	public void setPoseLabel(Label poseLabel){
		this.poseLabel = poseLabel;
	}


	@Override
	public void onPair(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onUnpair(Myo myo, long timestamp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onConnect(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDisconnect(Myo myo, long timestamp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection, float rotation,
			WarmupState warmupState) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onArmUnsync(Myo myo, long timestamp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onUnlock(Myo myo, long timestamp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onLock(Myo myo, long timestamp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onRssi(Myo myo, long timestamp, int rssi) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onBatteryLevelReceived(Myo myo, long timestamp, int level) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onEmgData(Myo myo, long timestamp, byte[] emg) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onWarmupCompleted(Myo myo, long timestamp, WarmupResult warmupResult) {
		// TODO Auto-generated method stub
		
	}
	


}