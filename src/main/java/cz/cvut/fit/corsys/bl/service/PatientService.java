package cz.cvut.fit.corsys.bl.service;

public interface PatientService {

    Patient createPatient(Patient patient);

    Patient updatePatient(Patient patient);

    void deletePatient(Patient patient);

    List<Patient> findAllPatients();

    List<Reservation> findReservations(Patient patient);

    List<Reservation> findReservationsSince(Patient patient, LocalDate date);

    List<Notification> findNotifications(Patient patient);
}
