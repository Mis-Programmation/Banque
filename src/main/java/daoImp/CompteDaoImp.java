package daoImp;

import daoInterface.CompteDaoInterface;
import entity.CompteEntity;
import entity.CustomerEntity;
import helpers.DatabaseHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteDaoImp extends DaoImp implements CompteDaoInterface {

    /**
     * permet de recuperer toute les enregistrement
     * @return
     * @throws SQLException
     */
    @Override
    public List<CompteEntity> findAll() throws SQLException {

        List<CompteEntity> compteEntity = new ArrayList<>();

        String sql = "SELECT * FROM compte,client" +
                "WHERE compte.client_id = client.id";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                compteEntity.add(hydrate(resultSet));
            }
            return compteEntity;
    }

    /**
     * permet de sauvegarder
     * @param compteEntity
     * @throws SQLException
     */
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
    public CompteEntity findCompteWitCustomer(String number) throws SQLException {
        CompteEntity compteEntity = null;

        String sql = "SELECT * FROM compte,client" + "WHERE compte.client_id = client.id" +
                "AND compte.numero = ?"
        ;

        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,number);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            compteEntity = hydrate(resultSet);
            compteEntity.setCustomer( new CustomerEntity(
                    resultSet.getString("numro_piece"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getDate("date_naissance"),
                    resultSet.getString("adress"),
                    resultSet.getString("email")
                )
            );
        }
        return compteEntity;
    }


    /**
     * permet de hydrater les object
     * @param resultSet
     * @return
     * @throws SQLException
     */
    @Override
    protected CompteEntity hydrate(ResultSet resultSet) throws SQLException {
        return new CompteEntity(
                resultSet.getString("numero"),
                resultSet.getDouble("solde")
                ,resultSet.getDate("date_creation")
        );
    }

}
