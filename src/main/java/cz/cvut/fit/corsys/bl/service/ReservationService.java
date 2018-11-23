package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Examination;
import cz.cvut.fit.corsys.dl.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    /**
     * Returns reservation for the specified id.
     *
     * @param id the id for which to find reservation.
     * @return the corresponding reservation, null if such reservation does not exist.
     */
    Reservation getReservation(Long id);

    /**
     * Creates new reservation.
     *
     * @param reservation the reservation to be created.
     * @return the created reservation. Use this object for further operations.
     */
    Reservation createReservation(Reservation reservation);

    /**
     * Changes state of existing reservation to confirmed.
     *
     * @param reservation the reservation to be confirmed.
     * @return the modified reservation. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified reservation does not exist.
     */
    Reservation confirmReservation(Reservation reservation) throws IllegalArgumentException;

    /**
     * Changes state of existing reservation to canceled.
     *
     * @param reservation the reservation to be canceled.
     * @return the modified reservation. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified reservation does not exist.
     */
    Reservation cancelReservation(Reservation reservation) throws IllegalArgumentException;

    /**
     * Modifies an existing reservation. This method should be used for changes other than state changes.
     *
     * @param reservation the reservation to be modified.
     * @return the modified reservation. Use this object for further operations.
     * @throws IllegalArgumentException in case that the specified reservation does not exist.
     */
    Reservation modifyReservation(Reservation reservation) throws IllegalArgumentException;

    /**
     * Finds non-confirmed reservations that should be checked and confirmed by receptionist.
     *
     * @param department the department for which to find reservations.
     * @return list of found reservations.
     * @throws IllegalArgumentException in case that the specified department does not exist.
     */
    List<Reservation> findReservationsToConfirm(Department department) throws IllegalArgumentException;

    /**
     * Finds free terms for the specified day, doctor and examinations.
     *
     * @param date        the day.
     * @param doctor      the doctor.
     * @param examination the examination.
     * @return list of starting times of free terms for the specified parameters.
     */
    List<LocalTime> findFreeTerms(LocalDate date, Doctor doctor, Examination examination);

}
