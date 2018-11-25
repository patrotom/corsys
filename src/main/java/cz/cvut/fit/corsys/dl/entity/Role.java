package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

@Entity
public class Role {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_DOCTOR = "DOCTOR";
    public static final String ROLE_PATIENT = "PATIENT";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @Column(length = 50, nullable = false, unique = true)
    private String name;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}