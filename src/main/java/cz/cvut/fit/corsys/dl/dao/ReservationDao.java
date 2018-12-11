package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Patient;
import cz.cvut.fit.corsys.dl.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface defines data layer interface for Reservation management
 */

public interface ReservationDao extends JpaRepository<Reservation, Integer> {

    /**
     * @param id
     * @return Reservation
     */
    Reservation findReservationByReservationId(Integer id);

    /**
     * By given Patient finds all Reservations for him.
     *
     * @param patient
     * @return List of Reservations
     */
    List<Reservation> findReservationsByPatient(Patient patient);

    /**
     * By given Patient and particular date, finds all Reservations for him.
     *
     * @param patient
     * @param localDate
     * @return List of Reservations
     */
    List<Reservation> findReservationsByPatientAndDate(Patient patient, LocalDate localDate);

    /**
     * Finds all Reservations after some particular time for the given Patient
     *
     * @param patient
     * @param localDate
     * @return List of Reservations
     */
    List<Reservation> findReservationsByPatientAndDateAfter(Patient patient, LocalDate localDate);

    /**
     * Finds all Reservations for given Doctor
     *
     * @param doctor
     * @return List of Reservations
     */
    List<Reservation> findReservationsByDoctor(Doctor doctor);

    /**
     * By given Doctor and particular date, finds all Reservations for him.
     *
     * @param doctor
     * @param localDate
     * @return List of Reservations
     */
    List<Reservation> findReservationsByDoctorAndDate(Doctor doctor, LocalDate localDate);

    /**
     * Finds all Reservations after some particular time for the given Doctor
     *
     * @param doctor
     * @param localDate
     * @return List of Reservations
     */
    List<Reservation> findReservationsByDoctorAndDateAfter(Doctor doctor, LocalDate localDate);

    /**
     * Find all reservations by given state
     *
     * @param state
     * @return List of Reservations
     */
    List<Reservation> findReservationsByState(String state);

}
