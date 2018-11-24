package cz.cvut.fit.corsys.pl.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {


    @RequestMapping(value = "/receptionist/createReservation", method = RequestMethod.GET)
    public String prepareCreateReservation(Model model) {
        model.addAttribute("userFullName", "user name");
        return "receptionist/createReservation";
    }
}
