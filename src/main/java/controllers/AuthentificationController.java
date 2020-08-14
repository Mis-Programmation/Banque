package controllers;

import entity.AdminEntity;
import exception.IncorrectPasswordException;
import exception.NotFoundEntityException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static main.Main.getPrimaryStage;
import utils.AlertUtils;

import java.io.IOException;
import java.sql.SQLException;

public class AuthentificationController extends AbstractController {

    @FXML
    private TextField pseaudoTfd;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private PasswordField passwordTfd;

    @FXML
    void handleSubmit(ActionEvent event) {
        String pseaudo  = pseaudoTfd.getText().trim();
        String password = passwordTfd.getText().trim();
        AdminEntity adminEntity = null;
        if(pseaudo.isEmpty() || password.isEmpty()){
            AlertUtils.showMessage("Tous les champs obligatoires ","Validation","Tous les champs obligatoires","ERROR");
           return;
        }

        try {
           adminEntity =  adminServiceInterface.login(pseaudo,password);
        } catch (SQLException e) {
            AlertUtils.showMessage("Erreur SQL","Validation","Veillez recommancer ulterieur","ERROR");
            return;
        } catch (NotFoundEntityException | IncorrectPasswordException e) {
            AlertUtils.showMessage("Erreur de login ou mot de passe","Validation","Erreur de login ou mot de passe","ERROR");
           passwordTfd.setText("");
           return;
        }

        if (null != adminEntity){
            getPrimaryStage().close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Dashbord.fxml"));
                Scene scene = new Scene(root);
                getPrimaryStage().setScene(scene);
                getPrimaryStage().show();
                getPrimaryStage().setTitle("Dashboard");
            } catch (IOException e) {
                System.out.println("impossible d'afficher la fenetre");
            }



        }


    }

}
