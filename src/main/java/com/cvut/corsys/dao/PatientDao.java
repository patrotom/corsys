package com.cvut.corsys.dao;

import com.cvut.corsys.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
