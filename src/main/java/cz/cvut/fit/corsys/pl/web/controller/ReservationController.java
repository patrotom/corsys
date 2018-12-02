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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class ReservationController {


    private static final Logger LOG = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PatientService patientService;


    /**
     * Prepare all departments for POST method
     *
     * @param model MVC model
     */
    @RequestMapping(value = "/receptionist/createReservationDep", method = RequestMethod.GET)
    public void prepareReservationDepartment(Model model) {
        model.addAttribute("departments", departmentService.findAllDepartments());
        model.addAttribute("command", new CreateReservationCommand());
    }

    /**
     * Validate patient username exists, save chosen username and department
     *
     * @param reservation MVC command
     * @param result
     * @param model       MVC model
     * @param session     MVC HttpSession
     * @return page displaying departments
     */
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
        return "redirect:/receptionist/createReservationExamination";
    }

    /**
     * Prepare examinations based on chosen department
     *
     * @param model MVC model
     * @param session MVC HttpSession
     * @return page displaying examinations
     */
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.GET)
    public String prepareReservationExamination(Model model, HttpSession session) {

        if (session.getAttribute("depId")==null) return "receptionist/receptionist";

        model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
        model.addAttribute("command", new CreateReservationCommand());

        return "receptionist/createReservationExamination";
    }

    /**
     * Validate and save chosen examination
     *
     * @param command            MVC command
     * @param result
     * @param model              MVC model
     * @param session            MVC HttpSession
     * @return redirect to choose a doctor
     */
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.POST)
    public String reservationExaminationSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors() || command.getExaminationId() == null) {
            model.addAttribute("command", command);
            model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
            return "receptionist/createReservationExamination";
        }

        session.setAttribute("exId", command.getExaminationId());
        return "redirect:/receptionist/createReservationDoc";
    }

    /**
     * Prepare doctors based on the department
     *
     * @param model MVC model
     * @param session MVC HttpSession
     * @return page displaying doctors
     */
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.GET)
    public String prepareReservationDoctor(Model model, HttpSession session) {
        if (session.getAttribute("depId")==null) return "receptionist/receptionist";
        model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
        model.addAttribute("command", new CreateReservationCommand());

        return "receptionist/createReservationDoc";
    }

    /**
     * Validate and save chosen doctor
     *
     * @param command MVC command
     * @param result
     * @param model   MVC model
     * @param session MVC HttpSession
     * @return redirect to pick a date
     */
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.POST)
    public String reservationDoctorSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors() || command.getDoctorId() == null) {
            model.addAttribute("command", command);
            model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment((Integer)session.getAttribute("depId"))));
            return "receptionist/createReservationDoc";
        }

        session.setAttribute("docId", command.getDoctorId());
        return "redirect:/receptionist/createReservationDate";
    }

    /**
     * Select date of reservation
     *
     * @param model MVC model
     * @param session MVC HttpSession
     * @return page displaying datepicker
     */
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.GET)
    public String prepareReservationDate(Model model, HttpSession session) {
        if (session.getAttribute("depId")==null
                || session.getAttribute("docId")==null
                || session.getAttribute("exId")==null
                || session.getAttribute("patientUsername")==null) return "receptionist/receptionist";
        model.addAttribute("command", new CreateReservationCommand());

        return "receptionist/createReservationDate";
    }

    /**
     * Validate date
     *
     * @param command MVC command
     * @param result
     * @param model   MVC model
     * @param session   MVC HttpSession
     * @return redirect to pick a time
     */
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

    /**
     * Prepare timeFrom attribute based on doctor's timetable, examination and date
     *
     * @param model MVC model
     * @param session MVC HttpSession
     * @return page displaying reservation times
     */
    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.GET)
    public String prepareReservationTime(Model model, HttpSession session) {

        if (session.getAttribute("depId")==null
                || session.getAttribute("docId")==null
                || session.getAttribute("exId")==null
                || session.getAttribute("patientUsername")==null
                || session.getAttribute("date")==null) return "receptionist/receptionist";

        addTime(model, session);
        model.addAttribute("command", new CreateReservationCommand());
        return "receptionist/createReservationTime";
    }


    /**
     * Validate chosen time, compute timeTo, create reservation and save
     *
     * @param command            MVC command
     * @param result
     * @param model              MVC model
     * @param session            MVC HttpSession
     * @return redirect to receptionist homepage
     */
    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.POST)
    public String reservationTimeSubmit(@Valid CreateReservationCommand command, BindingResult result,
                                        Model model, HttpSession session) {

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
            session.setAttribute("resSuccess", false);
            return "redirect:/receptionist";
        }
        session.setAttribute("resSuccess", true);
        return "redirect:/receptionist";
    }

    /**
     * Show all reservations
     *
     * @param model MVC model
     */
    @RequestMapping(value = "/receptionist/listReservations", method = RequestMethod.GET)
    public void listAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
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
}
