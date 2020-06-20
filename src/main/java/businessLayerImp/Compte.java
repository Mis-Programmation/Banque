package businessLayerImp;

import businessLayerInterface.CompteBLInterface;
import entity.CompteEntity;
import daoInterface.CompteDaoInterface;
import entity.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Compte implements CompteBLInterface {

    private CompteDaoInterface compteDao;

    public void setCompteDao(CompteDaoInterface compteDao) {
        this.compteDao = compteDao;
    }

    @Override
    public boolean save(CompteEntity compteEntity) {
        try {
            compteDao.save(compteEntity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    @Override
    public List<CompteEntity> findAll() {
        List<CompteEntity> compteEntity = new ArrayList<>();
        try {
            ResultSet resultSet = compteDao.findAll();
            while (resultSet.next()){
                compteEntity.add(hydrate(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return compteEntity;
    }

    @Override
    public CompteEntity findByNumber(String number)  {
        CompteEntity compteEntity = null;
        ResultSet resultSet = null;
        try {
            resultSet = compteDao.findByNumber(number);
            while (resultSet.next()){
                compteEntity = hydrate(resultSet);
                compteEntity.setCreateAt(resultSet.getDate("date_creation"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return compteEntity;
    }


    @Override
    public void deposit(double montant) {

    }

    @Override
    public void withdraw(double montant) {

    }

    @Override
    public void transfer(double montant, CompteEntity compteEntity) {

    }

    @Override
    public double getSolde() {
        return 0;
    }

    @Override
    public List<Operation> getAllOperation() {
        return null;
    }

    private CompteEntity hydrate(ResultSet resultSet) throws SQLException {
        CustomerEntity customerEntity = new CustomerEntity(
                resultSet.getString("numro_piece"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getDate("date_naissance"),
                resultSet.getString("adress"),
                resultSet.getString("email")
        );

       return new CompteEntity(
                resultSet.getString("numero"),
                resultSet.getDouble("solde")
               ,customerEntity
       );
    }
}
