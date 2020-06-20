package main;
import businessLayerImp.Compte;
import businessLayerImp.Customer;
import businessLayerInterface.CustomerBLInterface;
import daoImp.CompteDaoImp;
import daoImp.CustomerDaoImp;
import daoInterface.CompteDaoInterface;
import daoInterface.CustomerDaoInterface;
import entity.CompteEntity;
import entity.CustomerEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Main /* extends Application */{

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    public static void main(String[] args) {

        CustomerDaoInterface customerDao = new CustomerDaoImp();
        Customer customer = new Customer();
        customer.setCustomerDao(customerDao);

        CompteDaoInterface compteDao = new CompteDaoImp();
        Compte compte = new Compte();
        compte.setCompteDao(compteDao);

//        System.out.println("-------------CLIENT-----------------");
//        for (CustomerEntity customerEntity:customer.findAll()) {
//            System.out.println(customerEntity);
//            System.out.println("----------------------------------------------------");
//        }


//        System.out.println("-------------Compte-----------------");
//        for (CompteEntity compteEntity: compte.findAll()) {
//            System.out.println(compteEntity.getCustomer().getLastName());
//        }

        System.out.println("-------------Cree un Compte-----------------");
        CustomerEntity customerEntity = customer.findByName("soko");
        if(customerEntity != null){
            System.out.println(customerEntity);

//            CompteEntity compteEntity = new CompteEntity("55dnszx5dddn",10000000,customerEntity);
//            compte.save(compteEntity);
            System.out.println("le compte a bien ete cree");
            System.out.println("----------- Le compte -----------");
            CompteEntity compteEntity = compte.findByNumber("55dnszx5dddn");
            System.out.println(compteEntity);

        }else
        {
            System.out.println("cette personne n'existe pas");
        }

    }
}
