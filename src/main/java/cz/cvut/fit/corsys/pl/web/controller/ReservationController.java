package cz.cvut.fit.corsys.pl.web.controller;


import cz.cvut.fit.corsys.bl.service.DepartmentService;
import cz.cvut.fit.corsys.bl.service.DoctorService;
import cz.cvut.fit.corsys.bl.service.TimetableService;
import cz.cvut.fit.corsys.bl.service.UserService;
import cz.cvut.fit.corsys.dl.entity.Department;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Examination;
import cz.cvut.fit.corsys.dl.entity.Reservation;
import cz.cvut.fit.corsys.pl.web.command.CreateDoctorCommand;
import cz.cvut.fit.corsys.pl.web.command.CreateReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;

//    @Autowired
//    private ReservationService reservationService;

//    @Autowired
//    private ExaminationService examinationService;


    //Select DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationDepartment", method = RequestMethod.GET)
    public void prepareReservationDepartment(Model model) {
        model.addAttribute("departments", departmentService.findAllDepartments());
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DEPARTMENT to EXAMINATION
    @RequestMapping(value = "/receptionist/createReservationDepartment", method = RequestMethod.POST)
    public String reservationDepartmentSubmit(@Valid CreateReservationCommand reservation, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("command",reservation);
        return "redirect:/receptionist/createReservationExamination";
    }

    //Select EXAMINATION based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.GET)
    public void prepareReservationExamination(Model model,@ModelAttribute("command") CreateReservationCommand reservation) {
        model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment(reservation.getDepartmentId())));
        model.addAttribute("command", reservation);
    }

    //Redirect selected DEPARTMENT and EXAMINATION to DOCTOR
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.POST)
    public String reservationExaminationSubmit(@Valid CreateReservationCommand reservation, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("command",reservation);
        return "redirect:/receptionist/createReservationDoctor";
    }

    //Select DOCTOR based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationDoctor", method = RequestMethod.GET)
    public void prepareReservationDoctor(Model model,@ModelAttribute("command") CreateReservationCommand reservation) {
        model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment(reservation.getDepartmentId())));
        model.addAttribute("command", reservation);
    }

    //Redirect selected DEPARTMENT and EXAMINATION to DOCTOR
    @RequestMapping(value = "/receptionist/createReservationDoctor", method = RequestMethod.POST)
    public String reservationDoctorSubmit(@Valid CreateReservationCommand reservation, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("command",reservation);
        return "redirect:/receptionist/createReservationDate";
    }

    //Select DATE
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.GET)
    public void prepareReservationDate(Model model,@ModelAttribute("command") CreateReservationCommand reservation) {
        model.addAttribute("command", reservation);
    }

    //Redirect selected DATE,DOCTOR,EXAMINATION,DEPARTMENT to CHOOSE TIME
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.POST)
    public String reservationDateSubmit(@Valid CreateReservationCommand reservation, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("command",reservation);
        return "redirect:/receptionist/createReservationTime";
    }

    //Select DOCTOR based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.GET)
    public void prepareReservationTime(Model model,@ModelAttribute("command") CreateReservationCommand reservation) {
//        model.addAttribute("times", reservationService().findFreeTerms(/*DATE*/ reservation.getDate(),
//                                                                          /*DOCTOR*/ doctorService.getDoctor(reservation.getDoctorId()),
//                                                                          /*EXAMINATION*/ examinationService.getExamination(reservation.getExaminationId())
//        ));
        model.addAttribute("command", reservation);
    }

    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.POST)
    public String reservationTimeSubmit(@Valid CreateReservationCommand reservation, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("command",reservation);
/*
        Reservation res = new Reservation();
        res.setDate(reservation.getDate());
        res.setDescription(reservation.getDescription());
        res.setDoctor(doctorService.getDoctor(reservation.getDoctorId()));
        res.setExamination(examinationService.getExamination(reservation.getExaminationId());
        res.setPatient(patientService.findPatientByUsername(reservation.getPatient()));
        res.setState("CONFIRMED");
        res.setTimeFrom(reservation.getTimeFrom());
        res.setTimeTo(reservation.getTimeTo());
*/
        /*
        TODO validate reservation (not null attributes)
         */
//        reservationService().createReservation(res);

        return "redirect:/receptionist/receptionist";
    }
}
