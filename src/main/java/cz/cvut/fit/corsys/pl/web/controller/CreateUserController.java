package cz.cvut.fit.corsys.pl.web.controller;

import cz.cvut.fit.corsys.bl.auth.HashUtil;
import cz.cvut.fit.corsys.bl.service.DepartmentService;
import cz.cvut.fit.corsys.bl.service.DoctorService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.entity.*;
import cz.cvut.fit.corsys.pl.web.command.AbstractCreateUserCommand;
import cz.cvut.fit.corsys.pl.web.command.CreateDoctorCommand;
import cz.cvut.fit.corsys.pl.web.command.CreatePatientCommand;
import cz.cvut.fit.corsys.pl.web.command.CreateReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CreateUserController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    //prepare create Doctor
    @RequestMapping(value = "/admin/createDoctor", method = RequestMethod.GET)
    public void createUserAdminPrepare(Model model) {
        model.addAttribute("departments", this.departmentService.findAllDepartments());
        model.addAttribute("command", new CreateDoctorCommand());
    }

    //submit created Doctor
    @RequestMapping(value = "/admin/createDoctor", method = RequestMethod.POST)
    public String createUserAdminSubmit(@Valid CreateDoctorCommand doctor, BindingResult result, Model model) {
        this.validateUser(result, doctor);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("departments", this.departmentService.findAllDepartments());
            model.addAttribute("command", doctor);
            return "admin/createDoctor";
        }
        // Nacteni role
        Role role = this.userService.findRole("DOCTOR");
        // Nacteni dep
        Department dep = this.departmentService.getDepartment(doctor.getDepartmentId());
        // Naplneni spolecneho kodu
        User user = this.createUserObject(doctor, role);
        // Naplneni doktora
        Doctor doc = new Doctor();
        doc.setDepartment(dep);
        doc.setUser(user);
        // Vytvorim a jdu pryc
        this.doctorService.createDoctor(doc);
        return "redirect:/admin/doctorList";
    }

    //prepare create Patient
    @RequestMapping(value = "/receptionist/createPatient", method = RequestMethod.GET)
    public void createUserPatientPrepare(Model model) {
        model.addAttribute("command", new CreatePatientCommand());
    }

    //submit created Patient
    @RequestMapping(value = "/receptionist/createPatient", method = RequestMethod.POST)
    public String createUserPatientSubmit(@Valid CreatePatientCommand patient, BindingResult result, Model model) {
        this.validateUser(result, patient);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("command", patient);
            return "/receptionist/createPatient";
        }
        // Nacteni role
        Role role = this.userService.findRole("PATIENT");
        // Nacteni birth_Number
        String birth_number = patient.getBirthNumber();
        String insurance = patient.getInsurance();
        //TODO adresa

        // Naplneni spolecneho kodu
        User user = this.createUserObject(patient, role);
        // Naplneni doktora
        Patient pat = new Patient();
        pat.setBirthNumber(birth_number);
        pat.setInsurance(insurance);

        //TODO this.patientService.createPatient(pat);
        return "redirect:/receptionist";
    }

    private void validateUser(BindingResult result, AbstractCreateUserCommand command) {
        if (result.hasErrors()) {
            if (!command.getPassword().equals(command.getPassword2())) {
                result.addError(new FieldError("form", "password2", "Hesla nesúhlasia"));
            }
            User user = this.userService.findByUsername(command.getUsername());
            if (user != null)
                result.addError(new FieldError("form", "username", "Uživatel již existuje"));
        }
    }

    private User createUserObject(AbstractCreateUserCommand command, Role role) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setPassword(HashUtil.getPasswordHash(command.getPassword()));
        user.setEmail(command.getEmail());
        user.setPhone(command.getPhone());
        user.setFirstName(command.getFirstName());
        user.setLastName(command.getLastName());
        user.setRole(role);
        return user;
    }
}
