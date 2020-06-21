package businessLayerImp;

import businessLayerInterface.CustomerServiceInterface;
import daoInterface.CompteDaoInterface;
import entity.CompteEntity;
import entity.CustomerEntity;
import daoInterface.CustomerDaoInterface;

import java.sql.SQLException;

public class CustomerService implements CustomerServiceInterface {

    private CustomerDaoInterface customerDao;
    private CompteDaoInterface compteDao;


    public CustomerService(CustomerDaoInterface customerDao, CompteDaoInterface compteDao) {
        this.customerDao = customerDao;
        this.compteDao = compteDao;
    }

    public CustomerService() {
    }

    public CustomerEntity findCustomerWithAllCompte() {
        return null;
    }

    public void createCompte(CustomerEntity customerEntity, CompteEntity compteEntity) throws SQLException {
            compteEntity.setCustomer(customerEntity);
            compteDao.save(compteEntity);
    }

}
