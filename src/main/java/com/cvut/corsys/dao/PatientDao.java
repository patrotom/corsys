package com.cvut.corsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvut.corsys.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long>{

}
