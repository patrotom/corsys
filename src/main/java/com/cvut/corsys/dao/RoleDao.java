package com.cvut.corsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvut.corsys.entity.Role;

public interface RoleDao extends JpaRepository<Role, String> {

}
