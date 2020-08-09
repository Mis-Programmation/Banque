package daoImp;

import entity.CompteEntity;
import entity.CustomerEntity;
import daoInterface.CustomerDaoInterface;
import helpers.DatabaseHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation du class access au donnee client
 */
@Repository("CustomerDaoImp")
public class CustomerDaoImp extends DaoImp implements CustomerDaoInterface {

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) throws SQLException {

        String sql = "INSERT INTO client SET nom = ?," +
                "prenom = ?,email = ?,numro_piece = ?,adress = ?," +
                "date_naissance = NOW() ";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);

        preparedStatement.setString(1,customerEntity.getFirstName());
        preparedStatement.setString(2,customerEntity.getLastName());
        preparedStatement.setString(3,customerEntity.getEmail());
        preparedStatement.setString(4,customerEntity.getNumberIdentification());
        preparedStatement.setString(5,customerEntity.getAddress());

        preparedStatement.execute();
        preparedStatement.close();
        DatabaseHelper.closeConnection();

        return customerEntity;
    }

    @Override
     public List<CustomerEntity> findAll() throws SQLException {
        List<CustomerEntity> customerEntities = new ArrayList<>();
        CustomerEntity customerEntity;
        String sql = "SELECT * FROM client";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        ResultSet resultSet =  preparedStatement.executeQuery();

            while(resultSet.next()){
                customerEntity = hydrate(resultSet);
                customerEntity.setId(resultSet.getInt("id"));
                customerEntities.add(customerEntity);
            }
            resultSet.close();

        return customerEntities;
    }

    @Override
    public CustomerEntity findCustomerWithCompte(String numro_piece) throws SQLException {
        CustomerEntity customerEntity;
        List<CompteEntity> compteEntities = new ArrayList<>();
        customerEntity = findOne("numro_piece",numro_piece);
        if(customerEntity == null){
            return null;
        }
        ResultSet resultSet = findbyValue("compte","compte.client_id",String.valueOf(customerEntity.getId()));
        while(resultSet.next()) {
            CompteEntity compteEntity =
                    new CompteEntity(resultSet.getString("numero"),resultSet.getDouble("solde"),
                            resultSet.getDate("date_creation")
                    );
            compteEntities.add(compteEntity);
        }
        customerEntity.setCompte(compteEntities);
        return customerEntity;
    }

    /**
     *  permet de recuperer un enregisterment par sont nom
     * @param key
     * @param value
     * @return CustomerEntity | null
     */
    @Override
    public CustomerEntity findOne(String key,String value) {
        CustomerEntity customerEntity = null;
        try {
            ResultSet resultSet = findbyValue("client",key,value);
            while( resultSet.next() ){

                customerEntity = hydrate(resultSet);
                customerEntity.setId(resultSet.getInt(1));
                break;
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerEntity;
    }

    /**
     * permet de hydrater l'object
     * @param resultSet
     * @return customerEntity
     * @throws SQLException
     */
    @Override
    protected CustomerEntity hydrate(ResultSet resultSet) throws SQLException {
        return new CustomerEntity(
                resultSet.getString("numro_piece"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getDate("date_naissance"),
                resultSet.getString("adress"),
                resultSet.getString("email")
        );
    }

}
