package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    void deleteUser(User user);

    User updateUser(User user);

    List<User> findAllUsers();

    User findByUsername(String username);

    Role getRole(String role);

    User getLoggedUser();

    List<Notification> findNotifications(User user);

}
