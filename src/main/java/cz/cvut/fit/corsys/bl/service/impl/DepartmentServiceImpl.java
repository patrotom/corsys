package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.DepartmentService;
import cz.cvut.fit.corsys.dl.dao.DepartmentDao;
import cz.cvut.fit.corsys.dl.entity.Department;
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
    public Department getDeparment(Integer id) {
        return this.departmentDao.getOne(id);
    }
}
