package daoImp;

import daoInterface.CompteDaoInterface;
import entity.*;
import helpers.DatabaseHelper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("CompteDaoImp")

public class CompteDaoImp extends DaoImp implements CompteDaoInterface {

    /**
     * permet de recuperer toute les enregistrement
     * @return
     * @throws SQLException
     */
    @Override
    public List<CompteEntity> findAll() throws SQLException {

        List<CompteEntity> compteEntity = new ArrayList<>();

        String sql = "SELECT * FROM compte,client " +
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

    /**
     * permet de rechercher un compte avec sont proprios
     * @param number
     * @return
     * @throws SQLException
     */
    @Override
    public CompteEntity findCompteWithCustomerByNumber(String number) throws SQLException {
        CompteEntity compteEntity = null;
        String sql = "SELECT c.solde,c.numero,c.date_creation, c.id as compte_id , cl.* FROM compte as c,client as cl WHERE c.client_id = cl.id AND c.numero = ?"
        ;

        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,number);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            compteEntity = hydrate(resultSet);
            compteEntity.setId(resultSet.getInt("compte_id"));
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
     * @param resultSet @return @throws SQLException
     */
    @Override
    protected CompteEntity hydrate(ResultSet resultSet) throws SQLException {
        return new CompteEntity(
                resultSet.getString("numero"),
                resultSet.getDouble("solde")
                ,resultSet.getDate("date_creation")
        );
    }

    /**
     * permet de metre ajour le solde
     * @param compteEntity@throws SQLException
     */
    @Override
    public void updateAmount(CompteEntity compteEntity) throws SQLException {

        String sql = "UPDATE compte SET solde = ? WHERE numero = ?";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setDouble(1,compteEntity.getSolde());
        preparedStatement.setString(2,compteEntity.getNumero());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    /**
     * permet de rechercher toute les operation d'un compte
     * @param number
     * @return
     * @throws SQLException
     */
    @Override
    public CompteEntity findAllOperationForOneCompteBynumber(String number) throws SQLException
    {
        CompteEntity compteEntity = null;
        List<OperationEntity> operationEntities = new ArrayList<>();
        OperationEntity operation = null;

        ResultSet resultSet =  findbyValue("compte","numero",number);
        while (resultSet.next()){
         compteEntity =  hydrate(resultSet);
         compteEntity.setId(resultSet.getInt(1));
        }

        if(compteEntity == null){
            return null;
        }

         resultSet =  findbyValue("operation","compte_id",String.valueOf(compteEntity.getId()));
        while (resultSet.next()){
            if(resultSet.getInt("type") == 1){
                operation = new PaymentEntity(
                        resultSet.getInt(1),resultSet.getDouble("montant"),
                        resultSet.getDate("date_operation")
                );
            }

            if(resultSet.getInt("type") == 2){
                operation = new TransferEntity(
                        resultSet.getInt(1),resultSet.getDouble("montant"),
                        resultSet.getDate("date_operation")
                );
            }

            if(resultSet.getInt("type") == 3){
                 operation = new WithdrawalEntity(
                        resultSet.getInt(1),resultSet.getDouble("montant"),
                        resultSet.getDate("date_operation")
                );
            }
            if(operation != null){
                operationEntities.add(operation);
            }
        }
        compteEntity.setOperation(operationEntities);

        return compteEntity;
    }

}
