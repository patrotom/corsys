package cz.cvut.fit.corsys;

import com.google.common.base.Preconditions;
import cz.cvut.fit.corsys.dl.dao.RoleDao;
import cz.cvut.fit.corsys.dl.dao.UserDao;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorsysApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(CorsysApplication.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void insertRole() {

        Role role = new Role();
        role.setName("ADMIN");
        roleDao.save(role);

        Preconditions.checkState(roleDao.findByName("ADMIN").getName().equals("ADMIN"));


    }

    @Test
    public void insertUserRole() {
        Role role = new Role();
        role.setName("DOCTOR");

        System.out.println("created Role");
        roleDao.save(role);
        System.out.println("Added Role to db");


        User user = new User();
        user.setActive(true);
        user.setUsername("username_test");
        user.setPassword("pass");

        user.setEmail("email@email.com");
        user.setFirstName("first");
        user.setLastName("last");
        System.out.println("trying to add found DOCTOR role.");
        Role doctor = roleDao.findByName("DOCTOR");
        System.out.println(doctor);
        user.setRole(doctor);
        System.out.println("doctor role added");

        System.out.println("Adding User");
        userDao.save(user);
        System.out.println("Added User to db");

        Preconditions.checkState(
                userDao.findUserByUsername("username_test").getRole().getName()
                        .equals("DOCTOR"));
    }

    @Test
    public void springNativeQueryTest() {

        Role role = new Role();
        role.setName("TEST");
        roleDao.save(role);

        Preconditions.checkState(
                roleDao.findByName("TEST").getName()
                        .equals("TEST")
        );

    }

    @Test
    public void ManyToOneTest() {
        Role role = new Role();
        role.setName("PATIENT");
        roleDao.save(role);

        User user = new User();
        user.setActive(true);
        user.setUsername("1username");
        user.setPassword("pass");

        user.setEmail("email@email.com");
        user.setFirstName("1 name");
        user.setLastName("1 lastname");
        System.out.println("found patient: " + roleDao.findByName("PATIENT").getName());
        user.setRole(roleDao.findByName("PATIENT"));
        userDao.save(user);

        user = new User();
        user.setActive(true);
        user.setUsername("2username");
        user.setPassword("pass");

        user.setEmail("email@email.com");
        user.setFirstName("2 name");
        user.setLastName("2 lastname");
        System.out.println("found patient: " + roleDao.findByName("PATIENT").getName());
        user.setRole(roleDao.findByName("PATIENT"));
        userDao.save(user);

        Preconditions.checkState(
                userDao.findUserByUsername("1username").getRole().getName()
                        .equals("PATIENT")
        );
        Preconditions.checkState(
                userDao.findUserByUsername("2username").getRole().getName()
                        .equals("PATIENT")
        );

    }

}
