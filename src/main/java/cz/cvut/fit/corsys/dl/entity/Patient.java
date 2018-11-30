package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

/**
 * Represents patient''s role within the system.
 * Entity class is connected to the User table through its foreign key.
 *
 * @author fabosamu
 */
@Entity
public class Patient {

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @Column(length = 50)
    private String birthNumber;

    @Column(length = 50)
    private String insurance;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;


    public Integer getPatientId() {
        return patientId;
    }

    /**
     * @param patientId
     */
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    /**
     * @param birthNumber
     */
    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public String getInsurance() {
        return insurance;
    }

    /**
     * @param insurance
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}