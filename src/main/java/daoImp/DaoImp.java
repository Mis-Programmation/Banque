package daoImp;

import daoInterface.DaoInterface;
import helpers.DatabaseHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoImp implements DaoInterface {

    @Override
    public ResultSet findAll(String table) throws SQLException {
        String sql = "SELECT * FROM "+table;
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
    /**
     * permet de recuperer une entite rechercher par son nom
     * @param key
     * @param value
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet findbyValue(String table, String key, String value) throws SQLException {
        String sql = "SELECT * FROM "+table+" WHERE "+key+ " = ?";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,value);
        return preparedStatement.executeQuery();
    }

}
