package entity;

import java.util.Date;

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

    public TransferEntity(CompteEntity compteEntity) {
        super(compteEntity.getSolde());
        this.setCompte(compteEntity);
    }

    @Override
    public String toString() {
        return "------------ Virement ----------------- \n"+ super.toString();

    }
}
