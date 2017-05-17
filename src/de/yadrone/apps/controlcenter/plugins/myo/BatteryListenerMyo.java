package de.yadrone.apps.controlcenter.plugins.myo;

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

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.FlightAnimation;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static com.sun.webkit.graphics.GraphicsDecoder.SCALE;

public class BatteryListenerMyo extends Thread implements DeviceListener {

	private ProgressBar progressbarMyo;
	private Pose currentPose;
	private Label poseLabel;
	private Label armLabel;
	private Label connectionLabel;
	private Label pairLabel;
	private Label warmupLabel;
	private Image greenIcon = new Image(this.getClass().getResourceAsStream("dot_green.png"));
	private Image redIcon = new Image(this.getClass().getResourceAsStream("dot_red.png"));
	private IARDrone drone;
	private double rollW;
	private double pitchW;
	private double yawW;
	private double oldRollW;
	private double oldPitchW;
	private double oldYawW;

	public Label getArmLabel() {
		return armLabel;
	}

	public void setArmLabel(Label arm) {
		this.armLabel = arm;
	}

	public Label getPoseLabel() {
		return poseLabel;
	}

	public void setPoseLabel(Label poseLabel) {
		this.poseLabel = poseLabel;
	}

	public ProgressBar getProgressbarMyo() {
		return progressbarMyo;
	}

	public BatteryListenerMyo(ProgressBar progressbarMyo2, Label poseLabel, Label armLabel, Label connectionLabel,
			Label pairLabel, Label warmupLabel, IARDrone drone) {
		this.drone = drone;
		pairLabel.setGraphic(new ImageView(greenIcon));
		pairLabel.setText("Pair State");
		connectionLabel.setGraphic(new ImageView(redIcon));
		connectionLabel.setText("Connection State");
		warmupLabel.setGraphic(new ImageView(greenIcon));
		warmupLabel.setText("Warmup State");

		Runnable runnable = () -> {
			while (true) {
				this.checkIfXDirectionChanged();
				this.checkIfYDirectionChanged();
				this.checkIfZDirectionChanged();
			}
		};
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();

		setProgressbarMyo(progressbarMyo2);
		setPoseLabel(poseLabel);
		setArmLabel(armLabel);
		setConnectionLabel(connectionLabel);
		setPairLabel(pairLabel);
		setWarmupLabel(warmupLabel);

	}

	public Label getConnectionLabel() {
		return connectionLabel;
	}

	public void setConnectionLabel(Label connectionLabel) {
		this.connectionLabel = connectionLabel;
	}

	public Label getPairLabel() {
		return pairLabel;
	}

	public void setPairLabel(Label pairLabel) {
		this.pairLabel = pairLabel;
	}

	public Label getWarmupLabel() {
		return warmupLabel;
	}

	public void setWarmupLabel(Label warmupLabel) {
		this.warmupLabel = warmupLabel;
	}

	@Override
	public void onPair(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
		pairLabel.setGraphic(new ImageView(greenIcon));
		pairLabel.setText("Pair State");
	}

	@Override
	public void onUnpair(Myo myo, long timestamp) {
		pairLabel.setGraphic(new ImageView(redIcon));
		pairLabel.setText("Pair State");
	}

	@Override
	public void onConnect(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
		connectionLabel.setGraphic(new ImageView(greenIcon));
		connectionLabel.setText("Connection State");
	}

	@Override
	public void onDisconnect(Myo myo, long timestamp) {
		connectionLabel.setGraphic(new ImageView(redIcon));
		connectionLabel.setText("Connection State");
	}

	@Override
	public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection, float rotation,
			WarmupState warmupState) {
		if (arm != null) {
			String armString = String.valueOf(arm == Arm.ARM_LEFT ? "Left" : "Right");
			Platform.runLater(() -> this.armLabel.setText(armString));
			
		} else {
			String armString = "?";
			this.armLabel.setText(armString);
			
		}

	}

	@Override
	public void onArmUnsync(Myo myo, long timestamp) {

	}

	@Override
	public void onUnlock(Myo myo, long timestamp) {
		System.out.println("Unlocked");

	}

	@Override
	public void onLock(Myo myo, long timestamp) {
		System.out.println("Locked");

	}

	@Override
	public void onPose(Myo myo, long timestamp, Pose pose) {
		currentPose = pose;
		String poseTypeString = currentPose.getType().toString();
		Platform.runLater(() -> this.poseLabel.setText(poseTypeString));
		System.out.println(poseTypeString);
		if (currentPose.getType() == PoseType.FIST) {
			myo.vibrate(VibrationType.VIBRATION_MEDIUM);
		} else if (pose.getType() == PoseType.WAVE_IN) {
			System.out.println("Pose wave in");
			drone.spinLeft();

		} else if (pose.getType() == PoseType.WAVE_OUT) {
			System.out.println("Pose wave out");
			drone.spinRight();

		} else if (pose.getType() == PoseType.FIST) {
			System.out.println("Pose fist");
			drone.landing();

		} else if (pose.getType() == PoseType.DOUBLE_TAP) {
			System.out.println("Pose double tap");
			drone.takeOff();

		} else if (pose.getType() == PoseType.FINGERS_SPREAD) {
			drone.getCommandManager().animate(FlightAnimation.WAVE);

		} else {
			System.out.println("Didn't get gesture!");

		}

	}

	@Override
	public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
		Quaternion normalized = rotation.normalized();

		double roll = Math.atan2(2.0f * (normalized.getW() * normalized.getX() + normalized.getY() * normalized.getZ()),
				1.0f - 2.0f * (normalized.getX() * normalized.getX() + normalized.getY() * normalized.getY()));
		double pitch = Math
				.asin(2.0f * (normalized.getW() * normalized.getY() - normalized.getZ() * normalized.getX()));
		double yaw = Math.atan2(2.0f * (normalized.getW() * normalized.getZ() + normalized.getX() * normalized.getY()),
				1.0f - 2.0f * (normalized.getY() * normalized.getY() + normalized.getZ() * normalized.getZ()));

		this.rollW = Math.round(((roll + Math.PI) / (Math.PI * 2.0) * SCALE));
		this.pitchW = Math.round(((pitch + Math.PI / 2.0) / Math.PI * SCALE));
		this.yawW = Math.round(((yaw + Math.PI) / (Math.PI * 2.0) * SCALE));

	}

	@Override
	public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {

	}

	@Override
	public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {

	}

	@Override
	public void onRssi(Myo myo, long timestamp, int rssi) {

	}

	@Override
	public void onBatteryLevelReceived(Myo myo, long timestamp, int level) {
		float toSetInProgressbar = level / 100f;
		progressbarMyo.setProgress(toSetInProgressbar);
	}

	@Override
	public void onEmgData(Myo myo, long timestamp, byte[] emg) {
		System.out.println("emgdata");

	}

	@Override
	public void onWarmupCompleted(Myo myo, long timestamp, WarmupResult warmupResult) {
		Platform.runLater(() -> {
			warmupLabel.setGraphic(new ImageView(greenIcon));
			warmupLabel.setText("Warmup State");
		});
	}

	public void setProgressbarMyo(ProgressBar progressBarMyo) {
		this.progressbarMyo = progressBarMyo;
	}

	public void checkIfZDirectionChanged() {

		if (this.rollW > oldRollW) {
			drone.spinLeft();
			
		} else if (this.rollW < oldRollW) {
			drone.spinRight();
			
		}
		this.oldRollW = this.rollW;
		drone.hover();

	}

	public void checkIfYDirectionChanged() {
		if (this.yawW > oldYawW) {
			drone.goLeft();
			
		} else if (this.yawW < oldYawW) {
			drone.goRight();
			
		}
		this.oldYawW = this.yawW;
		drone.hover();

	}

	public void checkIfXDirectionChanged() {
		if (this.pitchW > oldPitchW) {
			drone.forward();
			
		} else if (this.pitchW < oldPitchW) {
			drone.backward();
			
		}
		this.oldPitchW = this.pitchW;
		drone.hover();

	}
}
