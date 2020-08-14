package entity;

import java.sql.Date;

/**
 * Virement
 */

public class TransferEntity extends OperationEntity {

    public TransferEntity(int id, Double montant, Date createAt) {
        super(id, montant, createAt);
    }

    public TransferEntity(Double montant) {
        super(montant);
    }

    @Override
    public String getoperationType() {
        return "Virement";
    }

    public TransferEntity(CompteEntity compteEntity) {
        super(compteEntity.getSolde());
        this.setCompte(compteEntity);
    }

    @Override
    public String toString() {
        return "------------ Virement ----------------- \n"+ super.toString();

    }
}
