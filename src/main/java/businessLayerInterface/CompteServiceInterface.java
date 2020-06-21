package businessLayerInterface;

import businessLayerImp.OperationService;
import entity.CompteEntity;
import exception.AmountInsufficient;
import exception.NotFoundEntityException;

import java.sql.SQLException;
import java.util.List;

public interface CompteServiceInterface {

    /**
     *permet de faire des virement
     * @param amount
     */
    public void transfer(double amount,String compteNumber1,String compteNumber2) throws SQLException, NotFoundEntityException, AmountInsufficient;

    /**
     * retrait
     * @param amount
     */
    public void withdraw(double amount,String compteNumber) throws SQLException, NotFoundEntityException, AmountInsufficient;

    /**
     * liste des operation
     * @return
     */
    public List<OperationService> getAllOperation();

    /**
     *
     * @param amount
     * @param compteNumber
     * @throws SQLException
     * @throws NotFoundEntityException
     */
    public void payment(double amount,String compteNumber) throws SQLException, NotFoundEntityException;
}
