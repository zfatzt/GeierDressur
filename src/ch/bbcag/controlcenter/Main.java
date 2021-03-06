package ch.bbcag.controlcenter;

import ch.bbcag.controlcenter.plugins.altitude.Altitude;
import ch.bbcag.controlcenter.plugins.battery.BatteryInDecimal;
import ch.bbcag.controlcenter.plugins.connection.ConnectionState;
import ch.bbcag.controlcenter.plugins.dialogs.ErrorDialogs;
import ch.bbcag.controlcenter.plugins.keyboard.KeyboardCommandManager;
import ch.bbcag.controlcenter.plugins.myo.MyoListener;
import ch.bbcag.controlcenter.plugins.speed.SpeedLabel;
import ch.bbcag.controlcenter.plugins.video.Video;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.LockingPolicy;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private IARDrone ardrone = null;
    private String currentKey;
    private ErrorDialogs errordialogs = new ErrorDialogs();

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) {
        stage.setOnCloseRequest((event -> System.exit(0)));
        ardrone = new ARDrone();
        ardrone.start();

        Hub hub = new Hub("com.example.hello-myo");
        Myo myo = hub.waitForMyo(10);
        if (myo == null) {
            errordialogs.errorDialogMyo();
        }

        hub.setLockingPolicy(LockingPolicy.LOCKING_POLICY_NONE);
        KeyboardCommandManager cmdManager = new KeyboardCommandManager(ardrone);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));

        // Key Listener
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            errordialogs.errorDialogScene();
        }

        scene.setOnKeyPressed((event) -> {
            cmdManager.keyPressed(event);
            setCurrentKey("KEY " + event.getCharacter());
        });
        scene.setOnKeyReleased((event) -> {
            cmdManager.keyReleased(event);
            setCurrentKey("");
        });


        // Controller
        Controlcenter c = loader.getController();
        c.setArdrone(ardrone);

        Runnable runnable2 = () -> {
            while (true) {
                hub.run(10);
                myo.requestBatteryLevel();
            }
        };
        Thread thread = new Thread(runnable2);
        thread.setName("Thread-Myo-Battery");
        thread.start();

        hub.addListener(new MyoListener(c.getProgressbarMyo(), c.getGesturePerformed(), c.getArmActive(),
                c.getStatusConnectionLabelMyo(), c.getStatusPairLabelMyo(), c.getStatusWarmupLabelMyo(), ardrone));


        // Battery
        BatteryInDecimal bid = new BatteryInDecimal(ardrone);
        bid.setProgressbarDrone(c.getProgressbarDrone());

        // Altitude
        Altitude altitude = new Altitude(ardrone);
        altitude.setAttitudeLabel((c.getHeightDrone()));

        // Speed
        SpeedLabel speed = new SpeedLabel(ardrone);
        speed.setSpeedLabel(c.getSpeedDrone());

        // Connection State
        ConnectionState cs = new ConnectionState(ardrone);
        cs.setNavadataStateLabel(c.getStatusNavdataLabelDrone());
        cs.setVideoStateLabel(c.getStatusVideoLabelDrone());
        cs.setCommandStateLabel(c.getStatusConnectionLabelDrone());

        // Video
        Video video = new Video(ardrone, c);
        video.start();

        // Main
        stage.setTitle("Geier Dressur");
        stage.setScene(scene);
        stage.show();

    }

    public String getCurrentKey() {
        return currentKey;
    }

    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

}
