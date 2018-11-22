package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Doctor;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartments();

    Department getDepartment(Long id);

    List<Doctor> findDoctors(Department department);

    List<Receptionist> findReceptionists(Department department);

    List<Examination> findExaminations(Department department);
}
