package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface defines data layer interface for Role management
 */

public interface RoleDao extends JpaRepository<Role, Integer> {

    /**
     * @param id
     * @return Role
     */
    Role findRoleByRoleId(Integer id);

    /**
     * Finds particular role, by its unique name
     *
     * @param name
     * @return Role
     */
    Role findRoleByName(String name);

}
