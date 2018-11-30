package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

/**
 * Entity role holds names of roles and their ids.
 * Each user is strictly defined by its role.
 * Role specifies user''s permissions and capabilities within the system.
 *
 * @author fabosamu
 */
@Entity
public class Role {

    /**
     * Predefined role names, used throughout the application. Other can be added.
     */
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_DOCTOR = "DOCTOR";
    public static final String ROLE_PATIENT = "PATIENT";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    /**
     * Predefined name for the role.
     */
    @Column(length = 50, nullable = false, unique = true)
    private String name;


    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}