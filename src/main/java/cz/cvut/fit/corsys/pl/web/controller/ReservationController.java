package cz.cvut.fit.corsys.pl.web.controller;


import cz.cvut.fit.corsys.CorsysApplication;
import cz.cvut.fit.corsys.bl.service.DepartmentService;
import cz.cvut.fit.corsys.bl.service.DoctorService;
import cz.cvut.fit.corsys.bl.service.PatientService;
import cz.cvut.fit.corsys.bl.service.ReservationService;
import cz.cvut.fit.corsys.dl.entity.Reservation;
import cz.cvut.fit.corsys.dl.entity.Timetable;
import cz.cvut.fit.corsys.pl.web.command.CreateReservationCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ReservationController {


    private static final Logger LOG = LoggerFactory.getLogger(CorsysApplication.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PatientService patientService;

    private CreateReservationCommand res = new CreateReservationCommand();

    //Select DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationDep", method = RequestMethod.GET)
    public void prepareReservationDepartment(Model model) {
        model.addAttribute("departments", departmentService.findAllDepartments());
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DEPARTMENT to EXAMINATION
    @RequestMapping(value = "/receptionist/createReservationDep", method = RequestMethod.POST)
    public String reservationDepartmentSubmit(@Valid CreateReservationCommand reservation, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("departments", departmentService.findAllDepartments());
            return "receptionist/createReservationDep";
        }

        if (reservation.getPatientUsername()==null || patientService.findPatientByUsername(reservation.getPatientUsername())==null) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("wrongUsername", true);
            model.addAttribute("departments", departmentService.findAllDepartments());
            return "receptionist/createReservationDep";
        }
        model.addAttribute("wrongUsername", false);
        res.setDepartmentId(reservation.getDepartmentId());
        res.setPatientUsername(reservation.getPatientUsername());
        return "redirect:/receptionist/createReservationExamination";
    }

    //Select EXAMINATION based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.GET)
    public void prepareReservationExamination(Model model) {
        model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment(res.getDepartmentId())));
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DEPARTMENT and EXAMINATION to DOCTOR
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.POST)
    public String reservationExaminationSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("command", command);
            model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment(res.getDepartmentId())));
            return "receptionist/createReservationExamination";
        }

        res.setExaminationId(command.getExaminationId());
        return "redirect:/receptionist/createReservationDoc";
    }

    //Select DOCTOR based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.GET)
    public void prepareReservationDoctor(Model model) {
        model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment(res.getDepartmentId())));
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DEPARTMENT and EXAMINATION to DOCTOR
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.POST)
    public String reservationDoctorSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("command", command);
            return "receptionist/createReservationDoc";
        }

        res.setDoctorId(command.getDoctorId());
        return "redirect:/receptionist/createReservationDate";
    }

    //Select DATE
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.GET)
    public void prepareReservationDate(Model model) {
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DATE,DOCTOR,EXAMINATION,DEPARTMENT to CHOOSE TIME
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.POST)
    public String reservationDateSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "receptionist/createReservationDate";
        }

        res.setDate(command.getDate());
        return "redirect:/receptionist/createReservationTime";
    }

    //Select DOCTOR based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.GET)
    public void prepareReservationTime(Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate resLocalDate = LocalDate.parse(res.getDate(), formatter);
        List<LocalTime> times = reservationService.findFreeTerms(resLocalDate,
                doctorService.getDoctor(res.getDoctorId()),
                departmentService.getExamination(res.getExaminationId()));

        LOG.info(res.toString());

        if (times.isEmpty()) {
            model.addAttribute("empty", true);
        } else {
            model.addAttribute("times", times);
            model.addAttribute("empty", false);
        }
        model.addAttribute("command", new CreateReservationCommand());
    }

    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.POST)
    public String reservationTimeSubmit(@Valid CreateReservationCommand command, BindingResult result,
                                        Model model, RedirectAttributes redirectAttributes) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate resLocalDate = LocalDate.parse(res.getDate(), formatter);

        Reservation resNew = new Reservation();
        resNew.setDate(resLocalDate);
        resNew.setDescription(res.getDescription());
        resNew.setDoctor(doctorService.getDoctor(res.getDoctorId()));
        resNew.setExamination(departmentService.getExamination(res.getExaminationId()));
        resNew.setPatient(patientService.findPatientByUsername(res.getPatientUsername()));
        resNew.setState(Reservation.STATE_CONFIRMED);
        resNew.setTimeFrom(res.getTimeFrom());

        int length = departmentService.getExamination(res.getExaminationId()).getLength();
        LocalTime timeTo = command.getTimeFrom().plusMinutes(length * Timetable.TIME_DIV);
        resNew.setTimeFrom(command.getTimeFrom());
        resNew.setTimeTo(timeTo);
        /*
        TODO validate reservation (not null attributes)
         */
        reservationService.createReservation(resNew);
        redirectAttributes.addAttribute("resSuccess", true);
        return "redirect:/receptionist";
    }

    @RequestMapping(value = "/receptionist/listReservations", method = RequestMethod.GET)
    public void listAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
    }
}
