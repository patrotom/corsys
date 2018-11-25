package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.PatientService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.dao.PatientDao;
import cz.cvut.fit.corsys.dl.dao.ReservationDao;
import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Patient;
import cz.cvut.fit.corsys.dl.entity.Reservation;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public Patient createPatient(Patient patient) {
        if (patientDao.findPatientByPatientId(patient.getPatientId()) != null) {
            throw new IllegalArgumentException();
        }
        User user = patient.getUser();
        user = userService.createUser(user);
        patient.setUser(user);
        return patientDao.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) throws IllegalArgumentException {
        Patient dbPatient = patientDao.findPatientByPatientId(patient.getPatientId());
        if (dbPatient == null) {
            throw new IllegalArgumentException();
        }
        if (! dbPatient.getPatientId().equals(patient.getPatientId())) {
            throw new IllegalArgumentException();
        }
        User user = userService.updateUser(patient.getUser());
        patient.setUser(user);
        return patientDao.save(patient);
    }

    @Override
    public void deletePatient(Patient patient) throws IllegalArgumentException {
        Patient dbPatient = patientDao.findPatientByPatientId(patient.getPatientId());
        if (dbPatient == null) {
            throw new IllegalArgumentException();
        }
        if (! dbPatient.getPatientId().equals(patient.getPatientId())) {
            throw new IllegalArgumentException();
        }
        User user = patient.getUser();
        patientDao.delete(patient);
        userService.deleteUser(user);
    }

    @Override
    public List<Patient> findAllPatients() {
        return patientDao.findAll();
    }

    @Override
    public Patient getPatient(Integer id) {
        return patientDao.findPatientByPatientId(id);
    }

    @Override
    public Patient findPatientByUsername(String username) {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return patientDao.findPatientByUser(user);
        }
        return null;
    }

    @Override
    public List<Reservation> findReservations(Patient patient) {
        return reservationDao.findReservationsByPatient(patient);
    }

    @Override
    public List<Reservation> findReservationsSince(Patient patient, LocalDate date) {
        List<Reservation> reservationsToday = reservationDao.findReservationsByPatientAndDate(patient, date);
        List<Reservation> reservationsNext = reservationDao.findReservationsByPatientAndDateAfter(patient, date);
        List<Reservation> newList = new ArrayList<Reservation>(reservationsToday);
        newList.addAll(reservationsNext);
        return newList;
    }

    @Override
    public List<Notification> findNotifications(Patient patient) {
        User user = patient.getUser();
        return userService.findNotifications(user);
    }
}
