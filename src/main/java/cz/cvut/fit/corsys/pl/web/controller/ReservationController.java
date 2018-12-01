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
     * @return
     */
    @RequestMapping(value = "/receptionist/createReservationDep", method = RequestMethod.POST)
    public String reservationDepartmentSubmit(@Valid CreateReservationCommand reservation, BindingResult result, Model model) {

        if (result.hasErrors() || reservation.getDepartmentId() == null) {
            model.addAttribute("command", reservation);
            model.addAttribute("departments", departmentService.findAllDepartments());
            return "receptionist/createReservationDep";
        }

        if (reservation.getPatientUsername() == null || patientService.findPatientByUsername(reservation.getPatientUsername()) == null) {
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

    /**
     * Prepare examinations based on chosen department
     *
     * @param model MVC model
     */
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.GET)
    public void prepareReservationExamination(Model model) {
        model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment(res.getDepartmentId())));
        model.addAttribute("command", new CreateReservationCommand());
    }

    /**
     * Validate and save chosen examination
     *
     * @param command            MVC command
     * @param result
     * @param model              MVC model
     * @return redirect to choose a doctor
     */
    @RequestMapping(value = "/receptionist/createReservationExamination", method = RequestMethod.POST)
    public String reservationExaminationSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model) {

        if (result.hasErrors() || command.getExaminationId() == null) {
            model.addAttribute("command", command);
            model.addAttribute("examinations", departmentService.findExaminations(departmentService.getDepartment(res.getDepartmentId())));
            return "receptionist/createReservationExamination";
        }

        res.setExaminationId(command.getExaminationId());
        return "redirect:/receptionist/createReservationDoc";
    }

    /**
     * Prepare doctors based on the department
     *
     * @param model MVC model
     */
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.GET)
    public void prepareReservationDoctor(Model model) {
        model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment(res.getDepartmentId())));
        model.addAttribute("command", new CreateReservationCommand());
    }

    /**
     * Validate and save chosen doctor
     *
     * @param command MVC command
     * @param result
     * @param model   MVC model
     * @return redirect to pick a date
     */
    @RequestMapping(value = "/receptionist/createReservationDoc", method = RequestMethod.POST)
    public String reservationDoctorSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model) {
        if (result.hasErrors() || command.getDoctorId() == null) {
            model.addAttribute("command", command);
            model.addAttribute("doctors", departmentService.findDoctors(departmentService.getDepartment(res.getDepartmentId())));
            return "receptionist/createReservationDoc";
        }

        res.setDoctorId(command.getDoctorId());
        return "redirect:/receptionist/createReservationDate";
    }

    /**
     * Select date of reservation
     *
     * @param model MVC model
     */
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.GET)
    public void prepareReservationDate(Model model) {
        model.addAttribute("command", new CreateReservationCommand());
    }

    /**
     * Validate date
     *
     * @param command MVC command
     * @param result
     * @param model   MVC model
     * @return redirect to pick a time
     */
    @RequestMapping(value = "/receptionist/createReservationDate", method = RequestMethod.POST)
    public String reservationDateSubmit(@Valid CreateReservationCommand command, BindingResult result, Model model) {
        if (result.hasErrors() || command.getDate().equals("")) {
            model.addAttribute("command", command);
            return "receptionist/createReservationDate";
        }

        res.setDate(command.getDate());
        return "redirect:/receptionist/createReservationTime";
    }

    /**
     * Prepare timeFrom attribute based on doctor's timetable, examination and date
     *
     * @param model MVC model
     */
    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.GET)
    public void prepareReservationTime(Model model) {

        addTime(model);
        model.addAttribute("command", new CreateReservationCommand());
    }

    /**
     * Validate chosen time, compute timeTo, create reservation and save
     *
     * @param command            MVC command
     * @param result
     * @param model              MVC model
     * @param redirectAttributes redirect message of creation
     * @return
     */
    @RequestMapping(value = "/receptionist/createReservationTime", method = RequestMethod.POST)
    public String reservationTimeSubmit(@Valid CreateReservationCommand command, BindingResult result,
                                        Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors() || command.getTimeFrom() == null) {
            model.addAttribute("command", command);
            addTime(model);
            return "receptionist/createReservationTime";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:MM");
        LocalDate resLocalDate = LocalDate.parse(res.getDate(), formatter);

        Reservation resNew = new Reservation();
        resNew.setDate(resLocalDate);
        resNew.setDescription(res.getDescription());
        resNew.setDoctor(doctorService.getDoctor(res.getDoctorId()));
        resNew.setExamination(departmentService.getExamination(res.getExaminationId()));
        resNew.setPatient(patientService.findPatientByUsername(res.getPatientUsername()));
        resNew.setState(Reservation.STATE_CONFIRMED);

        int length = departmentService.getExamination(res.getExaminationId()).getLength();
        LocalTime timeFrom = LocalTime.parse(command.getTimeFrom(), timeFormatter);
        resNew.setTimeFrom(timeFrom);
        LocalTime timeTo = timeFrom.plusMinutes(length * Timetable.TIME_DIV);
        resNew.setTimeTo(timeTo);

        reservationService.createReservation(resNew);
        redirectAttributes.addAttribute("resSuccess", true);
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

    private void addTime(Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate resLocalDate = LocalDate.parse(res.getDate(), formatter);
        List<LocalTime> times = reservationService.findFreeTerms(resLocalDate,
                doctorService.getDoctor(res.getDoctorId()),
                departmentService.getExamination(res.getExaminationId()));

        if (times.isEmpty()) {
            model.addAttribute("empty", true);
        } else {
            model.addAttribute("times", times);
            model.addAttribute("empty", false);
        }
    }
}
