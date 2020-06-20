package businessLayerInterface;

import businessLayerImp.Operation;
import entity.CompteEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompteBLInterface  {

    /**
     * permet d'ajouter un compte
     * @param compteEntity
     * @return
     */
     public boolean save(CompteEntity compteEntity) throws SQLException;

    /**
     * permet de recuperer touts les comptes
     * @return
     */
    public List<CompteEntity> findAll() throws SQLException;

    /**
     *  permet de recuperer un compte
     * @param number
     * @return
     * @throws SQLException
     */
    public CompteEntity findByNumber(String number) throws SQLException;


    /**
     * versement
     * @param montant
     */
    public void deposit(double montant);

    /**
     * retrait
     * @param montant
     */
    public void withdraw(double montant);

    /**
     * Virement bancaire
     * @param montant
     * @param compteEntity
     */
    public void transfer(double montant, CompteEntity compteEntity);

    /**
     * Consultation du solde
     * @return
     */
    public double getSolde();

    /**
     * liste des operation
     * @return
     */
    public List<Operation> getAllOperation();
}
