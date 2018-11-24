package cz.cvut.fit.corsys.pl.web.controller;


import cz.cvut.fit.corsys.bl.service.*;
import cz.cvut.fit.corsys.dl.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/receptionist/createReservation", method = RequestMethod.GET)
    public String prepareCreateReservation(Model model) {
        model.addAttribute("Departments", departmentService.findAllDepartments());
        //model.addAttribute("Examinations", departmentService.findExaminations());
        return "receptionist/createReservation";
    }
}
