package com.cvut.corsys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvut.corsys.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	// TODO vyskusat ci nejde vratit rovno jeden
	List<User> findByUsername(String username);
	
}
