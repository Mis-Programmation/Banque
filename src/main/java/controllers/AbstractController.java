package controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import serviceInterface.CompteServiceInterface;
import serviceInterface.CustomerServiceInterface;
import utils.AlertUtils;

public abstract class AbstractController {

    protected CustomerServiceInterface customerServiceInterface;
    protected CompteServiceInterface compteServiceInterface;

    public AbstractController()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("serviceImp","daoImp");
        this.customerServiceInterface = applicationContext.getBean(CustomerServiceInterface.class);
        this.compteServiceInterface = applicationContext.getBean(CompteServiceInterface.class);
    }

    public boolean isCompteNameValidate(String compteName)
    {
        if(compteName.startsWith("CP")){
            return true;
        }
        AlertUtils.showMessage("Nom du compte invalide " + compteName,"Compte","Entrer un nom de compte valide \n exp: CP000",
                "ERROR");
        return false;
    }
}
