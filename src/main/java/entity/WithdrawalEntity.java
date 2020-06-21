package entity;

import java.util.Date;

// retait
public class WithdrawalEntity extends OperationEntity {

    public WithdrawalEntity(int id, Double montant, Date createAt) {
        super(id, montant,createAt);
    }

    public WithdrawalEntity(CompteEntity compteEntity) {
        super(compteEntity.getSolde());
        this.setCompte(compteEntity);
    }

    @Override
    public String toString() {
        return  "------------- Retait -----------------\n" + super.toString();
    }
}
