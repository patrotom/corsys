package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface defines data layer interface for Department management
 */

public interface DepartmentDao extends JpaRepository<Department, Integer> {

    /**
     * @param id
     * @return Department
     */
    Department findDepartmentByDepartmentId(Integer id);

    /**
     * Finds Department by given name.
     * @param name
     * @return Department
     */
    Department findDepartmentByName(String name);

}
