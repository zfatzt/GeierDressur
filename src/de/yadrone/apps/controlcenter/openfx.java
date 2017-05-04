package de.yadrone.apps.controlcenter;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class openfx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(openfx.class.getClass().getResource("//controlcenter.fxml"));
        primaryStage.setTitle("Control panel");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
