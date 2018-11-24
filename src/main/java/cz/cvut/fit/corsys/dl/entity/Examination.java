package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;

@Entity
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer examinationId;

    @Column(length = 100, nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer length;

    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentId")
    private Department department;

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}