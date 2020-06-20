package entity;

import java.util.Date;

/**
 * Versement
 */

public class DepositEntity extends OperationEntity {

    public DepositEntity(int id, Date createAt, Double montant, int type) {
        super(id, createAt, montant, type);
    }
}
