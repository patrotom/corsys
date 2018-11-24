package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {

    Doctor findDoctorByDoctorId(Integer id);

    Doctor findDoctorByUser(User user);

    List<Doctor> findDoctorsByDepartment(Department department);

}
