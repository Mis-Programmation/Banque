package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashbordCustomerController {


    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnListeCustomer;

    @FXML
    private AnchorPane contentLayout;

    /**
     * permet de changer entre les views
     * @param event
     * @throws IOException
     */
    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if(event.getSource() == btnAddCustomer){
            // charger le views
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerView/AddCustomer.fxml"));
            // pusher dans le clientLayout comme enfant
            contentLayout.getChildren().setAll(anchorPane);
        }else if(event.getSource() == btnListeCustomer)
        {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerView/listeCustomer.fxml"));
            contentLayout.getChildren().setAll(anchorPane);
        }
    }

//    @FXML
//    void onAddCustomer(ActionEvent event) {
//        Parent root = null;
//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    //    root = FXMLLoader.load(getClass().getResource("/view/DashbordCustomer.fxml"));
//        Scene scene = new Scene(root);
//        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
//        window.setTitle("Dashboard");
//        window.setScene(scene);
//        window.show();
//    }





}
