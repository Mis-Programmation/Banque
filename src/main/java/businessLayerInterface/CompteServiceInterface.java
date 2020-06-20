package businessLayerInterface;

import businessLayerImp.OperationService;
import entity.CompteEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompteServiceInterface {

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
    public List<OperationService> getAllOperation();
}
