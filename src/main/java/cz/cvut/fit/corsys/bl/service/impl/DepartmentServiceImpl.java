package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.DepartmentService;
import cz.cvut.fit.corsys.dl.dao.DepartmentDao;
import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Examination;
import cz.cvut.fit.corsys.dl.entity.Receptionist;
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
    public Department getDepartment(Integer id) {
        return this.departmentDao.getOne(id);
    }

    @Override
    public List<Doctor> findDoctors(Department department) {
        //TODO implement
        return null;
    }

    @Override
    public List<Receptionist> findReceptionists(Department department) {
        //TODO implement
        return null;
    }

    @Override
    public List<Examination> findExaminations(Department department) {
        //TODO implement
        return null;
    }
}
