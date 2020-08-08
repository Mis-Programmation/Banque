package controllers;

import daoImp.CustomerDaoImp;
import daoInterface.CustomerDaoInterface;
import entity.CustomerEntity;
import exception.FoundEntityException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import serviceInterface.CustomerServiceInterface;
import utils.AlertUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerController {

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

    private CustomerServiceInterface customerServiceInterface;

    public CustomerController()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("serviceImp","daoImp");
        this.customerServiceInterface = applicationContext.getBean(CustomerServiceInterface.class);
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

    @FXML
    void seeListe(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/DashbordCustomer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Dashboard");
        window.setScene(scene);
        window.show();
    }

}
