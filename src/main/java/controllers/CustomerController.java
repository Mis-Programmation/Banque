package controllers;
import entity.CompteEntity;
import entity.CustomerEntity;
import exception.FoundEntityException;
import exception.NotFoundEntityException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.AlertUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController extends AbstractController implements Initializable {

    @FXML
    private TextField numberIdentification;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private DatePicker dateOfTheBirth;

    @FXML
    private Text firstnameTfd;

    @FXML
    private Text lastnameTfd;

    @FXML
    private Text cinTfd;

    @FXML
    private Pane paneLayout;


    @FXML
    private Text emailTfd;

    @FXML
    private Text adressTfd;

    @FXML
    private TableColumn<CompteEntity, String> compteColumn;

    @FXML
    private TableColumn<CompteEntity, Double> soldeColumn;

    @FXML
    private TableColumn<CompteEntity, java.sql.Date> createdColumn;

    @FXML
    private TableColumn<CustomerEntity, String> idCell;
    @FXML
    private TableColumn<CustomerEntity, String> firstNameCell;
    @FXML
    private TableColumn<CustomerEntity, String> lastNameCell;
    @FXML
    private TableColumn<CustomerEntity, String> emailCell;
    @FXML
    private TableColumn<CustomerEntity, String> adressCell;
    @FXML
    private TableColumn<CustomerEntity, Date> dateOfTheBirthCell;
    @FXML
    private TableView<CustomerEntity> tableCustomer;

    @FXML
    private TableView<CompteEntity> tableCompte;



    @FXML
    private TextField searchTfd;

    private List<CustomerEntity> customerEntities;


    @FXML
    void seeListe(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Dashbord.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Dashboard");
        window.setScene(scene);
        window.show();
    }

    // permet d'ajouter un client
    @FXML
    void handleSubmit(ActionEvent event) {
        addCustomer();
    }

    private CustomerEntity customerEntity;
    /**
     * permet d'ajouter un client
     */
    public void addCustomer()
    {
        CustomerEntity customerEntity  = new CustomerEntity();
        // recuperation contenu qui sont dans le champs
        customerEntity.setNumberIdentification(numberIdentification.getText().trim());
        customerEntity.setFirstName(firstName.getText().trim());
        customerEntity.setLastName(lastName.getText().trim());
        customerEntity.setEmail(email.getText().trim());
        customerEntity.setAddress(address.getText().trim());
        LocalDate localDate = dateOfTheBirth.getValue();
        if(numberIdentification.getText().trim().equals("")
                || firstName.getText().trim().equals("")
                || lastName.getText().trim().equals("")
                || email.getText().trim().equals("")
                || address.getText().trim().equals("")
        ){
            AlertUtils.showMessage("Alert",
                    "ok",
                    "Touts les champs sont required",
                    "WARNING");
            return;
        }
        try {
            //permet de sauvegarder le client
            this.customerServiceInterface.save(customerEntity);
            AlertUtils.showMessage("enregistrement",
                    "ok",
                    "le client a bien ete enregistrer",
                    "INFORMATION");
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            address.setText("");
            numberIdentification.setText("");
        } catch (SQLException e) {
            AlertUtils.showMessage("enregistrement",
                    "ok",
                    "le client n'a ete enregistrer",
                    "ERROR");
        }catch (FoundEntityException e){
            AlertUtils.showMessage("enregistrement",
                "ok",
                "le client existe deja",
                "WARNING");
        }
    }

    // permet de rechercher un client
    @FXML
    void handleSubmitSearch(ActionEvent event) {
        String searchTfd = this.searchTfd.getText().trim();
        if(searchTfd.isEmpty()){
            AlertUtils.showMessage("Le champs ne doit etre vide","validation"
                    ,"Le champs ne doit etre vide","ERROR");
            return;
        }
        try {
            AnchorPane parent = FXMLLoader.load(getClass().getResource("/view/CustomerView/ShowDetailCustomer.fxml"));
            paneLayout.getChildren().setAll(parent);
            this.customerEntity = customerServiceInterface.findCustomerWithCompte(searchTfd);
        } catch (SQLException | NotFoundEntityException e) {
            AlertUtils.showMessage("Ce client n'existe pas","Donnee"
                    ,"Le champs ne doit etre videCe client n'existe pas","ERROR");

        }catch (IOException e){
            System.out.println("error d'affichage de fenetre");
        }
    }

    private void getCustomWithCompte()
    {
        CustomerEntity customerEntity = null;
        if(!searchTfd.getText().trim().isEmpty()){
            try {
                customerEntity = customerServiceInterface.findCustomerWithCompte(searchTfd.getText().trim());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NotFoundEntityException e) {
                e.printStackTrace();
            }
        }

        if(null != customerEntity){
            firstnameTfd.setText(customerEntity.getFirstName());
            lastnameTfd.setText(customerEntity.getLastName());
            emailTfd.setText(customerEntity.getEmail());
            adressTfd.setText(customerEntity.getAddress());
            cinTfd.setText(customerEntity.getNumberIdentification());

            if(null != customerEntity.getCompte()){
                this.compteColumn.setCellValueFactory(e ->
                        new ReadOnlyObjectWrapper<>(e.getValue().getNumero()));
                this.soldeColumn.setCellValueFactory( e ->
                        new ReadOnlyObjectWrapper<>(e.getValue().getSolde()) );
                this.createdColumn.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getCreateAt()) );

                this.tableCompte.setItems(FXCollections.observableArrayList(customerEntity.getCompte()));
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableCustomer();
    }



    /**
     * permet d'initialiser le tableau de client
     */
    private void initTableCustomer()
    {
        if(null != this.idCell){
            try {
                customerEntities = customerServiceInterface.findAll();
            }

            catch (SQLException e) {
                AlertUtils.showMessage("Erreur de recuperation des donnees",
                        "ok",
                        "Erreur",
                        "WARNING");
            }

            this.idCell.setCellValueFactory( e ->
                    new ReadOnlyObjectWrapper<>(e.getValue().getNumberIdentification())  );
            this.firstNameCell.setCellValueFactory( cell -> new ReadOnlyObjectWrapper<>( cell.getValue().getFirstName() ));
            this.lastNameCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>(event.getValue().getLastName()) );
            this.emailCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>( event.getValue().getEmail()) );
            this.adressCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>( event.getValue().getAddress()) );
            this.dateOfTheBirthCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>( event.getValue().getDateOfTheBirth()) );
            this.tableCustomer.setItems(FXCollections.observableArrayList(customerEntities));
        }
    }


}
