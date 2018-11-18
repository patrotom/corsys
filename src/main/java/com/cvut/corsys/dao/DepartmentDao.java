package com.cvut.corsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvut.corsys.entity.Department;

public interface DepartmentDao extends JpaRepository<Department, Long> {

}
