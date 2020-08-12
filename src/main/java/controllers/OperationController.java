package controllers;

import exception.AmountInsufficient;
import exception.NotFoundEntityException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.AlertUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OperationController extends AbstractController implements Initializable{

    @FXML
    private Button btnHandleSubmit;

    @FXML
    private ComboBox<String> opeationTypeCombox;

    @FXML
    private Pane paneDebiteurAndCrediteur;

    @FXML
    private Label label1;

    @FXML
    private TextField  soldeTfd;

    @FXML
    private TextField input1;

    @FXML
    private Label label2;

    @FXML
    private Text heroText;

    @FXML
    private TextField input2;


    @FXML
    private VBox vboxSolde;

    @FXML
    void handleSubmit(ActionEvent event) {
        // permet de deposer
        withdraw();
        // permet de retirer
        payment();
        // permet de tranferer
        tranfert();
    }

    /**
     * permet de faire retrait
     */
    private void withdraw()
    {
        if(!(input1.getId().equals("compteNumber") && input2.getId().equals("amount"))){
            return;
        }
        Double amount = null;
        String compteName = this.input1.getText().trim();
        if(!isCompteNameValidate(compteName)){
            return;
        }

        if(input2.getText().trim().equals("")){
            AlertUtils.showMessage("Tout les champs sont requis","Champs","Tout les champs sont requis",
                    "WARNING");
            return;
        }

        try{
            amount  = Double.parseDouble(this.input2.getText().trim());
        }catch (NumberFormatException e){
            AlertUtils.showMessage("Solde","Solde","Entrer une valeur numerique",
                    "ERROR");
            return;
        }

        try {
            compteServiceInterface.withdraw(amount,compteName);
            AlertUtils.showMessage("Retait de " + amount + "réussi"
                    ,"Retait"
                    ,"Retait réussi",
                    "INFORMATION");
            input1.setText("");
            input2.setText("");
        } catch (SQLException e) {
            AlertUtils.showMessage("Erreur de retait"
                    ,"Solde"
                    ,"Erreur de retait",
                    "ERROR");
        } catch (NotFoundEntityException e) {
            AlertUtils.showMessage("Compte "+ compteName + " n'existe pas "
                    ,"Solde"
                    ,"Entrer un numero de compte qui existe",
                    "ERROR");
        } catch (AmountInsufficient amountInsufficient) {
            AlertUtils.showMessage("Le montant est superieur a la solde de votre compte"
                    ,"Solde"
                    ,"Veillez esseyer un motant plus petit",
                    "ERROR");
        }catch (ArithmeticException e){
        AlertUtils.showMessage("Impossible le Montant soit inferieur a 0 ou superieur a 99999999"
                ,"Solde"
                ,"Impossible le Montant soit inferieure a 0 ou superieur a 99999999",
                "ERROR");
    }
    }

    /**
     * permet de faire un tranfert
     */
    private void tranfert()
    {
        if(!(input1.getId().equals("compteNumberEnvoyeur")
                && input2.getId().equals("compteNumberReceveur")
        )){
            return;
        }

        Double amount = null;
        String compteNameEnvoyer = this.input1.getText().trim();
        String compteNameReceveur = this.input2.getText().trim();
        if(compteNameEnvoyer.equals("") || compteNameReceveur.equals("") || soldeTfd.getText().trim().equals("")){
            AlertUtils.showMessage("Tout les champs sont requis","Champs","Tout les champs sont requis",
                    "WARNING");
            return;
        }
        if (!isCompteNameValidate(compteNameEnvoyer)){
            System.out.println("com 1");
            return;
        }
        if (!isCompteNameValidate(compteNameReceveur)){
            System.out.println("com 2");
            return;
        }
        try{
            amount  = Double.parseDouble(this.soldeTfd.getText().trim());
        }catch (NumberFormatException e){
            AlertUtils.showMessage("Solde","Solde","Entrer une valeur numerique",
                    "ERROR");
            return;
        }
        try {
            compteServiceInterface.transfer(amount,compteNameEnvoyer,compteNameReceveur);
            AlertUtils.showMessage("Virement de " + amount + "réussi"
                    ,"Virement"
                    ,"Virement réussi",
                    "INFORMATION");
            input1.setText("");
            input2.setText("");
            soldeTfd.setText("");
        } catch (SQLException e) {
            AlertUtils.showMessage("Erreur de virement"
                    ,"Solde"
                    ,"Erreur de retait",
                    "ERROR");
        } catch (NotFoundEntityException e) {
            AlertUtils.showMessage(""+ e.getMessage() +""
                    ,"Compte"
                    ,"Entrer un numero de compte qui existe",
                    "ERROR");
        }catch (AmountInsufficient amountInsufficient) {
            AlertUtils.showMessage("Le montant est superieur a la solde de votre compte"
                    ,"Solde"
                    ,"Veillez esseyer un motant plus petit",
                    "ERROR");
        }catch (ArithmeticException e){
            AlertUtils.showMessage("Impossible le Montant soit inferieure a 0 ou superieur a 99999999"
                    ,"Solde"
                    ,"Impossible le Montant soit inferieure a 0 ou superieur a 99999999",
                    "ERROR");
        }

    }

    /**
     * permet faire le depot
     */
    private void payment(){

        if(!(input1.getId().equals("compteNumberPayment") && input2.getId().equals("amount"))){
            return;
        }

        Double amount = null;
        String compteName = this.input1.getText().trim();


        if(compteName.equals("") || input2.getText().trim().equals("")){
            AlertUtils.showMessage("Tout les champs sont requis","Champs","Tout les champs sont requis",
                    "WARNING");
            return;
        }

        // permet de valide le nom du compte
        if (!isCompteNameValidate(compteName)){
            return;
        }
        try{
            amount  = Double.parseDouble(this.input2.getText().trim());
        }catch (NumberFormatException e){
            AlertUtils.showMessage("Solde","Solde","Entrer une valeur numerique",
                    "ERROR");
            return;
        }
        try {
            compteServiceInterface.payment(amount,compteName);
            AlertUtils.showMessage("Depot de " + amount + "réussi"
                    ,"Depot"
                    ,"Retait réussi",
                    "INFORMATION");
            input1.setText("");
            input2.setText("");
        } catch (SQLException e) {
            AlertUtils.showMessage("Erreur de depot"
                    ,"Solde"
                    ,"Erreur de depot",
                    "ERROR");
        } catch (NotFoundEntityException e) {
            AlertUtils.showMessage("Compte "+ compteName + " n'existe pas "
                    ,"Compte"
                    ,"Entrer un numero de compte qui existe",
                    "ERROR");
        }catch (ArithmeticException e){
            AlertUtils.showMessage("Impossible le Montant soit inferieure a 0 ou superieur a 99999999"
                    ,"Solde"
                    ,"Impossible le Montant soit inferieure a 0 ou superieur a 99999999",
                    "ERROR");
        }
    }

    /**
     * permet de choisir l'option
     * @param event
     */
    @FXML
    void HandleChoise(ActionEvent event) {
        String choise = opeationTypeCombox.getValue().toUpperCase();
        btnHandleSubmit.setVisible(true);
        switch (choise) {
            case "DEPOT" -> paymentInit();
            case "RETAIT" -> withdrawalInit();
            case "VIREMENT" -> transfertInit();
            default -> System.out.println("ok");
        }
    }


    /**
     * permet d'initialiser retrait
     */
    private void withdrawalInit()
    {

        heroText.setText("Operation retrait");
        // changer les text des label
        this.label1.setText("Numero du compte");
        this.label2.setText("Montant");
        // changer les Id des champs
        input1.setId("compteNumber");
        input2.setId("amount");

        btnHandleSubmit.setText("Retirer");
        this.paneDebiteurAndCrediteur.setVisible(true);
        this.btnHandleSubmit.setVisible(true);
        this.vboxSolde.setVisible(false);

    }

    /**
     * permet d'intialiser le tranfert
     */
    private void transfertInit(){


        this.heroText.setText("Operation virement");
        this.label1.setText("N du compte envoyeur");
        this.label2.setText("N Compte receveur");
        this.input1.setId("compteNumberEnvoyeur");
        this.input2.setId("compteNumberReceveur");

        this.paneDebiteurAndCrediteur.setVisible(true);
        this.btnHandleSubmit.setVisible(true);
        btnHandleSubmit.setText("Tranferer");
        vboxSolde.setVisible(true);
    }


    /**
     * permet d'intialiser le depot
     */
    private void paymentInit(){

        this.heroText.setText("Operation depot");
        this.label1.setText("Numero du compte");
        this.label2.setText("Montant");
        this.input1.setId("compteNumberPayment");
        this.input2.setId("amount");

        this.paneDebiteurAndCrediteur.setVisible(true);
        vboxSolde.setVisible(false);
        this.btnHandleSubmit.setVisible(true);
        btnHandleSubmit.setText("Deposer");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    /**
     * permet d'inilialiser le combo box
     * et cacher les champs
     */
    public void init()
    {
        opeationTypeCombox.setItems(FXCollections.observableArrayList(
                "Depot",
                "Retait",
                "Virement"
        ));

        paneDebiteurAndCrediteur.setVisible(false);
        vboxSolde.setVisible(false);
        btnHandleSubmit.setVisible(false);
    }
}

