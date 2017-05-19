package ch.bbcag.controlcenter.plugins.exceptions;

import javafx.scene.control.Alert;

/**
 * Created by zfatzt on 19.05.2017.
 */
public class ErrorDialogs {
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    public void errorDialogMyo(){
        alert.setTitle("Error Dialog");
        alert.setHeaderText("No Myo Connected");
        alert.showAndWait();
    }

    public void errorDialogScene(){
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Failed to build Gui");
        alert.showAndWait();


    }

    public void errorDialogConnectionDrone(){
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Failed to Connect to Drone");
        alert.showAndWait();


    }

    public void errorDialogVideoDrone(){
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Failed to Open Video Stream");
        alert.showAndWait();
    }

}
