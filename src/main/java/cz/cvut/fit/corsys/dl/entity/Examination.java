package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

/**
 * Information about the examination entity class - type, its length, department and optional description.
 * Examination is mandatory part of the reservation.
 * It is mandatory to fill in Examination type during the creation of a reservation.
 * Examinations are also connected to the departments so when a user is creating a reservation,
 * only valid types of examinations will be offered by the system to choose.
 *
 * @author fabosamu
 */
@Entity
public class Examination {

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer examinationId;

    @Column(length = 100, nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer length;

    @Column(nullable = true)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departmentId")
    private Department department;

    public Integer getExaminationId() {
        return examinationId;
    }

    /**
     * @param examinationId
     */
    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    /**
     * @param length
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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