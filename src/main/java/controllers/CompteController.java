package controllers;

import entity.CompteEntity;
import entity.CustomerEntity;
import exception.FoundEntityException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import utils.AlertUtils;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompteController extends AbstractController implements Initializable {

    @FXML
    private Button btnHandleSubmit;

    @FXML
    private AnchorPane contentLayout;

    @FXML
    private TableView<CompteEntity> tableCompte;

    @FXML
    private TableColumn<CompteEntity, String> numberCell;

    @FXML
    private TableColumn<CompteEntity, Double> soldeCell;

    @FXML
    private TableColumn<CompteEntity, String> customerCell;

    @FXML
    private TableColumn<CompteEntity, Date> createdAtCell;

    @FXML
    private TextField numero;

    @FXML
    private TextField solde;

    @FXML
    private ComboBox<CustomerEntity> customerId;

    @FXML
    void handleSubmit(ActionEvent event) {
        addCompte();
        btnHandleSubmit.setDisable(false);
    }

    // permet d'ajouter un compte
    public void addCompte()
    {
        // cutomerId => retour l'objec customer
        if(numero.getText().trim().isEmpty() || solde.getText().trim().isEmpty() || customerId.getValue() == null){
            AlertUtils.showMessage("Errors","ok","Toutes les champs sont required","WARNING");
            return;
        }
        CompteEntity compteEntity = new CompteEntity();
        compteEntity.setCustomer(this.customerId.getValue());
        try {
            compteEntity.setSolde(Double.parseDouble(solde.getText().trim()));
        }catch (NumberFormatException e){
            AlertUtils.showMessage("Solde","Solde","Entrer une valeur numerique",
                    "ERROR");
            return;
        }
        if( (int) Double.parseDouble(solde.getText().trim()) < 0){
            AlertUtils.showMessage("Le solde est trop petit","ok",solde.getText().trim(),"WARNING");
            return;
        }
        compteEntity.setNumero(numero.getText().trim());
        try {
            compteServiceInterface.save(compteEntity);
            double nb =  Math.random() * 1000;
            numero.setText("CP"+(int)nb);
            numero.setText(""+nb);
            solde.setText("");
            AlertUtils.showMessage("Sauvegarde","ok","Le compte a bien ete ajouter","INFORMATION");
        } catch (SQLException e) {
            AlertUtils.showMessage("Sauvegarde","ok","Erreur de sauvegarde","ERROR");
        } catch (FoundEntityException e) {
            AlertUtils.showMessage("Ce compte existe deja","ok","Compte : "+ numero.getText().trim(),"WARNING");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCombox();
        initTableCompte();
    }

    private void initCombox(){
        if(null != customerId){
            try {
                // permet de rendre la liste observable
                // et il va executer pour chaque element la method toString
                customerId.getItems().addAll(FXCollections.observableArrayList(customerServiceInterface.findAll()));
            } catch (SQLException e) {
                AlertUtils.showMessage("Errors",
                        "ok",
                        "Erreur de recuperation des donnees",
                        "WARNING");
            }
            double nb =  Math.random() * 1000;
            numero.setText("CP"+(int)nb);
            numero.setDisable(true);
        }
    }
    private void initTableCompte()
    {
        if ( null != this.numberCell ){
            this.numberCell.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getNumero()));
            soldeCell.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getSolde()));
            customerCell.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getCustomer().getLastName()));
            createdAtCell.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getCreateAt()));
            try {
                this.tableCompte.setItems(FXCollections.observableArrayList(compteServiceInterface.findCompteWithCustomer()));
            } catch (SQLException e) {
                AlertUtils.showMessage("Erreur de recuperation des donnees",
                        "ok",
                        "Erreur",
                        "WARNING");
            }
        }
    }


}
