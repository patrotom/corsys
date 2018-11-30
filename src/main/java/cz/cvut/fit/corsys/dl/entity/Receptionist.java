package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

/**
 * Represents receptionist's role within the system.
 * Entity is connected to the User entity through its foreign key.
 * Receptionist also carries entity of the department he/she is working at.
 *
 * @author fabosamu
 */
@Entity
public class Receptionist {

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    public User user;
    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receptionistId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departmentId")
    private Department department;


    public Integer getReceptionistId() {
        return receptionistId;
    }

    /**
     * @param receptionistId
     */
    public void setReceptionistId(Integer receptionistId) {
        this.receptionistId = receptionistId;
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

    public Department getDepartment() {
        return department;
    }

    /**
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
}