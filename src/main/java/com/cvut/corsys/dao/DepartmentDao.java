package com.cvut.corsys.dao;

import com.cvut.corsys.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Long> {

}
