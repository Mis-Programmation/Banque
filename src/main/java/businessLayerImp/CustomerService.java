package businessLayerImp;

import businessLayerInterface.CustomerServiceInterface;
import entity.CustomerEntity;
import daoInterface.CustomerDaoInterface;

public class CustomerService implements CustomerServiceInterface {

    private CustomerDaoInterface customerDao;

    public void setCustomerDao(CustomerDaoInterface customerDao)
    {
        this.customerDao = customerDao;
    }

    public CustomerEntity findCustomerWithAllCompte() {
        return null;
    }


}
