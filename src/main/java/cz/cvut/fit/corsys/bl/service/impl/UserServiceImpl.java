package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.dl.dao.DoctorDao;
import cz.cvut.fit.corsys.dl.dao.RoleDao;
import cz.cvut.fit.corsys.dl.dao.UserDao;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;
import cz.cvut.fit.corsys.bl.service.UserService;
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

    public void createUser(User user) {
        this.userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = this.userDao.findByUsername(username);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Role getRole(String role) {
        return this.roleDao.getOne(role);
    }

    @Override
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            List<User> users = this.userDao.findByUsername(currentUserName);
            return users.isEmpty() ? null : users.get(0);
        }
        return null;
    }


}
