package daoImp;

import entity.CustomerEntity;
import daoInterface.CustomerDaoInterface;
import helpers.DatabaseHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Implementation du class access au donnee client
 */
public class CustomerDaoImp extends DaoImp implements CustomerDaoInterface {

    @Override
    public void save(CustomerEntity customerEntity) throws SQLException {

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
    }

}
