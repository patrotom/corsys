package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

/**
 * Represents doctor's role within the system.
 * Entity is connected to the User entity through its foreign key.
 * Doctor also carries foreign key to the department he/she is working at.
 *
 * @author fabosamu
 */
@Entity
public class Doctor {

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    /**
     * Generic user entity
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;

    /**
     * department of the particular doctor
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departmentId")
    private Department department;


    public Integer getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId
     */
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public User getUser() {
        return user;
    }

    /**
     *
     * @param userId
     */
    public void setUser(User userId) {
        this.user = userId;
    }

    public Department getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
}