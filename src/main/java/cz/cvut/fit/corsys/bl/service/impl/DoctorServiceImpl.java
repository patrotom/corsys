package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.DoctorService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.dao.DoctorDao;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private UserService userService;

    @Override
    public void createDoctor(Doctor doctor) {
        this.userService.createUser(doctor.getUser());
        this.doctorDao.save(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        this.userService.updateUser(doctor.getUser());
        this.doctorDao.save(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        this.userService.deleteUser(doctor.getUser());
        this.doctorDao.delete(doctor);
    }

    @Override
    public List<Doctor> findDoctors() {
        return this.doctorDao.findAll();
    }
}
