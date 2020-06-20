package daoInterface;

import entity.CompteEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CompteDaoInterface {
    /**
     * permet de enregister un compte
     * @param compteEntity
     * @throws SQLException
     */
    public void save(CompteEntity compteEntity) throws SQLException;
    public CompteEntity findCompteWitCustomer(String number) throws SQLException;
    public List<CompteEntity> findAll() throws SQLException;
}
