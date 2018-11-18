package com.cvut.corsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvut.corsys.entity.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long>{

}
