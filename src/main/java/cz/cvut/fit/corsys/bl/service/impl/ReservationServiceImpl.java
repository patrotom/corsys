package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.ReservationService;
import cz.cvut.fit.corsys.bl.service.TimetableService;
import cz.cvut.fit.corsys.dl.dao.ReservationDao;
import cz.cvut.fit.corsys.dl.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private TimetableService timetableService;

    @Override
    public Reservation getReservation(Integer id) {
        return reservationDao.findReservationByReservationId(id);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        if (reservationDao.findReservationByReservationId(reservation.getReservationId()) != null) {
            throw new IllegalArgumentException();
        }
        return reservationDao.save(reservation);
    }

    @Override
    public Reservation confirmReservation(Reservation reservation) throws IllegalArgumentException {
        Reservation dbReservation = reservationDao.findReservationByReservationId(reservation.getReservationId());
        if (dbReservation == null) {
            throw new IllegalArgumentException();
        }
        if (!dbReservation.getReservationId().equals(reservation.getReservationId())) {
            throw new IllegalArgumentException();
        }
        reservation.setState(Reservation.STATE_CONFIRMED);
        return modifyReservation(reservation);
    }

    @Override
    public Reservation cancelReservation(Reservation reservation) throws IllegalArgumentException {
        Reservation dbReservation = reservationDao.findReservationByReservationId(reservation.getReservationId());
        if (dbReservation == null) {
            throw new IllegalArgumentException();
        }
        if (!dbReservation.getReservationId().equals(reservation.getReservationId())) {
            throw new IllegalArgumentException();
        }
        reservation.setState(Reservation.STATE_CANCELED);
        return modifyReservation(reservation);
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) throws IllegalArgumentException {
        Reservation dbReservation = reservationDao.findReservationByReservationId(reservation.getReservationId());
        if (dbReservation == null) {
            throw new IllegalArgumentException();
        }
        if (!dbReservation.getReservationId().equals(reservation.getReservationId())) {
            throw new IllegalArgumentException();
        }
        return reservationDao.save(reservation);
    }

    @Override
    public List<Reservation> findReservationsToConfirm(Department department) throws IllegalArgumentException {
        return reservationDao.findReservationsByState(Reservation.STATE_UNCONFIRMED);
    }

    @Override
    public List<LocalTime> findFreeTerms(LocalDate date, Doctor doctor, Examination examination) {
        List<Timetable> timetables = timetableService.findTimetablesForDate(doctor, date);
        List<Reservation> reservations = reservationDao.findReservationsByDoctorAndDate(doctor, date);
        int examinationLength = examination.getLength();
        List<LocalTime> freeTerms = new ArrayList<>();

//        System.out.println("Timetables count: " + timetables.size());
//        System.out.println("Reservations count: " + reservations.size());
//        System.out.println("Examination length: " + examinationLength);

        for (Reservation reservation : reservations) {
            timetables = timetableService.subtractTimeInterval(timetables, reservation.getTimeFrom(), reservation.getTimeTo());
        }

        for (Timetable timetable : timetables) {
//            System.out.println("Timetable " + timetable.getTimetableId());
//            System.out.println(" - from: " + timetable.getTimeFrom());
//            System.out.println(" - to: " + timetable.getTimeTo());

            Integer length = timetableService.getTimetableLength(timetable);
            for (int i = 0; i < length / examinationLength; i++) {
                LocalTime timeFrom = timetable.getTimeFrom();
                freeTerms.add(timeFrom.plusMinutes(i * (examinationLength * 15)));
            }
        }

        return freeTerms;
    }
}
