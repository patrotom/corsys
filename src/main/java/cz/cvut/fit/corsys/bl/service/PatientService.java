package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Patient;
import cz.cvut.fit.corsys.dl.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface PatientService {

    /**
     * Creates a patient.
     * This method is intended to create a new, non-existing patient.
     *
     * @param patient the patient to be created.
     * @return the created patient. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified patient already exists.
     */
    Patient createPatient(Patient patient) throws IllegalArgumentException;

    /**
     * Updates a patient.
     * This method is intended to update existing patients only.
     *
     * @param patient the patient to be updated.
     * @return the updated patient. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified patient does not exist.
     */
    Patient updatePatient(Patient patient) throws IllegalArgumentException;

    /**
     * Deletes a patient.
     *
     * @param patient the patient to be deleted.
     * @throws IllegalArgumentException in case that the specified patient does not exist.
     */
    void deletePatient(Patient patient) throws IllegalArgumentException;

    /**
     * Finds all patients.
     *
     * @return list of all patients.
     */
    List<Patient> findAllPatients();

    /**
     * Returns patient for the specified id.
     *
     * @param id the id for which to find patient.
     * @return the corresponding patient, null if such patient does not exist.
     */
    Patient getPatient(Integer id);

    /**
     * Finds patient for specific username.
     *
     * @param username username to be found.
     * @return the found patient, null if specified username could not be found.
     */
    Patient findPatientByUsername(String username);

    /**
     * Finds all reservations assigned to the specified patient.
     *
     * @param patient the patient whose reservations should be found.
     * @return list of found reservations.
     */
    List<Reservation> findReservations(Patient patient);

    /**
     * Finds all reservations assigned to the specified patient for dates since the specified date including.
     *
     * @param patient the patient whose reservations should be found.
     * @param date    date since when to find reservations (including).
     * @return list of found reservations.
     */
    List<Reservation> findReservationsSince(Patient patient, LocalDate date);

    /**
     * Finds notifications belonging to the specified patient.
     *
     * @param patient the patient for which to find notifications.
     * @return list of all notifications for the specified patient.
     */
    List<Notification> findNotifications(Patient patient);
}
