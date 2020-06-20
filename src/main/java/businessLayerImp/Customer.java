package businessLayerImp;

import businessLayerInterface.CustomerBLInterface;
import entity.CompteEntity;
import entity.CustomerEntity;
import daoInterface.CustomerDaoInterface;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer implements CustomerBLInterface {

    private CustomerDaoInterface customerDao;

    public void setCustomerDao(CustomerDaoInterface customerDao)
    {
        this.customerDao = customerDao;
    }

    @Override
    public boolean save(CustomerEntity customerEntity) {
        try {
            customerDao.save(customerEntity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public CustomerEntity findCustomerWithAllCompte() {
        return null;
    }


    @Override
    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customerEntities = new ArrayList<>();
        CustomerEntity customerEntity;
        try {
            ResultSet resultSet = customerDao.findAll("client");
            while(resultSet.next()){
                customerEntity = hydrate(resultSet);
                customerEntities.add(customerEntity);
            }

            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerEntities;
    }

    /**
     * permet de recuperer un enregisterment par sont nom
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public CustomerEntity findByName(String name) {
        CustomerEntity customerEntity = null;
        ResultSet resultSet = null;
        try {
            resultSet = customerDao.findbyValue("client","nom",name);
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
     * @return
     * @throws SQLException
     */
    private CustomerEntity hydrate(ResultSet resultSet) throws SQLException {
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
