package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Examination;
import cz.cvut.fit.corsys.dl.entity.Receptionist;

import java.util.List;

public interface DepartmentService {

    /**
     * Finds all departments.
     *
     * @return list of all departments.
     */
    List<Department> findAllDepartments();

    /**
     * Returns department for the specified id.
     *
     * @param id the id for which to find department.
     * @return the corresponding department, null if such department does not exist.
     */
    Department getDepartment(Integer id);

    /**
     * Returns department for the specified name.
     *
     * @param name the id for which to find department.
     * @return the corresponding department, null if such department does not exist.
     */
    Department findDepartment(String name);

    /**
     * Returns examination for the specified id.
     *
     * @param id the id for which to find examination.
     * @return the corresponding examination, null if such examination does not exist.
     */
    Examination getExamination(Integer id);

    /**
     * Finds all doctors belonging to the specified department.
     *
     * @param department the department for which to find doctors.
     * @return list of doctors.
     */
    List<Doctor> findDoctors(Department department);

    /**
     * Finds all receptionists belonging to the specified department.
     *
     * @param department the department for which to find receptionists.
     * @return list of receptionists.
     */
    List<Receptionist> findReceptionists(Department department);

    /**
     * Finds all examinations which are performed on the specified department.
     *
     * @param department the department for which to find examinations.
     * @return list of examinations.
     */
    List<Examination> findExaminations(Department department);
}
