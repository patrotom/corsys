package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    Reservation getReservation(Long id);

    Reservation createReservation(Reservation reservation);

    Reservation confirmReservation(Reservation reservation);

    Reservation cancelReservation(Reservation reservation);

    Reservation modifyReservation(Reservation reservation);

    List<LocalTime> findFreeTerms(LocalDate date, Doctor doctor, Examination examination);

}
