package serviceInterface;

import entity.CompteEntity;
import entity.CustomerEntity;
import exception.FoundEntityException;
import exception.NotFoundEntityException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerServiceInterface {

    public void createCompte(CustomerEntity customerEntity, CompteEntity compteEntity) throws SQLException;
    public CustomerEntity save(CustomerEntity customerEntity) throws SQLException, FoundEntityException;
    public CustomerEntity findByCin(String value) throws SQLException;
    public List<CustomerEntity>  findAll() throws SQLException;

    /**
     * permet de recuperer l'utilisateur et tout ces compte
     * @param numro_piece
     * @return
     * @throws SQLException
     */
    public CustomerEntity findCustomerWithCompte(String numro_piece) throws SQLException, NotFoundEntityException;

}
