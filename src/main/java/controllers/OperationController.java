package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class OperationController implements Initializable {


    @FXML
    private AnchorPane contentLayout;

    @FXML
    private Button btnHandleSubmit;

    @FXML
    private ComboBox<String> opeationTypeCombox;

    @FXML
    private Pane paneDebiteurAndCrediteur;

    @FXML
    private Label label1;

    @FXML
    private TextField input1;

    @FXML
    private Label label2;

    @FXML
    private TextField input2;


    @FXML
    private TextField heroTitle;

    @FXML
    void handleSubmit(ActionEvent event) {
        String choise = opeationTypeCombox.getValue().toUpperCase();

        switch (choise){
            case "DEPOT" :
                System.out.println(choise);
                break;
            case "RETAIT" :
                System.out.println(choise);
                break;
            case "VIREMENT" :
                System.out.println(choise);
                break;
            default:
                System.out.println("ok");
                break;
        }
    }

    /**
     * permet de faire le depot
     */
    private void payment()
    {
        heroTitle.setText("OPERATION VERSEMENT");
        label1.setText("Numero du compte");
        label2.setText("Montant");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    /**
     * permet d'inilialiser le combo box
     * et cacher les champs
     */
    public void init()
    {
        paneDebiteurAndCrediteur.setVisible(false);
        opeationTypeCombox.setItems(FXCollections.observableArrayList(
                "Depot",
                "Retait",
                "Virement"
        ));
    }

}
