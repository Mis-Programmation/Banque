package daoInterface;

import entity.CompteEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CompteDaoInterface extends DaoInterface {
    /**
     * permet de enregister un compte
     * @param compteEntity
     * @throws SQLException
     *
     */
    public void save(CompteEntity compteEntity) throws SQLException;
    public ResultSet findByNumber(String number) throws SQLException;
    public ResultSet findAll() throws SQLException;
}
