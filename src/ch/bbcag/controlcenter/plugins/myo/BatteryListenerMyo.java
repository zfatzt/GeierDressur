package ch.bbcag.controlcenter.plugins.myo;

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

public class BatteryListenerMyo extends Thread implements DeviceListener {

	private ProgressBar progressbarMyo;
	private Pose currentPose;
	private Label poseLabel;
	private Label armLabel;
	private Label connectionLabel;
	private Label pairLabel;
	private Label warmupLabel;
	private Image greenIcon = new Image(
			this.getClass().getResourceAsStream("/ch/bbcag/controlcenter/img/dot_green.png"));
	private Image redIcon = new Image(this.getClass().getResourceAsStream("/ch/bbcag/controlcenter/img/dot_red.png"));
	private IARDrone drone;

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
		pairLabel.setGraphic(new ImageView(greenIcon));
		pairLabel.setText("Pair State");
		connectionLabel.setGraphic(new ImageView(redIcon));
		connectionLabel.setText("Connection State");
		warmupLabel.setGraphic(new ImageView(greenIcon));
		warmupLabel.setText("Warmup State");

		setProgressbarMyo(progressbarMyo2);
		setPoseLabel(poseLabel);
		setArmLabel(armLabel);
		setConnectionLabel(connectionLabel);
		setPairLabel(pairLabel);
		setWarmupLabel(warmupLabel);
		this.drone = drone;

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
        Platform.runLater(() -> {
            connectionLabel.setGraphic(new ImageView(greenIcon));
            connectionLabel.setText("Connection State");
        });
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
			String armString = String.valueOf(arm == Arm.ARM_LEFT ? "L" : "R");
			Platform.runLater(() -> this.armLabel.setText(armString));
			System.out.println(armString);
		} else {
			String armString = "?";
			this.armLabel.setText(armString);
			System.out.println(armString);
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
		}
		switch (pose.getType()) {
		case WAVE_IN:
			System.out.println("Pose wavein");
			drone.spinLeft();
			break;
		case WAVE_OUT:
			System.out.println("Pose wave out");
			drone.spinRight();
			break;
		case FIST:
			System.out.println("Pose fist");
			drone.landing();
			break;
		case DOUBLE_TAP:
			System.out.println("Pose double tap");
			drone.takeOff();
			break;
		case REST:
			drone.hover();
			break;
		case FINGERS_SPREAD:
			drone.getCommandManager().animate(FlightAnimation.WAVE);
			break;
		default:
			System.out.println("Hello world my friend is the darkness... (I aint your friend)");
			break;
		}

	}

	@Override
	public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {

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

}
