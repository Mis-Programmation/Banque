package businessLayerInterface;

import entity.CompteEntity;
import entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBLInterface {
    /**
     * permet d'ajouter un client
     * @param customerEntity
     * @return
     */
    public boolean save(CustomerEntity customerEntity);

    public CustomerEntity findCustomerWithAllCompte();

    /**
     * permet de recuperer tout les clients
     * @return
     */

    public List<CustomerEntity> findAll();

    /**
     * permet de recuperer le nom
     * @return
     */
    public CustomerEntity findByName(String name) throws SQLException;

}
