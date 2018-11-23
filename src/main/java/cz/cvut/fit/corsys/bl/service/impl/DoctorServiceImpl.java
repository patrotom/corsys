package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.DoctorService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.dao.DoctorDao;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private UserService userService;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        this.userService.createUser(doctor.getUser());
        return this.doctorDao.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        this.userService.updateUser(doctor.getUser());
        return this.doctorDao.save(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        this.userService.deleteUser(doctor.getUser());
        this.doctorDao.delete(doctor);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return this.doctorDao.findAll();
    }

    @Override
    public List<Reservation> findReservations(Doctor doctor) {
        //TODO implement
        return null;
    }

    @Override
    public List<Reservation> findReservationsSince(Doctor doctor, LocalDate date) {
        //TODO implement
        return null;
    }

    @Override
    public List<Notification> findNotifications(Doctor doctor) {
        //TODO implement
        return null;
    }
}
