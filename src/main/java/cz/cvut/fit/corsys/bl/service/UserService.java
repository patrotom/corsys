package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> findUsers();

    User findByUsername(String username);

    Role getRole(String role);

    User getLoggedUser();

//    List<Notification> getNotifications(User user);

}
