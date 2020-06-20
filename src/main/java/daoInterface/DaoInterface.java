package daoInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public  abstract interface DaoInterface {

    /**
     * permet de recuperer toutes les entites de la table
     * @return
     * @throws SQLException
     * @param table
     */

    public ResultSet findAll(String table) throws SQLException;

    /**
     * permet de de rechercher une entite par une valeur definie
     * @param key
     * @param value
     * @return
     * @throws SQLException
     */
    public ResultSet findbyValue(String table,String key,String value) throws SQLException;

}
