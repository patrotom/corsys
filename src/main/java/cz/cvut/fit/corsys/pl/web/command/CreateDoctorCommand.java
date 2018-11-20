package cz.cvut.fit.corsys.pl.web.command;

import javax.validation.constraints.NotNull;

public class CreateDoctorCommand extends AbstractCreateUserCommand {

    @NotNull
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
