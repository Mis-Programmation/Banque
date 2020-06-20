package main;
import businessLayerImp.CustomerService;
import daoImp.CustomerDaoImp;
import daoInterface.CustomerDaoInterface;
import entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

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
        try {
            CustomerEntity customerEntity = customerDao.findCustomerWithCompte("soko");
            System.out.println(customerEntity);

        }catch (Exception e){
            e.printStackTrace();
        }

//        CompteDaoInterface compteDao = new CompteDaoImp();
//        Compte compte = new Compte();
//        compte.setCompteDao(compteDao);

//        System.out.println("-------------CLIENT-----------------");
//        for (CustomerEntity customerEntity:customerService.findAll()) {
//            System.out.println(customerEntity);
//            System.out.println("----------------------------------------------------");
//        }


//        System.out.println("-------------Compte-----------------");
//        for (CompteEntity compteEntity: compte.findAll()) {
//            System.out.println(compteEntity.getCustomer().getLastName());
//        }



    }
}
