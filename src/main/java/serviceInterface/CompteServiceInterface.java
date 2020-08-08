package serviceInterface;

import serviceImp.OperationService;
import entity.CompteEntity;
import exception.AmountInsufficient;
import exception.NotFoundEntityException;

import java.sql.SQLException;
import java.util.List;

public interface CompteServiceInterface {

    /**
     *permet de faire des virement
     */
    public void transfer(double amount,String compteNumber1,String compteNumber2) throws SQLException, NotFoundEntityException, AmountInsufficient;
    public void withdraw(double amount,String compteNumber) throws SQLException, NotFoundEntityException, AmountInsufficient;
    public List<OperationService> getAllOperation();
    public void payment(double amount,String compteNumber) throws SQLException, NotFoundEntityException;
    public CompteEntity save(CompteEntity compteEntity) throws SQLException;
    public CompteEntity findCompteWithCustomerByNumber(String number) throws SQLException;
    public List<CompteEntity> findAll() throws SQLException;
    public CompteEntity findCompteWithAllOperationBynumber(String number) throws SQLException;

}
