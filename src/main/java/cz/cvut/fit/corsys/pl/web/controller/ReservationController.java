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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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


    //Select DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationDep", method = RequestMethod.GET)
    public void prepareReservationDepartment(Model model) {
        model.addAttribute("departments", departmentService.findAllDepartments());
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DEPARTMENT to EXAMINATION
    @RequestMapping(value = "/receptionist/createReservationDep", method = RequestMethod.POST)
    public String reservationDepartmentSubmit(@Valid CreateReservationCommand reservation, BindingResult result,
                                              Model model, HttpSession session) {

        if (result.hasErrors() || reservation.getDepartmentId() == null) {
            model.addAttribute("command", reservation);
            model.addAttribute("departments", departmentService.findAllDepartments());
            return "receptionist/createReservationDep";
        }

        if (reservation.getPatientUsername() == null || patientService.findPatientByUsername(reservation.getPatientUsername()) == null) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("wrongUsername", true);
            model.addAttribute("command", reservation);
            model.addAttribute("departments", departmentService.findAllDepartments());
            return "receptionist/createReservationDep";
        }
        model.addAttribute("wrongUsername", false);
        session.setAttribute("depId", reservation.getDepartmentId());
        session.setAttribute("patientUsername", reservation.getPatientUsername());
        //res.setDepartmentId(reservation.getDepartmentId());
        //res.setPatientUsername(reservation.getPatientUsername());
        return "redirect:/receptionist/createReservationExamination";
    }

    //Select EXAMINATION based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.GET)
    public void prepareReservationExamination(Model model, HttpSession session) {
        model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DEPARTMENT and EXAMINATION to DOCTOR
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.POST)
    public String reservationExaminationSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors() || command.getExaminationId() == null) {
            model.addAttribute("command", command);
            model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
            return "receptionist/createReservationExamination";
        }

        //res.setExaminationId(command.getExaminationId());
        session.setAttribute("exId", command.getExaminationId());
        return "redirect:/receptionist/createReservationDoc";
    }

    //Select DOCTOR based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.GET)
    public void prepareReservationDoctor(Model model, HttpSession session) {
        model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DEPARTMENT and EXAMINATION to DOCTOR
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.POST)
    public String reservationDoctorSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors() || command.getDoctorId() == null) {
            model.addAttribute("command", command);
            model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
            return "receptionist/createReservationDoc";
        }

        session.setAttribute("docId", command.getDoctorId());
        //res.setDoctorId(command.getDoctorId());
        return "redirect:/receptionist/createReservationDate";
    }

    //Select DATE
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.GET)
    public void prepareReservationDate(Model model) {
        model.addAttribute("command", new CreateReservationCommand());
    }

    //Redirect selected DATE,DOCTOR,EXAMINATION,DEPARTMENT to CHOOSE TIME
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.POST)
    public String reservationDateSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors() || command.getDate().equals("")) {
            model.addAttribute("command", command);
            return "receptionist/createReservationDate";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate resLocalDate;
        try {
            resLocalDate = LocalDate.parse(command.getDate(), formatter);
        } catch (DateTimeParseException e) {
            model.addAttribute("command", command);
            return "receptionist/createReservationDate";
        }
        session.setAttribute("date", resLocalDate);
        return "redirect:/receptionist/createReservationTime";
    }

    //Select DOCTOR based on DEPARTMENT
    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.GET)
    public void prepareReservationTime(Model model, HttpSession session) {

        addTime(model, session);
        model.addAttribute("command", new CreateReservationCommand());
    }

    private boolean addTime(Model model, HttpSession session) {

        List<LocalTime> times = reservationService.findFreeTerms((LocalDate)session.getAttribute("date"),
                doctorService.getDoctor((Integer)session.getAttribute("docId")),
                departmentService.getExamination((Integer)session.getAttribute("exId")));

        if (times.isEmpty()) {
            model.addAttribute("empty", true);
        } else {
            model.addAttribute("times", times);
            model.addAttribute("empty", false);
        }
        return true;
    }

    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.POST)
    public String reservationTimeSubmit(@Valid CreateReservationCommand command, BindingResult result,
                                        Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        if (result.hasErrors() || command.getTimeFrom() == null) {
            model.addAttribute("command", command);
            addTime(model, session);
            return "receptionist/createReservationTime";
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:MM");

        Reservation resNew = new Reservation();
        resNew.setDate((LocalDate)session.getAttribute("date"));
        resNew.setDoctor(doctorService.getDoctor((Integer)session.getAttribute("docId")));
        resNew.setExamination(departmentService.getExamination((Integer)session.getAttribute("exId")));
        resNew.setPatient(patientService.findPatientByUsername((String)session.getAttribute("patientUsername")));
        resNew.setState(Reservation.STATE_CONFIRMED);

        int length = departmentService.getExamination((Integer)session.getAttribute("exId")).getLength();
        LocalTime timeFrom = LocalTime.parse(command.getTimeFrom(), timeFormatter);
        resNew.setTimeFrom(timeFrom);
        LocalTime timeTo = timeFrom.plusMinutes(length * Timetable.TIME_DIV);
        resNew.setTimeTo(timeTo);

        try {
            reservationService.createReservation(resNew);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addAttribute("resSuccess", false);
            return "redirect:/receptionist";
        }
        redirectAttributes.addAttribute("resSuccess", true);
        return "redirect:/receptionist";
    }

    @RequestMapping(value = "/receptionist/listReservations", method = RequestMethod.GET)
    public void listAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
    }
}
