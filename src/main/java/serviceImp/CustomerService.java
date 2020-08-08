package serviceImp;

import exception.FoundEntityException;
import org.springframework.stereotype.Service;
import serviceInterface.CustomerServiceInterface;
import daoInterface.CompteDaoInterface;
import entity.CompteEntity;
import entity.CustomerEntity;
import daoInterface.CustomerDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLException;
import java.util.List;

@Service("CustomerService")
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


    public void createCompte(CustomerEntity customerEntity, CompteEntity compteEntity) throws SQLException {
            compteEntity.setCustomer(customerEntity);
            compteDao.save(compteEntity);
    }

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) throws SQLException, FoundEntityException {
        if(null != customerDao.findOne("numro_piece",customerEntity.getNumberIdentification())){
            throw new FoundEntityException("ce client existe deja");
        }
       return customerDao.save(customerEntity);
    }

    @Override
    public CustomerEntity findByCin(String value) throws SQLException {
        return customerDao.findOne("numro_piece",value);
    }

    @Override
    public List<CustomerEntity> findAll() throws SQLException {
        return customerDao.findAll();
    }

    @Override
    public CustomerEntity findCustomerWithCompte(String numro_piece) throws SQLException {
        return customerDao.findCustomerWithCompte(numro_piece);
    }

}
