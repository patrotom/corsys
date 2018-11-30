package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Receptionist;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionistDao extends JpaRepository<Receptionist, Integer> {

    /**
     * @param id
     * @return Receptionist
     */
    Receptionist findReceptionistByReceptionistId(Integer id);

    /**
     * Finds particular Receptionist entity by given user.
     *
     * @param user
     * @return Receptionist
     */
    Receptionist findReceptionistByUser(User user);

    /**
     * By given department, finds all Receptionists assigned to it.
     *
     * @param department
     * @return List of Receptionist
     */
    List<Receptionist> findReceptionistsByDepartment(Department department);

}
