package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

/**
 * Contains information about patient's address.
 *
 * @author fabosamu
 */
@Entity
public class Address {

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(length = 100, nullable = false)
    private String street;

    @Column(length = 50, nullable = false)
    private String number;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String zipCode;


    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    /**
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}