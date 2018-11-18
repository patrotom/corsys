package com.cvut.corsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cvut.corsys.dao.DoctorDao;
import com.cvut.corsys.dao.RoleDao;
import com.cvut.corsys.dao.UserDao;
import com.cvut.corsys.entity.Doctor;
import com.cvut.corsys.entity.Role;
import com.cvut.corsys.entity.User;
import com.cvut.corsys.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DoctorDao doctorDao;

	public void createUser(User user) {
		this.userDao.save(user);
	}

	public void createDoctor(Doctor doc) {
		this.userDao.save(doc.getUser());
		this.doctorDao.save(doc);
	}

	@Override
	public List<Doctor> findDoctor() {
		return this.doctorDao.findAll();
	}

	@Override
	public User findByUsername(String username) {
		List<User> users = this.userDao.findByUsername(username);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public Role getRole(String role) {
		return this.roleDao.getOne(role);
	}
}
