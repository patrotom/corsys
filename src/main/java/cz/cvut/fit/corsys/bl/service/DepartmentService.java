package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartments();

    Department getDeparment(Integer id);
}
