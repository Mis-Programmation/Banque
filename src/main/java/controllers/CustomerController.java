package controllers;
import entity.CustomerEntity;
import exception.FoundEntityException;
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
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import serviceInterface.CustomerServiceInterface;
import utils.AlertUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

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
    private TableColumn<CustomerEntity, Integer> idCell;
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


    private CustomerServiceInterface customerServiceInterface;
    private List<CustomerEntity> customerEntities;
    public CustomerController()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("serviceImp","daoImp");
        this.customerServiceInterface = applicationContext.getBean(CustomerServiceInterface.class);
    }

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

    @FXML
    void handleSubmit(ActionEvent event) {
        addCustomer();
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableCustomer();
    }

    /**
     * permet d'initialier le tableau de client
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

            this.idCell.setCellValueFactory( e -> new ReadOnlyObjectWrapper<>(e.getValue().getId())  );
            this.firstNameCell.setCellValueFactory( cell -> new ReadOnlyObjectWrapper<>( cell.getValue().getFirstName() ));
            this.lastNameCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>(event.getValue().getLastName()) );
            this.idCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>(event.getValue().getId()) );
            this.emailCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>( event.getValue().getEmail()) );
            this.adressCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>( event.getValue().getAddress()) );
            this.dateOfTheBirthCell.setCellValueFactory( event -> new ReadOnlyObjectWrapper<>( event.getValue().getDateOfTheBirth()) );
            this.tableCustomer.setItems(FXCollections.observableArrayList(customerEntities));
        }
    }
}
