package serviceImp;

import exception.FoundEntityException;
import org.springframework.stereotype.Service;
import serviceInterface.CompteServiceInterface;
import daoInterface.CompteDaoInterface;
import daoInterface.OperationDaoInterface;
import entity.*;
import exception.AmountInsufficient;
import exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLException;
import java.util.List;

@Service("CompteServiceInterface")
public class CompteService implements CompteServiceInterface {
    @Autowired
    private CompteDaoInterface compteDao;
    @Autowired
    private OperationDaoInterface operationDao;

    public void setCompteDao(CompteDaoInterface compteDao) {
        this.compteDao = compteDao;
    }

    public void setOperationDao(OperationDaoInterface operationDao) {
        this.operationDao = operationDao;
    }

    /**
     * permet de faire un versement
     */
    @Override
    public void payment(double amount,String compteNumber) throws SQLException, NotFoundEntityException {
        if(amount < 1000 || amount > 99999999){
            throw new ArithmeticException("impossible le nombre soit inferieuse a 0 ou superieur a 99999999 "+ amount);
        }
        CompteEntity compteEntity = compteDao.findCompteWithCustomerByNumber(compteNumber);
        if(compteEntity == null){
            throw new NotFoundEntityException("Ce compte n'existe pas "+ compteNumber);
        }
        compteEntity.setSolde(compteEntity.getSolde() + amount);
        compteDao.updateAmount(compteEntity);
        compteEntity.setSolde(amount);
        operationDao.save(new PaymentEntity(compteEntity));

    }

    /** Permet d'ajouter un compte */
    @Override
    public CompteEntity save(CompteEntity compteEntity) throws SQLException, FoundEntityException {
        if(null != compteDao.findOne("numero",compteEntity.getNumero())){
            throw new FoundEntityException("ce client existe deja");
        }
        return compteDao.save(compteEntity);
    }

    /**
     * Permet de chercher un compte avec son propretaire
     */
    @Override
    public CompteEntity findCompteWithCustomerByNumber(String number) throws SQLException {
        return compteDao.findCompteWithCustomerByNumber(number);
    }

    /**
     * permet d'afficher tout les comptes
     */
    @Override
    public List<CompteEntity> findAll() throws SQLException {
        return compteDao.findAll();
    }

    /**
     * permet de rechercher un compte avec tout les operations
     * @param number
     */
    @Override
    public CompteEntity findCompteWithAllOperationBynumber(String number) throws SQLException {
        return compteDao.findCompteWithCustomerByNumber(number);
    }

    /**
     * permet de faire un tranfert
    */
    @Override
    public void transfer(double amount,String compteNumber1,String compteNumber2)
            throws SQLException, NotFoundEntityException, AmountInsufficient {
        //permet de controler la somme entrer
        if(amount < 1000 || amount > 99999999){
            throw new ArithmeticException("impossible le nombre soit inferieur a 0 ou superieur a 99999999 "+ amount);
        }

        CompteEntity compteEntity1 = compteDao.findCompteWithCustomerByNumber(compteNumber1);
        if(compteEntity1 == null){
            throw new NotFoundEntityException("Ce compte n'existe pas "+ compteNumber1);
        }
        if(compteEntity1.getSolde() < amount){
            throw new AmountInsufficient("Le montant est superieur a la solde de votre compte");
        }
        CompteEntity compteEntity2 = compteDao.findCompteWithCustomerByNumber(compteNumber2);
        if(compteEntity2 == null){
            throw new NotFoundEntityException("Ce compte n'existe pas "+ compteNumber2);
        }

        compteEntity1.setSolde(compteEntity1.getSolde() - amount);
        compteEntity2.setSolde(compteEntity2.getSolde() + amount);
        try{
            compteDao.updateAmount(compteEntity1);
            compteDao.updateAmount(compteEntity2);

        }catch (SQLException e){
            throw e;
        }

        compteEntity1.setSolde(amount);
        compteEntity2.setSolde(amount);

        operationDao.save(new TransferEntity(compteEntity1));
        operationDao.save(new WithdrawalEntity(compteEntity2));

    }

    /**
     * Permet de faire un retait
     */
    @Override
    public void withdraw(double amount,String compteNumber) throws SQLException, NotFoundEntityException, AmountInsufficient {

        if(amount < 1000 || amount > 99999999){
            throw new ArithmeticException("impossible le nombre soit inferieuse a 0 ou superieur a 99999999 "+ amount);
        }
        CompteEntity compteEntity = compteDao.findCompteWithCustomerByNumber(compteNumber);
        if(compteEntity == null){
            throw new NotFoundEntityException("Ce compte n'existe pas "+ compteNumber);
        }
        if(compteEntity.getSolde() < amount){
            throw new AmountInsufficient("Le montant est superieur a la solde de votre compte");
        }

        compteEntity.setSolde(compteEntity.getSolde() - amount);

        OperationEntity operation = new WithdrawalEntity();
        operation.setCompte(compteEntity);
        operation.setMontant(amount);



        compteDao.updateAmount(compteEntity);
        operationDao.save(operation);
    }

    @Override
    public List<OperationService> getAllOperation() {
        return null;
    }

    @Override
    public List<CompteEntity> findCompteWithCustomer() throws SQLException {
       return compteDao.findCompteWithCustomer();
    }

}
