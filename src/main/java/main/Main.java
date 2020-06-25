package main;

import businessLayerInterface.AdminServiceInterface;
import businessLayerInterface.CompteServiceInterface;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main /* extends Application */{

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("businessLayerImp","daoImp");
        AdminServiceInterface adminService =  applicationContext.getBean(AdminServiceInterface.class);
        CompteServiceInterface compteService =  applicationContext.getBean(CompteServiceInterface.class);
        try {
            compteService.payment(10000,"sklsklsjsk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
