package com.cvut.corsys.web.command;

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
