package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.dao.NotificationDao;
import cz.cvut.fit.corsys.dl.dao.RoleDao;
import cz.cvut.fit.corsys.dl.dao.UserDao;
import cz.cvut.fit.corsys.dl.entity.Notification;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private NotificationDao notificationDao;

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(User user) {
        if (userDao.findUserByUserId(user.getUserId()) == null) {
            throw new IllegalArgumentException();
        }
        userDao.delete(user);
    }

    @Override
    public User updateUser(User user) {
        if (userDao.findUserByUserId(user.getUserId()) == null) {
            throw new IllegalArgumentException();
        }
        return userDao.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(Integer id) {
        return userDao.findUserByUserId(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public Role findRole(String role) {
        return roleDao.findRoleByName(role);
    }

    @Override
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userDao.findUserByUsername(currentUserName);
        }
        return null;
    }

    @Override
    public List<Notification> findNotifications(User user) {
        return notificationDao.findNotificationsByUser(user);
    }


}
