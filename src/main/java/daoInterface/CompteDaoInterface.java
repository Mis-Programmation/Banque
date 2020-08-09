package daoInterface;

import entity.CompteEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompteDaoInterface {
    /**
     * permet de enregister un compte
     * @param compteEntity@throws SQLException
     */
    public CompteEntity save(CompteEntity compteEntity) throws SQLException;
    public CompteEntity findCompteWithCustomerByNumber(String number) throws SQLException;
    public CompteEntity findOne(String key,String value) throws SQLException;
    public List<CompteEntity> findCompteWithCustomer() throws SQLException;
    public List<CompteEntity> findAll() throws SQLException;
    public void updateAmount(CompteEntity compteEntity) throws SQLException;
    public CompteEntity findCompteWithAllOperationBynumber(String number) throws SQLException;
}
