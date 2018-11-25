package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

@Entity
public class Receptionist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer receptionistId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    public User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departmentId")
    private Department department;


    public Integer getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(Integer receptionistId) {
        this.receptionistId = receptionistId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}