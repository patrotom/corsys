package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    //    List<Role> findRoleByName(String name);
    Role findByName(String name);
}
