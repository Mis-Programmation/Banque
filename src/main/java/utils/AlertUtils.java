package utils;

import javafx.scene.control.Alert;

public class AlertUtils {

    public static void showMessage(String headText,String title,String content,String alertType){

        Alert alert = null;
        if(alertType.trim().toUpperCase().equals("ERROR")){
            alert =  new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        }else if(alertType.trim().toUpperCase().equals("WARNING")){
            alert =  new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        }else {
            alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        }

        alert.setHeaderText(headText);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
