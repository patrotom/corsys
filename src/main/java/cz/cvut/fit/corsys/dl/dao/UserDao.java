package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface defines data layer interface for User management
 */

public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * @param id
     * @return User
     */
    User findUserByUserId(Integer id);

    /**
     * Finds particular user by its unique username
     *
     * @param username
     * @return User
     */
    User findUserByUsername(String username);

}
