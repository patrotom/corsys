package com.cvut.corsys.dao;

import com.cvut.corsys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    // TODO vyskusat ci nejde vratit rovno jeden
    List<User> findByUsername(String username);

}
