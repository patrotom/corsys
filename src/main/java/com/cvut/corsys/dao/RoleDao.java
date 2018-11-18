package com.cvut.corsys.dao;

import com.cvut.corsys.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, String> {

}
