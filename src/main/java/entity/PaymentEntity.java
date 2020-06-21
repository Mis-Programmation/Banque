package entity;

import java.util.Date;

public class PaymentEntity extends OperationEntity {
    public PaymentEntity(int id, Double montant, Date createAt) {
        super(id, montant, createAt);
    }

    public PaymentEntity(CompteEntity compteEntity) {
        super(compteEntity.getSolde());
        this.setCompte(compteEntity);
    }

    @Override
    public String toString() {

        return super.toString() + "\n------------ Versement -----------------";
    }
}
