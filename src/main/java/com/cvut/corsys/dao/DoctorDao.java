package com.cvut.corsys.dao;

import com.cvut.corsys.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

}
