package cz.cvut.fit.corsys.pl.web.command;

import javax.validation.constraints.NotNull;

public class CreateDoctorCommand extends AbstractCreateUserCommand {

    @NotNull
    private Integer departmentId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
