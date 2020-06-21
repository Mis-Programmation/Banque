package entity;

import java.time.LocalDate;
import java.util.Date;

public abstract class OperationEntity {

    protected int id;
    protected Date createAt;
    protected Double montant;
    protected CompteEntity compte;

    public OperationEntity(int id, Double montant, Date createAt) {
        this.id = id;
        this.createAt = createAt;
        this.montant = montant;
    }

    public OperationEntity( Double montant) {
        this.montant = montant;
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

    public CompteEntity getCompte() {
        return compte;
    }

    public void setCompte(CompteEntity compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return
            "\n createAt " + createAt +
            "\n montant  " + montant ;
    }
}
