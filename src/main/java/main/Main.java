package main;
import daoInterface.CompteDaoInterface;
import daoInterface.CustomerDaoInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import serviceImp.CompteService;
import serviceInterface.CompteServiceInterface;
import serviceInterface.CustomerServiceInterface;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/DashbordCustomer.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("serviceImp","daoImp");
//        CustomerServiceInterface compteDaoInterface = applicationContext.getBean(CustomerServiceInterface.class);
//
//        try {
//            System.out.println(compteDaoInterface.findAll());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        launch(args);
    }
}



