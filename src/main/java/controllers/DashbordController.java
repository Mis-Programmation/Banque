package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class DashbordController {


    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnListeCustomer;

    @FXML
    private Button btnAddCompte;

    @FXML
    private AnchorPane contentLayout;

    @FXML
    private Button btnListeCompte;

    @FXML
    private Button btnOperation;

    /**
     * permet de changer entre les views
     * @param event
     * @throws IOException
     */
    @FXML
    void handleClicks(ActionEvent event) throws IOException {

        if (event.getSource() == btnAddCustomer) {
            // charger le views
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerView/AddCustomer.fxml"));
            // pusher dans le clientLayout comme enfant
            contentLayout.getChildren().setAll(anchorPane);
        } else if (event.getSource() == btnListeCustomer) {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerView/listeCustomer.fxml"));
            contentLayout.getChildren().setAll(anchorPane);
        } else if (event.getSource() == btnAddCompte) {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CompteView/addCompte.fxml"));
            contentLayout.getChildren().setAll(anchorPane);
        } else if (event.getSource() == btnListeCompte) {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CompteView/listeCompte.fxml"));
                contentLayout.getChildren().setAll(anchorPane);
        }else if(event.getSource() == btnOperation)
        {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Operation/operationView.fxml"));
            contentLayout.getChildren().setAll(anchorPane);
        }
    }






}
