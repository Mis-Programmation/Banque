package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Compte
 */
public class CompteEntity {

    private int id;
    private String numero;
    private double solde;
    private Date createAt;
    private CustomerEntity customer;
    private List<OperationEntity> operation = new ArrayList<>();

    public CompteEntity() {

    }

    /**
     *
     * @param numero
     * @param solde
     * @param customer
     */
    public CompteEntity( String numero, double solde, CustomerEntity customer) {
        this.numero = numero;
        this.solde = solde;
        this.customer = customer;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public BigDecimal getBigDecimal()
    {
       return new BigDecimal(solde);
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<OperationEntity> getOperation() {
        return operation;
    }

    public void setOperation(List<OperationEntity> operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return
                "\n numero    " + numero +
                "\n solde     " + getBigDecimal() +
                "\n createAt  " + createAt +
                        "\n ---------- Proprietaire ----------" + customer;
    }
}
