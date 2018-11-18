package com.cvut.corsys.service;

import com.cvut.corsys.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartments();

    Department getDeparment(Long id);
}
