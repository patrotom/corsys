package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {

    /**
     * @param id
     * @return Doctor
     */
    Doctor findDoctorByDoctorId(Integer id);

    /**
     * Given user entity finds Doctor entity
     *
     * @param user
     * @return Doctor
     */
    Doctor findDoctorByUser(User user);

    /**
     * Given department, finds Doctors assigned to the particular department.
     *
     * @param department
     * @return List of Doctors
     */
    List<Doctor> findDoctorsByDepartment(Department department);

}
