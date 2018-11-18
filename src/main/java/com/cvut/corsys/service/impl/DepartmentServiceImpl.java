package com.cvut.corsys.service.impl;

import com.cvut.corsys.dao.DepartmentDao;
import com.cvut.corsys.entity.Department;
import com.cvut.corsys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findAllDepartments() {
        return this.departmentDao.findAll();
    }

    @Override
    public Department getDeparment(Long id) {
        return this.departmentDao.getOne(id);
    }
}
