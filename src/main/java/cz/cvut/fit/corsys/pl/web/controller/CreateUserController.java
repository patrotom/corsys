package cz.cvut.fit.corsys.pl.web.controller;

import cz.cvut.fit.corsys.bl.auth.HashUtil;
import cz.cvut.fit.corsys.bl.service.DepartmentService;
import cz.cvut.fit.corsys.bl.service.DoctorService;
import cz.cvut.fit.corsys.bl.service.PatientService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.entity.*;
import cz.cvut.fit.corsys.pl.web.command.AbstractCreateUserCommand;
import cz.cvut.fit.corsys.pl.web.command.CreateDoctorCommand;
import cz.cvut.fit.corsys.pl.web.command.CreatePatientCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CreateUserController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    /**
     * Displays form for creating a doctor
     * @param model Model
     */
    @RequestMapping(value = "/admin/createDoctor", method = RequestMethod.GET)
    public void createUserDoctorPrepare(Model model) {
        model.addAttribute("departments", this.departmentService.findAllDepartments());
        model.addAttribute("command", new CreateDoctorCommand());
    }

    /**
     * Validate and save created Doctor
     * @param doctor - object for holding user input of create doctor form
     *
     * @param result BindingResult
     * @param model Model
     * @return the same view if the form has errors, otherwise redirection to doctor list view
     */
    @RequestMapping(value = "/admin/createDoctor", method = RequestMethod.POST)
    public String createUserDoctorSubmit(@Valid CreateDoctorCommand doctor, BindingResult result, Model model) {
        this.validateUser(result, doctor, model);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("departments", this.departmentService.findAllDepartments());
            model.addAttribute("command", doctor);
            return "admin/createDoctor";
        }

        Role role = this.userService.findRole(Role.ROLE_DOCTOR);
        Department dep = this.departmentService.getDepartment(doctor.getDepartmentId());
        User user = this.createUserObject(doctor, role);
        Doctor doc = new Doctor();
        doc.setDepartment(dep);
        doc.setUser(user);
        this.doctorService.createDoctor(doc);
        return "redirect:/admin/doctorList";
    }

    /**
     * Displays a form for creating a patient
     *
     * @param model Model
     */
    @RequestMapping(value = "/receptionist/createPatient", method = RequestMethod.GET)
    public void createUserPatientPrepare(Model model) {
        model.addAttribute("command", new CreatePatientCommand());
    }

    /**
     * Validate and save created Patient
     *
     * @param patient - object for holding user input values of create patient form
     * @param result BindingResult
     * @param model Model
     * @param session HttpSession
     * @return the same view if the form has errors, otherwise redirection to receptionist home page
     */
    @RequestMapping(value = "/receptionist/createPatient", method = RequestMethod.POST)
    public String createUserPatientSubmit(@Valid CreatePatientCommand patient, BindingResult result,
                                          Model model, HttpSession session) {
        this.validateUser(result, patient, model);
        if (result.hasErrors()) {
             model.addAttribute("errors", result.getFieldErrors());
            model.addAttribute("command", patient);
            return "/receptionist/createPatient";
        }

        Role role = this.userService.findRole(Role.ROLE_PATIENT);
        User user = this.createUserObject(patient, role);

        Address address = new Address();
        address.setCity(patient.getCity());
        address.setNumber(patient.getNumber());
        address.setStreet(patient.getStreet());
        address.setZipCode(patient.getZipCode());

        Patient pat = new Patient();
        pat.setBirthNumber(patient.getBirthNumber());
        pat.setInsurance(patient.getInsurance());
        pat.setAddress(address);
        pat.setUser(user);

        this.patientService.createPatient(pat);
        session.setAttribute("patSuccess", true);
        return "redirect:/receptionist";
    }

    private void validateUser(BindingResult result, AbstractCreateUserCommand command, Model model) {

            if (!command.getPassword().equals(command.getPassword2())) {
                result.addError(new FieldError("form", "password2", "Heslá sa nezhodujú"));
                model.addAttribute("passFail", "Heslá sa nezhodujú");
            }
            User user = this.userService.findUserByUsername(command.getUsername());
            if (user != null) {
                result.addError(new FieldError("form", "username", "Uživateľ už existuje"));
                model.addAttribute("userFail", "Uživateľ  s daným uživateľským menom už existuje");
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
        user.setActive(true);
        return user;
    }
}
