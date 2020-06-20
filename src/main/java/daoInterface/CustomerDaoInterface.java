package daoInterface;

import entity.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDaoInterface extends DaoInterface {
    /**
     * permet de ajouter un client
     * @param customerEntity
     * @throws SQLException
     */
    public void save(CustomerEntity customerEntity) throws SQLException;

}
