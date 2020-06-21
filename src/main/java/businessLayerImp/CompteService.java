package businessLayerImp;

import businessLayerInterface.CompteServiceInterface;
import daoInterface.CompteDaoInterface;
import daoInterface.OperationDaoInterface;
import entity.CompteEntity;
import entity.PaymentEntity;
import entity.TransferEntity;
import entity.WithdrawalEntity;
import exception.AmountInsufficient;
import exception.NotFoundEntityException;

import java.sql.SQLException;
import java.util.List;

public class CompteService implements CompteServiceInterface {
    private CompteDaoInterface compteDao;
    private OperationDaoInterface operationDao;

    public CompteService(CompteDaoInterface compteDao, OperationDaoInterface operationDao) {
        this.compteDao = compteDao;
        this.operationDao = operationDao;
    }

    public CompteService() {
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

        compteDao.updateAmount(compteEntity1);
        compteDao.updateAmount(compteEntity2);

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
        compteDao.updateAmount(compteEntity);
        operationDao.save(new WithdrawalEntity(compteEntity));
    }

    @Override
    public List<OperationService> getAllOperation() {
        return null;
    }


}
