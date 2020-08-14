package controllers;

import entity.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.AlertUtils;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController extends AbstractController implements Initializable {


    @FXML
    private TextField searchTfd;

    @FXML
    private Text numberTfd;

    @FXML
    private Text soldeTfd;

    @FXML
    private Text firstnameTfd;

    @FXML
    private Pane displayDataTable;

    @FXML
    private Text lastnameTfd;

    @FXML
    private Text cinTfd;

    @FXML
    private Text emailTfd;

    @FXML
    private Text adressTfd;

    @FXML
    private Text creadAtTfd;
    @FXML
    private TableView<OperationEntity> table;


    @FXML
    private Pane displayData;

    @FXML
    private Text textHeroOperation;

    @FXML
    private TableColumn<OperationEntity, String> operationColumn;

    @FXML
    private TableColumn<OperationEntity, Double> soldeColumn;

    @FXML
    private TableColumn<OperationEntity, Date> dateColumn;

    @FXML
    void handleSubmit(ActionEvent event) {
        searchCompte();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCustomerDetailInit();
    }
    /**
     * permet d'initialiser le tableau de client
     */
    private void showCustomerDetailInit(){
        displayData.setVisible(false);
        displayDataTable.setVisible(false);
    }

    private void searchCompte()
    {
        String q = searchTfd.getText().trim();
        if(q.isEmpty()){
            AlertUtils.showMessage("Le champs est obligatoire",
                    "INFO",
                    "Le champs est obligatoire",
                    "WARNING");
            return;
        }
        if(!isCompteNameValidate(q)){
            return;
        }

        try {
          CompteEntity compteEntity = compteServiceInterface.findCompteWithCustomerByNumber(q);
          if(compteEntity == null)
          {
              AlertUtils.showMessage("Aucun compte trouver avec ce nom",
                      "INFO",
                      "Aucun compte trouver avec ce nom",
                      "WARNING");
              return;
          }
            this.showCustomerDetail(compteEntity);

          List<OperationEntity> operationEntity = (List<OperationEntity>) compteServiceInterface.findCompteWithAllOperationBynumber(q).getOperation();
            showAllOperation(operationEntity);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllOperation(List<OperationEntity> operationEntities)
    {
        displayDataTable.setVisible(true);
        table.setVisible(false);
        if(operationEntities.size() <  0){
            textHeroOperation.setText("La liste des operations vide !!! ");
            return;
        }

        table.setVisible(true);
        operationColumn.setCellValueFactory( (e) ->
                new ReadOnlyObjectWrapper<>(e.getValue().getoperationType())
        );

        soldeColumn.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getMontant()));
        dateColumn.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getCreateAt()) );
        this.table.setItems(FXCollections.observableArrayList(operationEntities));

    }

    /**
     * permet d'afficher les donnees du client et du compte
     */
    public void showCustomerDetail(CompteEntity compteEntity)
    {

        if (null != compteEntity){
            displayData.setVisible(true);

            firstnameTfd.setText(compteEntity.getCustomer().getFirstName());
            lastnameTfd.setText(compteEntity.getCustomer().getLastName());
            emailTfd.setText(compteEntity.getCustomer().getEmail());
            cinTfd.setText(compteEntity.getCustomer().getNumberIdentification());
            adressTfd.setText(compteEntity.getCustomer().getAddress());
            numberTfd.setText(compteEntity.getNumero());
            soldeTfd.setText( ""+compteEntity.getSolde());
            creadAtTfd.setText(""+compteEntity.getCreateAt());
        }
    }

}
