package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;

import java.util.List;

public interface UserService {

    void createDoctor(Doctor doc);

    List<Doctor> findDoctor();

    User findByUsername(String username);

    Role getRole(String role);

    User getLoggedUser();

}
