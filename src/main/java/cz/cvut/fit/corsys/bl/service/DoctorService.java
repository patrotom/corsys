package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface DoctorService {

    /**
     * Creates a doctor.
     * This method is intended to create a new, non-existing doctor.
     *
     * @param doctor the doctor to be created.
     * @return the created doctor. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified doctor already exists.
     */
    Doctor createDoctor(Doctor doctor) throws IllegalArgumentException;

    /**
     * Updates a doctor.
     * This method is intended to update existing doctors only.
     *
     * @param doctor the doctor to be updated.
     * @return the updated doctor. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified doctor does not exist.
     */
    Doctor updateDoctor(Doctor doctor) throws IllegalArgumentException;

    /**
     * Deletes a doctor.
     *
     * @param doctor the doctor to be deleted.
     * @throws IllegalArgumentException in case that the specified doctor does not exist.
     */
    void deleteDoctor(Doctor doctor) throws IllegalArgumentException;

    /**
     * Returns doctor for the specified id.
     *
     * @param id the id for which to find doctor.
     * @return the corresponding doctor, null if such doctor does not exist.
     */
    Doctor getDoctor(Integer id);

    /**
     * Finds doctor for specific username.
     *
     * @param username username to be found.
     * @return the found doctor, null if specified username could not be found.
     */
    Doctor findDoctorByUsername(String username);

    /**
     * Finds all doctors.
     *
     * @return list of all doctors.
     */
    List<Doctor> findAllDoctors();

    /**
     * Finds all reservations assigned to the specified doctor.
     *
     * @param doctor the doctor whose reservations should be found.
     * @return list of found reservations.
     */
    List<Reservation> findReservations(Doctor doctor);

    /**
     * Finds all reservations assigned to the specified doctor for dates since the specified date including.
     *
     * @param doctor the doctor whose reservations should be found.
     * @param date   date since when to find reservations (including).
     * @return list of found reservations.
     */
    List<Reservation> findReservationsSince(Doctor doctor, LocalDate date);

    /**
     * Finds notifications belonging to the specified doctor.
     *
     * @param doctor the doctor for which to find notifications.
     * @return list of all notifications for the specified doctor.
     */
    List<Notification> findNotifications(Doctor doctor);

}
