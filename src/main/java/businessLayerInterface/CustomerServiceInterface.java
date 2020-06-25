package businessLayerInterface;

import entity.CompteEntity;
import entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public interface CustomerServiceInterface {

    public void createCompte(CustomerEntity customerEntity, CompteEntity compteEntity) throws SQLException;
    public CustomerEntity save(CustomerEntity customerEntity) throws SQLException;
    public CustomerEntity findByCin(String value) throws SQLException;
    public List<CustomerEntity>  findAll() throws SQLException;
    public CustomerEntity findCustomerWithCompte(String numro_piece) throws SQLException;

}
