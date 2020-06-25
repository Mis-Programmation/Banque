package businessLayerImp;

import businessLayerInterface.CompteServiceInterface;
import daoInterface.CompteDaoInterface;
import daoInterface.OperationDaoInterface;
import entity.*;
import exception.AmountInsufficient;
import exception.NotFoundEntityException;
import helpers.DatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component("CompteServiceInterface")
public class CompteService implements CompteServiceInterface {
    @Autowired
    private CompteDaoInterface compteDao;
    @Autowired
    private OperationDaoInterface operationDao;
    private static Connection db =  DatabaseHelper.getConnection();

    public void setCompteDao(CompteDaoInterface compteDao) {
        this.compteDao = compteDao;
    }

    public void setOperationDao(OperationDaoInterface operationDao) {
        this.operationDao = operationDao;
    }

    /**
     * permet de faire un versement
     * @param amount
     * @param compteNumber
     * @return
     * @throws SQLException
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

    /**
     * permet de faire un tranfert
     * @param amount
     * @param compteNumber1
     * @param compteNumber2
     * @throws SQLException
     * @throws NotFoundEntityException
     * @throws AmountInsufficient
     */
    @Override
    public void transfer(double amount,String compteNumber1,String compteNumber2)
            throws SQLException, NotFoundEntityException, AmountInsufficient {
        //permet de controler la somme entrer
        if(amount < 1000 || amount > 99999999){
            throw new ArithmeticException("impossible le nombre soit inferieuse a 0 ou superieur a 99999999 "+ amount);
        }

        CompteEntity compteEntity1 = compteDao.findCompteWithCustomerByNumber(compteNumber1);
        if(compteEntity1 == null){
            throw new NotFoundEntityException("Ce compte n'existe pas "+ compteEntity1);
        }
        if(compteEntity1.getSolde() < amount){
            throw new AmountInsufficient("Le montant est superieur a la solde de votre compte");
        }
        CompteEntity compteEntity2 = compteDao.findCompteWithCustomerByNumber(compteNumber2);
        if(compteEntity2 == null){
            throw new NotFoundEntityException("Ce compte n'existe pas "+ compteEntity2);
        }

        compteEntity1.setSolde(compteEntity1.getSolde() - amount);
        compteEntity2.setSolde(compteEntity2.getSolde() + amount);
        try{
            db.rollback();
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
     * @param amount
     * @param compteNumber
     * @throws SQLException
     * @throws NotFoundEntityException
     * @throws AmountInsufficient
     *
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


}
