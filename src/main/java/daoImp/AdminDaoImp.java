package daoImp;

import daoInterface.AdminDaoInterface;
import entity.AdminEntity;
import helpers.DatabaseHelper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("AdminDaoImp")

public class AdminDaoImp implements AdminDaoInterface {

    /**
     * Permet de recuperer un utilisateur
     */
    @Override
    public AdminEntity getUser(String pseudo) throws SQLException {
        String sql = "SELECT * FROM users WHERE pseudo = ?";
        AdminEntity adminEntity = null;
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,pseudo);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            adminEntity = new AdminEntity(resultSet.getString("pseudo"),resultSet.getString("password"));
            adminEntity.setIsConnected(resultSet.getInt("isConnected"));
        }
        resultSet.close();
        preparedStatement.close();
        return adminEntity;
    }

    /**
     * Permet d'ajouter un utilisateur
     */
    @Override
    public void save(AdminEntity adminEntity) throws SQLException {
        String sql = "INSERT INTO users SET pseudo = ?,password = ?";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,adminEntity.getPseudo());
        preparedStatement.setString(2,adminEntity.getPassword());
        preparedStatement.executeUpdate();
    }

}
