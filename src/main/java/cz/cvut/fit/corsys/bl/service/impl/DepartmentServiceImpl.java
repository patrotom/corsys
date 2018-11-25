package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.DepartmentService;
import cz.cvut.fit.corsys.dl.dao.DepartmentDao;
import cz.cvut.fit.corsys.dl.dao.DoctorDao;
import cz.cvut.fit.corsys.dl.dao.ExaminationDao;
import cz.cvut.fit.corsys.dl.dao.ReceptionistDao;
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

    @Autowired
    private ExaminationDao examinationDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private ReceptionistDao receptionistDao;

    @Override
    public List<Department> findAllDepartments() {
        return departmentDao.findAll();
    }

    @Override
    public Department getDepartment(Integer id) {
        return departmentDao.getOne(id);
    }

    @Override
    public Examination getExamination(Integer id) {
        return examinationDao.findExaminationByExaminationId(id);
    }

    @Override
    public List<Doctor> findDoctors(Department department) {
        return doctorDao.findDoctorsByDepartment(department);
    }

    @Override
    public List<Receptionist> findReceptionists(Department department) {
        return receptionistDao.findReceptionistsByDepartment(department);
    }

    @Override
    public List<Examination> findExaminations(Department department) {
        return examinationDao.findExaminationsByDepartment(department);
    }
}
