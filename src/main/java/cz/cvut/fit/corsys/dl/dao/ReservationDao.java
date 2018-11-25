package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Patient;
import cz.cvut.fit.corsys.dl.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {

    Reservation findReservationByReservationId(Integer id);

    List<Reservation> findReservationsByPatient(Patient patient);

    List<Reservation> findReservationsByPatientAndDate(Patient patient, LocalDate localDate);

    List<Reservation> findReservationsByPatientAndDateAfter(Patient patient, LocalDate localDate);

    List<Reservation> findReservationsByDoctor(Doctor doctor);

    List<Reservation> findReservationsByDoctorAndDate(Doctor doctor, LocalDate localDate);

    List<Reservation> findReservationsByDoctorAndDateAfter(Doctor doctor, LocalDate localDate);

    List<Reservation> findReservationsByState(String state);

}
