package com.cvut.corsys.service;

import java.util.List;

import com.cvut.corsys.entity.Department;

public interface DepartmentService {

	List<Department> findAllDepartments();

	Department getDeparment(Long id);
}
