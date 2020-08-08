package daoImp;

import daoInterface.OperationDaoInterface;
import entity.*;
import helpers.DatabaseHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository("OperationDaoImp")
public class OperationDaoImp implements OperationDaoInterface {

    /**
     * permet de metre ajour le solde
     */
    @Override
    public void save(OperationEntity operationEntity) throws SQLException {

        int type = 0;
        if(operationEntity instanceof PaymentEntity){
            type = 1;
        }else if(operationEntity instanceof TransferEntity){
            type = 2;
        }else if(operationEntity instanceof WithdrawalEntity) type = 3;
        else return;

        String sql = "INSERT INTO operation SET montant = ?,type = ?, compte_id = ?";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setDouble(1,operationEntity.getMontant());
        preparedStatement.setInt(2,type);
        preparedStatement.setInt(3,operationEntity.getCompte().getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


}
