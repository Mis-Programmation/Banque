package entity;

import java.util.Date;

// retait
public class WithdrawalEntity extends OperationEntity {

    public WithdrawalEntity(int id, Date createAt, Double montant, int type) {
        super(id, createAt, montant, type);
    }

}
