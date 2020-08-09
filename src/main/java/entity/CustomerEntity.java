package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Client
 */
public class CustomerEntity {

    private int id;
    private String numberIdentification;
    private String firstName;
    private String lastName;
    private Date dateOfTheBirth;
    private String address;
    private String email;
    private List<CompteEntity> compte = new ArrayList<>();

    public CustomerEntity() {

    }

    /**
     *
     * @param numberIdentification
     * @param firstName
     * @param lastName
     * @param dateOfTheBirth
     * @param address
     * @param email
     */
    public CustomerEntity(String numberIdentification, String firstName, String lastName,
                          Date dateOfTheBirth, String address, String email) {
        this.numberIdentification = numberIdentification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfTheBirth = dateOfTheBirth;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberIdentification() {
        return numberIdentification;
    }

    public void setNumberIdentification(String numberIdentification) {
        this.numberIdentification = numberIdentification;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfTheBirth() {
        return dateOfTheBirth;
    }

    public void setDateOfTheBirth(Date dateOfTheBirth) {
        this.dateOfTheBirth = dateOfTheBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CompteEntity> getCompte() {
        return compte;
    }

    public void setCompte(List<CompteEntity> compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return this.getLastName();
    }
}
