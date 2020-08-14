package entity;

import java.sql.Date;

// retait
public class WithdrawalEntity extends OperationEntity {

    public WithdrawalEntity() {
    }

    @Override
    public String getoperationType() {
        return "Retait";
    }

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
