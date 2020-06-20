package daoImp;

import daoInterface.CompteDaoInterface;
import entity.CompteEntity;
import helpers.DatabaseHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteDaoImp extends DaoImp implements CompteDaoInterface {

    @Override
    public ResultSet findAll() throws SQLException {

        String sql = "SELECT * FROM compte,client" +
                " WHERE compte.client_id = client.id";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    @Override
    public void save(CompteEntity compteEntity) throws SQLException {
        String sql = "INSERT INTO compte SET solde = ?," +
                "numero = ?,client_id = ?";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setDouble(1,compteEntity.getSolde());
        preparedStatement.setString(2,compteEntity.getNumero());
        preparedStatement.setDouble(3,compteEntity.getCustomer().getId());
        preparedStatement.execute();
        preparedStatement.close();
        DatabaseHelper.closeConnection();
    }

    @Override
    public ResultSet findByNumber(String number) throws SQLException {

        String sql = "SELECT * FROM compte,client" +
                " WHERE compte.client_id = client.id" +
                " AND compte.numero = ?";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,number);
        return preparedStatement.executeQuery();
    }

}
