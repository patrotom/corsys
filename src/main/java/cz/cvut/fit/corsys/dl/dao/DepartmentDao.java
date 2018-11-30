package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Integer> {

    /**
     * @param id
     * @return Department
     */
    Department findDepartmentByDepartmentId(Integer id);

    Department findDepartmentByName(String name);

}
