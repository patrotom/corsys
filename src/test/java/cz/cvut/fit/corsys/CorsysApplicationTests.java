package cz.cvut.fit.corsys;

import com.google.common.base.Preconditions;
import cz.cvut.fit.corsys.bl.service.*;
import cz.cvut.fit.corsys.dl.dao.*;
import cz.cvut.fit.corsys.dl.entity.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CorsysApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(CorsysApplication.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ReceptionistDao receptionistDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ExaminationDao examinationDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private TimetableDao timetableDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReceptionistService receptionistService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ReservationService reservationService;

    @Test
    public void T01insertRole() {

        Role role = new Role();
        role.setName("ADMIN");
        roleDao.save(role);

        Preconditions.checkState(roleDao.findRoleByName("ADMIN").getName().equals("ADMIN"));
    }

    @Test
    public void T02insertUserRole() {
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
    public void T03springNativeQueryTest() {

        Role role = new Role();
        role.setName("TEST");
        roleDao.save(role);

        Preconditions.checkState(
                roleDao.findRoleByName("TEST").getName()
                        .equals("TEST")
        );

    }

    @Test
    public void T04ManyToOneTest() {
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
    public void T05UserDaoTest() {

        User user = new User();
        user.setActive(true);
        user.setUsername("3username");
        user.setPassword("pass");
        user.setEmail("email@email.com");
        user.setFirstName("3 name");
        user.setLastName("3 lastname");
        user.setRole(roleDao.findRoleByName(Role.ROLE_PATIENT));
        userDao.save(user);

        Integer id = userDao.findUserByUsername("3username").getUserId();
        System.out.println("User ID: " + id);

        Preconditions.checkState(
                userDao.findUserByUserId(id).getUsername().equals("3username")
        );

    }

    @Test
    public void T06PatientDaoTest() {
        Role role = roleDao.findRoleByName(Role.ROLE_PATIENT);
        User user = userDao.findUserByUsername("3username");
        Patient p = new Patient();
        p.setUser(user);
        patientDao.save(p);

        Preconditions.checkState(
                user.getUsername().equals("3username")
        );

        Patient patient = patientDao.findPatientByUser(user);

        Preconditions.checkState(
                patient.getPatientId().equals(p.getPatientId())
        );
    }

    @Test
    public void T07ReceptionistDaoTest() {
        Role role = new Role();
        role.setName(Role.ROLE_RECEPTIONIST);
        roleDao.save(role);

        Department department = new Department();
        department.setName("dep1");
        department = departmentDao.save(department);

        User user = new User();
        user.setActive(true);
        user.setUsername("recep1");
        user.setPassword("pass");
        user.setEmail("email@email.com");
        user.setFirstName("recep1a");
        user.setLastName("recep1b");
        user.setRole(role);
        userDao.save(user);
        Receptionist r = new Receptionist();
        r.setUser(user);
        r.setDepartment(department);
        receptionistDao.save(r);

        Receptionist r1 = receptionistDao.findReceptionistByUser(user);
        List<Receptionist> listRecep = receptionistDao.findReceptionistsByDepartment(departmentDao.findDepartmentByName("dep1"));

        Preconditions.checkState(
                r1.getReceptionistId().equals(listRecep.get(0).getReceptionistId())
        );
    }

    @Test
    public void T08ExaminationDaoTest() {
        Department department = departmentDao.findDepartmentByName("dep1");

        Examination e1 = new Examination();
        e1.setLength(4);
        e1.setType("prehliadka");
        e1.setDepartment(department);
        examinationDao.save(e1);

        List<Examination> list = examinationDao.findExaminationsByDepartment(departmentDao.findDepartmentByName("dep1"));

        Preconditions.checkState(
                list.get(0).getLength().equals(e1.getLength())
        );
    }

    @Test
    public void T09ReservationDaoTest() {
        User user = userDao.findUserByUsername("username_test");
        Department department = departmentDao.findDepartmentByName("dep1");
        Examination examination = examinationDao.findExaminationsByDepartment(department).get(0);
        Patient patient = patientDao.findAll().get(0);
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setDepartment(department);
        doctor = doctorDao.save(doctor);

        Reservation reservation = new Reservation();
        reservation.setState(Reservation.STATE_UNCONFIRMED);
        reservation.setDoctor(doctor);
        reservation.setExamination(examination);
        reservation.setPatient(patient);
        reservation.setDate(LocalDate.of(2018, 12, 01));
        reservation.setTimeFrom(LocalTime.of(8, 15));
        reservation.setTimeTo(LocalTime.of(9, 15));
        reservationDao.save(reservation);

        Reservation reservation2 = new Reservation();
        reservation2.setState(Reservation.STATE_CONFIRMED);
        reservation2.setDoctor(doctor);
        reservation2.setExamination(examination);
        reservation2.setPatient(patient);
        reservation2.setDate(LocalDate.of(2018, 12, 02));
        reservation2.setTimeFrom(LocalTime.of(8, 15));
        reservation2.setTimeTo(LocalTime.of(9, 15));
        reservationDao.save(reservation2);

        Reservation reservation3 = new Reservation();
        reservation3.setState(Reservation.STATE_CONFIRMED);
        reservation3.setDoctor(doctor);
        reservation3.setExamination(examination);
        reservation3.setPatient(patient);
        reservation3.setDate(LocalDate.of(2018, 12, 03));
        reservation3.setTimeFrom(LocalTime.of(8, 15));
        reservation3.setTimeTo(LocalTime.of(9, 15));
        reservationDao.save(reservation3);

        Preconditions.checkState(
                reservationDao.findReservationsByState(Reservation.STATE_UNCONFIRMED).size() == 1
        );

        Preconditions.checkState(
                reservationDao.findReservationsByDoctor(doctor).size() == 3
        );

        List<Reservation> reservations = reservationDao.findReservationsByDoctorAndDateAfter(doctor, LocalDate.of(2018, 12, 02));
        for (Reservation r : reservations) {
            System.out.println(r.getDate());
        }
    }

    @Test
    public void T10TimetableDaoTest() {
        User user = userDao.findUserByUsername("username_test");
        Doctor doctor = doctorDao.findDoctorByUser(user);

        Timetable timetable = new Timetable();
        timetable.setDoctor(doctor);
        timetable.setDate(LocalDate.of(2018, 12, 1));
        timetable.setTimeFrom(LocalTime.of(8, 0));
        timetable.setTimeTo(LocalTime.of(15, 0));
        timetableDao.save(timetable);

        Timetable timetable2 = new Timetable();
        timetable2.setDoctor(doctor);
        timetable2.setDate(LocalDate.of(2018, 12, 2));
        timetable2.setTimeFrom(LocalTime.of(8, 0));
        timetable2.setTimeTo(LocalTime.of(15, 0));
        timetableDao.save(timetable2);

        List<Timetable> today = timetableDao.findTimetablesByDoctorAndDate(doctor, LocalDate.of(2018, 12, 1));
        List<Timetable> nextDays = timetableDao.findTimetablesByDoctorAndDateAfter(doctor, LocalDate.of(2018, 12, 1));

        Preconditions.checkState(
                today.size() == 1
        );

        Preconditions.checkState(
                nextDays.size() == 1
        );

    }

    @Test
    public void T11UserServiceTest() {
        User user = new User();
        user.setActive(true);
        user.setUsername("patient1");
        user.setPassword("pass");
        user.setEmail("email@email.com");
        user.setFirstName("patient1");
        user.setLastName("patient1");
        Role role = userService.findRole(Role.ROLE_PATIENT);
        user.setRole(role);
        user = userService.createUser(user);
        System.out.println("Created user: " + user.getUserId());

        Preconditions.checkState(
                userService.findUserByUsername("patient1").getFirstName().equals("patient1")
        );

        user.setRole(userService.findRole(Role.ROLE_RECEPTIONIST));
        user = userService.updateUser(user);

        System.out.println("Updated user: " + user.getUserId());

        boolean check = false;

        try {
            userService.createUser(user);
        } catch (IllegalArgumentException e) {
            check = true;
        }

        Preconditions.checkState(
                check == true
        );

        check = false;

        try {
            User user1 = userService.findUserByUsername("patient1");
            user1.setUsername("void");
            Preconditions.checkState(
                    userService.findUserByUsername("void") == null
            );
            userService.deleteUser(user1);
        } catch (IllegalArgumentException e) {
            check = true;
        }

        Preconditions.checkState(
                check == true
        );


        Preconditions.checkState(
                userService.findUserByUsername("patient1").getRole().getName().equals(Role.ROLE_RECEPTIONIST)
        );

        userService.deleteUser(user);
        Preconditions.checkState(
                userService.findUserByUsername("patient1") == null
        );
    }

    @Test
    public void T12PatientServiceTest() {
        User user = new User();
        user.setActive(true);
        user.setUsername("patient1");
        user.setPassword("pass");
        user.setEmail("email@email.com");
        user.setFirstName("patient1");
        user.setLastName("patient1");
        Role role = userService.findRole(Role.ROLE_PATIENT);
        user.setRole(role);
        user = userService.createUser(user);

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setBirthNumber("132");
        Address address = new Address();
        address.setCity("Praha");
        address.setNumber("25");
        address.setStreet("Vanickova");
        address.setZipCode("16000");
        patient.setAddress(address);
        patient.setInsurance("insurance");

        boolean check = false;

        try {
            patientService.createPatient(patient);
        } catch (IllegalArgumentException e) {
            check = true;
        }

        Preconditions.checkState(
                check == true
        );

        userService.deleteUser(user);
        patientService.createPatient(patient);
        Integer userId = userService.findUserByUsername("patient1").getUserId();

        Preconditions.checkState(
                patientService.findPatientByUsername("patient1").getUser().getUserId().equals(userId)
        );

        Patient p1 = patientService.findPatientByUsername("patient1");
        Integer uid = p1.getUser().getUserId();
        Integer pid = p1.getPatientId();
        Address a = p1.getAddress();
        a.setCity("Brno");
        patient.setAddress(a);
        patientService.updatePatient(p1);

        Preconditions.checkState(
                patientService.findPatientByUsername("patient1").getAddress().getCity().equals("Brno")
        );

        Preconditions.checkState(
                addressDao.findAll().size() == 1 && addressDao.findAll().get(0).getCity().equals("Brno")
        );

        Preconditions.checkState(
                patientService.findPatientByUsername("patient1").getPatientId().equals(pid)
        );

        Preconditions.checkState(
                patientService.findPatientByUsername("patient1").getUser().getUserId().equals(uid)
        );

        User u = p1.getUser();
        u.setFirstName("Patient01");
        p1.setUser(u);
        patientService.updatePatient(p1);

        Preconditions.checkState(
                patientService.findPatientByUsername("patient1").getUser().getFirstName().equals("Patient01")
        );

        Preconditions.checkState(
                userService.findUserByUsername("patient1").getFirstName().equals("Patient01")
        );

        User u2 = new User();
        u2.setActive(true);
        u2.setUsername("patient1");
        u2.setPassword("pass");
        u2.setEmail("email@email.com");
        u2.setFirstName("patient1");
        u2.setLastName("patient1");
        Role r = userService.findRole(Role.ROLE_PATIENT);
        u2.setRole(r);

        p1.setUser(u2);

        check = false;

        try {
            patientService.updatePatient(p1);
        } catch (IllegalArgumentException e) {
            check = true;
        }

        Preconditions.checkState(
                check == true
        );

        p1 = patientService.findPatientByUsername("patient1");
        patientService.deletePatient(p1);

        Preconditions.checkState(
                userService.findUserByUsername("patient1") == null
        );

        // RESERVATIONS
        User dUser = new User();
        dUser.setActive(true);
        dUser.setUsername("doctor1");
        dUser.setPassword("pass");
        dUser.setEmail("email@email.com");
        dUser.setFirstName("doctor1");
        dUser.setLastName("doctor1");
        Role dRole = userService.findRole(Role.ROLE_DOCTOR);
        dUser.setRole(dRole);
        Department dDep = departmentService.findDepartment("dep1");
        Preconditions.checkState(
                dDep.getName().equals("dep1")
        );
        Examination examination = departmentService.findExaminations(dDep).get(0);
        Doctor doctor = new Doctor();
        doctor.setUser(dUser);
        doctor.setDepartment(dDep);
        doctorService.createDoctor(doctor);
        p1 = patientService.createPatient(p1);

        Reservation r1 = new Reservation();
        r1.setPatient(p1);
        r1.setDoctor(doctor);
        r1.setState(Reservation.STATE_UNCONFIRMED);
        r1.setDate(LocalDate.of(2018, 12, 02));
        r1.setExamination(examination);
        r1.setTimeFrom(LocalTime.of(9,15));
        r1.setTimeTo(LocalTime.of(9,30));
        reservationService.createReservation(r1);

        Reservation r2 = new Reservation();
        r2.setPatient(p1);
        r2.setDoctor(doctor);
        r2.setState(Reservation.STATE_UNCONFIRMED);
        r2.setDate(LocalDate.of(2018, 12, 02));
        r2.setExamination(examination);
        r2.setTimeFrom(LocalTime.of(10,15));
        r2.setTimeTo(LocalTime.of(10,30));
        reservationService.createReservation(r2);

        Reservation r3 = new Reservation();
        r3.setPatient(p1);
        r3.setDoctor(doctor);
        r3.setState(Reservation.STATE_UNCONFIRMED);
        r3.setDate(LocalDate.of(2018, 12, 03));
        r3.setExamination(examination);
        r3.setTimeFrom(LocalTime.of(9,15));
        r3.setTimeTo(LocalTime.of(9,30));
        reservationService.createReservation(r3);

        Preconditions.checkState(
                patientService.findReservationsSince(p1, LocalDate.of(2018,12,02)).size() == 3
        );
    }

}
