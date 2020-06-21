package daoInterface;

import entity.CompteEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompteDaoInterface {
    /**
     * permet de enregister un compte
     * @param compteEntity@throws SQLException
     */
    public void save(CompteEntity compteEntity) throws SQLException;
    public CompteEntity findCompteWithCustomerByNumber(String number) throws SQLException;
    public List<CompteEntity> findAll() throws SQLException;
    public void updateAmount(CompteEntity compteEntity) throws SQLException;
    public CompteEntity findAllOperationForOneCompteBynumber(String number) throws SQLException;
}
