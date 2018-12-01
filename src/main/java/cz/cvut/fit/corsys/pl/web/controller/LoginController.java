package cz.cvut.fit.corsys.pl.web.controller;

import cz.cvut.fit.corsys.bl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Validate credentials
     *
     * @param wrongPass boolean flag
     * @param model MVC model
     * @return resources/templates/login page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(@RequestParam(name = "wrongPass", required = false) Boolean wrongPass, Model model) {
        if (wrongPass != null && wrongPass) {
            model.addAttribute("wrongPass", "Zlé heslo!!!");
        }
        return "login";
    }

    /**
     * Check role of user and redirect to user's role homepage
     * @param model MVC model
     * @return welcome homepage for DOCTOR,PATIENT and ADMIN roles
     *         redirect to receptionist homepage for role == receptionist
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
     * Receptionist homepage
     * @param patSuccess Redirected parameter after creation of patient
     * @param resSuccess Redirected parameter after creation of reservation
     * @param model MVC model
     * @return stay on page
     */
    @GetMapping(value = "/receptionist")
    public String welcomeReceptionist(@RequestParam(name = "patSuccess", required = false) Boolean patSuccess, @RequestParam(name = "resSuccess", required = false) Boolean resSuccess, Model model) {
        model.addAttribute("userFullName", userService.getLoggedUser().getFirstName() + " " + userService.getLoggedUser().getLastName());
        model.addAttribute("resSuccess", resSuccess);
        model.addAttribute("patSuccess", patSuccess);
        return "receptionist/receptionist";
    }


}

