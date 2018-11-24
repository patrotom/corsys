package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Patient;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Integer> {

    Patient findPatientByPatientId(Integer id);

    Patient findPatientByUser(User user);

}
