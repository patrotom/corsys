package com.cvut.corsys.service;

import java.util.List;

import com.cvut.corsys.entity.Doctor;
import com.cvut.corsys.entity.Role;
import com.cvut.corsys.entity.User;

public interface UserService {
	
	void createDoctor(Doctor doc);

	List<Doctor> findDoctor();

	User findByUsername(String username);

	Role getRole(String role);

}
