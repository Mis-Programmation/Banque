package daoInterface;

import entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDaoInterface {
    /**
     * permet de ajouter un client
     * @param customerEntity
     * @throws SQLException
     */
    public void save(CustomerEntity customerEntity) throws SQLException;
    public CustomerEntity findByCin(String numero_piece) throws SQLException;
    public List<CustomerEntity>  findAll() throws SQLException;
    public CustomerEntity findCustomerWithCompte(String numro_piece) throws SQLException ;
}
