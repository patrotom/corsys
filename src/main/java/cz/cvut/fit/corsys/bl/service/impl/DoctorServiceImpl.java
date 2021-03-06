package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.DoctorService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.dao.DoctorDao;
import cz.cvut.fit.corsys.dl.dao.ReservationDao;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Notification;
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
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public Doctor getDoctor(Integer id) {
        return doctorDao.findDoctorByDoctorId(id);
    }

    @Override
    public Doctor findDoctorByUsername(String username) {
        User user = userService.findUserByUsername(username);
        return doctorDao.findDoctorByUser(user);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        if (doctorDao.findDoctorByDoctorId(doctor.getDoctorId()) != null) {
            throw new IllegalArgumentException();
        }
        User user = doctor.getUser();
        user = userService.createUser(user);
        doctor.setUser(user);
        return doctorDao.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        Doctor dbDoctor = doctorDao.findDoctorByDoctorId(doctor.getDoctorId());
        if (dbDoctor == null) {
            throw new IllegalArgumentException();
        }
        if (!dbDoctor.getDoctorId().equals(doctor.getDoctorId())) {
            throw new IllegalArgumentException();
        }
        User user = userService.updateUser(doctor.getUser());
        doctor.setUser(user);
        return doctorDao.save(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        Doctor dbDoctor = doctorDao.findDoctorByDoctorId(doctor.getDoctorId());
        if (dbDoctor == null) {
            throw new IllegalArgumentException();
        }
        if (!dbDoctor.getDoctorId().equals(doctor.getDoctorId())) {
            throw new IllegalArgumentException();
        }
        User user = doctor.getUser();
        doctorDao.delete(doctor);
        userService.deleteUser(user);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return doctorDao.findAll();
    }

    @Override
    public List<Reservation> findReservations(Doctor doctor) {
        return reservationDao.findReservationsByDoctor(doctor);
    }

    @Override
    public List<Reservation> findReservationsSince(Doctor doctor, LocalDate date) {
        List<Reservation> reservationsToday = reservationDao.findReservationsByDoctorAndDate(doctor, date);
        List<Reservation> reservationsNext = reservationDao.findReservationsByDoctorAndDateAfter(doctor, date);
        List<Reservation> newList = new ArrayList<Reservation>(reservationsToday);
        newList.addAll(reservationsNext);
        return newList;
    }

    @Override
    public List<Notification> findNotifications(Doctor doctor) {
        User user = doctor.getUser();
        return userService.findNotifications(user);
    }
}
