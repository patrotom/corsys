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
        return patientDao.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) throws IllegalArgumentException {
        if (patientDao.findPatientByPatientId(patient.getPatientId()) == null) {
            throw new IllegalArgumentException();
        }
        return patientDao.save(patient);
    }

    @Override
    public void deletePatient(Patient patient) throws IllegalArgumentException {
        if (patientDao.findPatientByPatientId(patient.getPatientId()) == null) {
            throw new IllegalArgumentException();
        }
        patientDao.delete(patient);
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
        return reservationDao.findReservationsByPatientAndDateAfter(patient, date);
    }

    @Override
    public List<Notification> findNotifications(Patient patient) {
        User user = patient.getUser();
        return userService.findNotifications(user);
    }
}
