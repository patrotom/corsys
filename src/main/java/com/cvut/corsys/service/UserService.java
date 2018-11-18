package com.cvut.corsys.service;

import com.cvut.corsys.entity.Doctor;
import com.cvut.corsys.entity.Role;
import com.cvut.corsys.entity.User;

import java.util.List;

public interface UserService {

    void createDoctor(Doctor doc);

    List<Doctor> findDoctor();

    User findByUsername(String username);

    Role getRole(String role);

}
