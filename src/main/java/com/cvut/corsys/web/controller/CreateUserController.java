package com.cvut.corsys.web.controller;

import com.cvut.corsys.auth.HashUtil;
import com.cvut.corsys.entity.Department;
import com.cvut.corsys.entity.Doctor;
import com.cvut.corsys.entity.Role;
import com.cvut.corsys.entity.User;
import com.cvut.corsys.service.DepartmentService;
import com.cvut.corsys.service.UserService;
import com.cvut.corsys.web.command.AbstractCreateUserCommand;
import com.cvut.corsys.web.command.CreateDoctorCommand;
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

    @RequestMapping(value = "/admin/createDoctor", method = RequestMethod.GET)
    public void createUserAdminPrepare(Model model) {
        model.addAttribute("departments", this.departmentService.findAllDepartments());
        model.addAttribute("command", new CreateDoctorCommand());
    }

    @RequestMapping(value = "/admin/createDoctor", method = RequestMethod.POST)
    public String createUserAdminSubmit(@Valid CreateDoctorCommand doctor, BindingResult result, Model model) {
        this.validateUser(result, doctor);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("departments", this.departmentService.findAllDepartments());
            model.addAttribute("command", doctor);
            System.out.println(result);
            return "admin/createDoctor";
        }
        // Nacteni role
        Role role = this.userService.getRole("DOCTOR");
        // Nacteni dep
        Department dep = this.departmentService.getDeparment(doctor.getDepartmentId());
        // Naplneni spolecneho kodu
        User user = this.createUserObject(doctor, role);
        // Naplneni doktora
        Doctor doc = new Doctor();
        doc.setDepartment(dep);
        doc.setUser(user);
        // Vytvorim a jdu pryc
        this.userService.createDoctor(doc);
        return "redirect:/admin/doctorList";
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
