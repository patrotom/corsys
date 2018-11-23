package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {

}
