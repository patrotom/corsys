package cz.cvut.fit.corsys.pl.web.controller;

import cz.cvut.fit.corsys.bl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(@RequestParam(name = "wrongPass", required = false) Boolean wrongPass, Model model) {
        if (wrongPass != null && wrongPass) {
            model.addAttribute("wrongPass", "Zlé heslo!!!");
        }
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        String role = this.userService.getLoggedUser().getRole().getName();
        model.addAttribute("userRole", role);
        if (role.equals("RECEPTIONIST")) {
            return "redirect:/receptionist";
        }
        return "welcome";
    }

    @GetMapping(value = "/receptionist")
    public String welcomeReceptionist(@RequestParam(name = "patSuccess", required = false) Boolean patSuccess,
                                      @RequestParam(name = "resSuccess", required = false) Boolean resSuccess,
                                      Model model, HttpSession session) {
        String name = userService.getLoggedUser().getFirstName() + " " + userService.getLoggedUser().getLastName();
        session.setAttribute("userFullName", name);
        model.addAttribute("userFullName", name);
        model.addAttribute("resSuccess", resSuccess);
        model.addAttribute("patSuccess", patSuccess);
        return "receptionist/receptionist";
    }


}

