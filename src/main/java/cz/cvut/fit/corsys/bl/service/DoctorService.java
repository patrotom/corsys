package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    void deleteDoctor(Doctor doctor);

    List<Doctor> findAllDoctors();

    List<Reservation> findReservations(Doctor doctor);

    List<Reservation> findReservationsSince(Doctor doctor, LocalDate date);

    List<Notification> findNotifications(Doctor doctor);

}
