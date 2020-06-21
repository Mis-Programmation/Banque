package main;
import businessLayerImp.CompteService;
import businessLayerImp.CustomerService;
import businessLayerInterface.CompteServiceInterface;
import businessLayerInterface.CustomerServiceInterface;
import daoImp.CompteDaoImp;
import daoImp.CustomerDaoImp;
import daoImp.OperationDaoImp;
import daoInterface.CompteDaoInterface;
import daoInterface.CustomerDaoInterface;
import daoInterface.OperationDaoInterface;
import entity.CompteEntity;
import entity.CustomerEntity;
import entity.PaymentEntity;

import java.sql.Date;
import java.util.Scanner;

public class Main /* extends Application */{

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        CompteDaoInterface compteDao = new CompteDaoImp();
        CustomerDaoInterface customerDao =  new CustomerDaoImp();
        OperationDaoInterface operationDao = new OperationDaoImp();

        CompteServiceInterface compteService     =  new CompteService(compteDao,operationDao);
        CustomerServiceInterface customerService = new CustomerService(customerDao,compteDao);

        CustomerEntity customerEntity ;
        CompteEntity compteEntity;

        try{

//           compteService.transfer(1000,"c2","c1");
           compteEntity = compteDao.findAllOperationForOneCompteBynumber("c4");

           System.out.println(compteEntity);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
