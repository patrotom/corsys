package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;

import java.util.List;

public interface DoctorService {

    void createDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Doctor doctor);

    List<Doctor> findDoctors();

}
