package businessLayerImp;

import businessLayerInterface.CustomerServiceInterface;
import daoInterface.CompteDaoInterface;
import entity.CompteEntity;
import entity.CustomerEntity;
import daoInterface.CustomerDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component("CustomerService")
public class CustomerService implements CustomerServiceInterface {
    @Autowired
    private CustomerDaoInterface customerDao;
    @Autowired
    private CompteDaoInterface compteDao;

    public void setCustomerDao(CustomerDaoInterface customerDao) {
        this.customerDao = customerDao;
    }

    public void setCompteDao(CompteDaoInterface compteDao) {
        this.compteDao = compteDao;
    }

    public CustomerEntity findCustomerWithAllCompte() {

        return null;
    }

    public void createCompte(CustomerEntity customerEntity, CompteEntity compteEntity) throws SQLException {
            compteEntity.setCustomer(customerEntity);
            compteDao.save(compteEntity);
    }

}
