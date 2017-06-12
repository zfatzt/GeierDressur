package ch.bbcag.controlcenter.plugins.myo;

import ch.bbcag.controlcenter.plugins.speed.SpeedLabel;
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

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.FlightAnimation;
import javafx.application.Platform;
import javafx.beans.binding.When;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;

import static com.sun.webkit.graphics.GraphicsDecoder.SCALE;

public class MyoListener /*extends Thread */ implements DeviceListener {

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
    private double rollW;
    private double pitchW;
    private double yawW;
    private double oldRollW;
    private double oldPitchW;
    private double oldYawW;
    private int speed = 10;

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

    public MyoListener(ProgressBar progressbarMyo2, Label poseLabel, Label armLabel, Label connectionLabel,
                       Label pairLabel, Label warmupLabel, IARDrone drone) {
        this.drone = drone;
        Platform.runLater(() -> pairLabel.setGraphic(new ImageView(greenIcon)));
        pairLabel.setText("Pair State");
        Platform.runLater(() -> connectionLabel.setGraphic(new ImageView(redIcon)));
        connectionLabel.setText("Connection State");
        Platform.runLater(() -> warmupLabel.setGraphic(new ImageView(greenIcon)));
        warmupLabel.setText("Warmup State");

        setProgressbarMyo(progressbarMyo2);
        setPoseLabel(poseLabel);
        setArmLabel(armLabel);
        setConnectionLabel(connectionLabel);
        setPairLabel(pairLabel);
        setWarmupLabel(warmupLabel);

    }

    public void setConnectionLabel(Label connectionLabel) {
        this.connectionLabel = connectionLabel;
    }

    public void setPairLabel(Label pairLabel) {
        this.pairLabel = pairLabel;
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
        Platform.runLater(() -> {
            connectionLabel.setGraphic(new ImageView(redIcon));
            connectionLabel.setText("Connection State");
        });
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
        System.out.println("onArmUnsync");
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

        switch (pose.getType()) {
            case WAVE_IN:
                System.out.println("Pose wavein");
                drone.down();
                break;
            case WAVE_OUT:
                System.out.println("Pose wave out");
                drone.up();
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
//                drone.getCommandManager().animate(FlightAnimation.WAVE);
                break;
            default:
                System.out.println("No Pose");
                break;
        }
    }

    int i = 0;

    @Override
    public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
        System.out.println("onOrientationData" + i++);
        Quaternion normalized = rotation.normalized();

        double roll = Math.atan2(2.0f * (normalized.getW() * normalized.getX() + normalized.getY() * normalized.getZ()), 1.0f - 2.0f * (normalized.getX() * normalized.getX() + normalized.getY() * normalized.getY()));
        double pitch = Math.asin(2.0f * (normalized.getW() * normalized.getY() - normalized.getZ() * normalized.getX()));
        double yaw = Math.atan2(2.0f * (normalized.getW() * normalized.getZ() + normalized.getX() * normalized.getY()), 1.0f - 2.0f * (normalized.getY() * normalized.getY() + normalized.getZ() * normalized.getZ()));

        double roundedPitch = RoundTo2Decimals(pitch);

        if (roll < -0.5) {
            System.out.println("Go Right (Roll: " + RoundTo2Decimals(roll) + ")");
            drone.goRight();
        } else if (roll > 0.5) {
            System.out.println("Go Left (Roll: " + RoundTo2Decimals(roll) + ")");
            drone.goLeft();
        }else if (pitch > 0.6) {
            System.out.println("Go forward (Pitch: " + roundedPitch + ")");
            drone.forward();
        } else if (pitch < -0.6) {
            System.out.println("Go back (Pitch: " + RoundTo2Decimals(pitch) + ")");
            drone.backward();
        } else {
            drone.hover();
        }
    }

    private double RoundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
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
