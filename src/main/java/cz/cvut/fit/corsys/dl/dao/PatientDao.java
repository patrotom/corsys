package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Patient;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface defines data layer interface for Patient management
 */

public interface PatientDao extends JpaRepository<Patient, Integer> {

    /**
     * @param id
     * @return Patient
     */
    Patient findPatientByPatientId(Integer id);

    /**
     * Finds a patient linked to the given user
     *
     * @param user
     * @return Patient
     */
    Patient findPatientByUser(User user);

}
