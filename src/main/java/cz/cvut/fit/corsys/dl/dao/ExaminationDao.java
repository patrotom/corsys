package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationDao extends JpaRepository<Examination, Integer> {

    /**
     * @param id
     * @return Examination
     */
    Examination findExaminationByExaminationId(Integer id);

    /**
     * Finds all Examinations in given department.
     *
     * @param department
     * @return List of Examinations
     */
    List<Examination> findExaminationsByDepartment(Department department);

}
