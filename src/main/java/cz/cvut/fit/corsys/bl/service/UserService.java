package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User findByUsername(String username);

    Role getRole(String role);

    User getLoggedUser();

}
