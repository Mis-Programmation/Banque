package daoInterface;

import entity.CompteEntity;
import entity.OperationEntity;

import java.sql.SQLException;
public interface OperationDaoInterface {
    /**
     * permet de sauvegarder les operation
     * @param operationEntity
     * @throws SQLException
     */
    public void save(OperationEntity operationEntity) throws SQLException;

}
