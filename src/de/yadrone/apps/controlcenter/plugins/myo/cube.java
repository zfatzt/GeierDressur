package de.yadrone.apps.controlcenter.plugins.myo;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class cube extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a Box
        Box box = new Box();

//        DataCollector dc = new DataCollector();


        //Setting the properties of the Box
        box.setWidth(150.0);
        box.setHeight(150.0);
        box.setDepth(150.0);

        //Creating the translation transformation
        Translate translate = new Translate();
        translate.setX(300);
        translate.setY(150);
        translate.setZ(25);

        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
//        rxBox.setAngle(dc.getYawW());
//        ryBox.setAngle(dc.getRollW());
//        rzBox.setAngle(dc.getPitchW());
        box.getTransforms().addAll(translate, rxBox, ryBox, rzBox);

        //Creating a Group object
        Group root = new Group(box);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Drawing a cylinder");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]) {
        Hub hub = new Hub("com.example.hello-myo");

        System.out.println("Attempting to find a Myo...");
        Myo myo = hub.waitForMyo(10000);

        if (myo == null) {
            throw new RuntimeException("Unable to find a Myo!");
        }

        System.out.println("Connected to a Myo armband!");

        launch(args);
    }
}
