package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Receptionist;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionistDao extends JpaRepository<Receptionist, Integer> {

    Receptionist findReceptionistByReceptionistId(Integer id);

    Receptionist findReceptionistByUser(User user);

    List<Receptionist> findReceptionistsByDepartment(Department department);

}
