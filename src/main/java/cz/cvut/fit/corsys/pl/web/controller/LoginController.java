package cz.cvut.fit.corsys.pl.web.controller;

import cz.cvut.fit.corsys.bl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    /**
     * Displays login page view
     * @param wrongPass - request parameter, is set when user enters wrong password
     * @param model Model
     * @return path to login page view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(@RequestParam(name = "wrongPass", required = false) Boolean wrongPass, Model model) {
        if (wrongPass != null && wrongPass) {
            model.addAttribute("wrongPass", "Zlé heslo!!!");
        }
        return "login";
    }

    /**
     * Displays home page based on user role
     * @param model Model
     * @return path to view based on role
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        String role = this.userService.getLoggedUser().getRole().getName();
        model.addAttribute("userRole", role);
        if (role.equals("RECEPTIONIST")) {
            return "redirect:/receptionist";
        }
        return "welcome";
    }

    /**
     *  Displays home page of receptionist
     * @param model Model
     * @param session HttpSession
     * @return path to receptionist view
     */
    @RequestMapping(value = "/receptionist", method = RequestMethod.GET)
    public String welcomeReceptionist(Model model, HttpSession session) {
        String name = userService.getLoggedUser().getFirstName() + " " + userService.getLoggedUser().getLastName();
        session.setAttribute("userFullName", name);
        model.addAttribute("userFullName", name);
        model.addAttribute("resSuccess", session.getAttribute("resSuccess"));
        model.addAttribute("patSuccess", session.getAttribute("patSuccess"));
        session.setAttribute("resSuccess", null);
        session.setAttribute("patSuccess", null);
        return "receptionist/receptionist";
    }


}

