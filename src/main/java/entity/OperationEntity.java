package entity;

import java.time.LocalDate;
import java.util.Date;

public abstract class OperationEntity {

    protected int id;
    protected Date createAt;
    protected Double montant;
    protected int type;
    protected CompteEntity compte;

    public OperationEntity(int id, Date createAt, Double montant, int type) {
        this.id = id;
        this.createAt = createAt;
        this.montant = montant;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CompteEntity getCompte() {
        return compte;
    }

    public void setCompte(CompteEntity compte) {
        this.compte = compte;
    }
}
