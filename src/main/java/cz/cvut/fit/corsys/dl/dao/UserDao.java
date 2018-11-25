package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserByUserId(Integer id);

}
