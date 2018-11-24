package cz.cvut.fit.corsys;

import com.google.common.base.Preconditions;
import cz.cvut.fit.corsys.bl.service.PatientService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.dao.PatientDao;
import cz.cvut.fit.corsys.dl.dao.RoleDao;
import cz.cvut.fit.corsys.dl.dao.UserDao;
import cz.cvut.fit.corsys.dl.entity.Patient;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorsysApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(CorsysApplication.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Test
    public void insertRole() {

        Role role = new Role();
        role.setName("ADMIN");
        roleDao.save(role);

        Preconditions.checkState(roleDao.findRoleByName("ADMIN").getName().equals("ADMIN"));


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
        Role doctor = roleDao.findRoleByName("DOCTOR");
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
                roleDao.findRoleByName("TEST").getName()
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
        System.out.println("found patient: " + roleDao.findRoleByName("PATIENT").getName());
        user.setRole(roleDao.findRoleByName("PATIENT"));
        userDao.save(user);

        user = new User();
        user.setActive(true);
        user.setUsername("2username");
        user.setPassword("pass");

        user.setEmail("email@email.com");
        user.setFirstName("2 name");
        user.setLastName("2 lastname");
        System.out.println("found patient: " + roleDao.findRoleByName("PATIENT").getName());
        user.setRole(roleDao.findRoleByName("PATIENT"));
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

    @Test
    public void UserDaoTest() {
        Role role = new Role();
        role.setName("PATIENT");
        roleDao.save(role);

        User user = new User();
        user.setActive(true);
        user.setUsername("3username");
        user.setPassword("pass");
        user.setEmail("email@email.com");
        user.setFirstName("3 name");
        user.setLastName("3 lastname");
        user.setRole(role);
        userDao.save(user);

        List<User> users = userDao.findAll();
        Integer id = users.get(0).getUserId();

        Preconditions.checkState(
                userDao.findUserByUserId(id).getUsername().equals("3username")
        );

        System.out.println("User ID: " + id);

        Preconditions.checkState(
                userDao.findUserByUsername("3username").getUserId().equals(id)
        );
    }

    @Test
    public void ServiceTest() {
        Role role = new Role();
        role.setName("PATIENT");
        roleDao.save(role);

        User user = new User();
        user.setActive(true);
        user.setUsername("3username");
        user.setPassword("pass");
        user.setEmail("email@email.com");
        user.setFirstName("3 name");
        user.setLastName("3 lastname");
        user.setRole(role);

        Patient patient = new Patient();
        patient.setUser(user);

        patient = patientService.createPatient(patient);
        user = patient.getUser();
        user.setUsername("4username");
        patient.setUser(user);
        patientService.updatePatient(patient);

        System.out.println(patientDao.findAll().size());
        System.out.println(userService.findUserByUsername("4username"));

        System.out.println(patientService.findPatientByUsername("4username"));

        patientService.deletePatient(patient);
        System.out.println(userService.findUserByUsername("4username"));

    }

}
