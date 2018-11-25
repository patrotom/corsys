package cz.cvut.fit.corsys.pl.web.controller;

import cz.cvut.fit.corsys.bl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam(name = "wrongPass", required = false) Boolean wrongPass, Model model) {
        if (wrongPass != null && wrongPass) {
            model.addAttribute("wrongPass", "Zlé heslo!!!");
        }
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
    public String welcomeReceptionist(Model model) {
        model.addAttribute("userFullName", userService.getLoggedUser().getFirstName() + " " + userService.getLoggedUser().getLastName());
        return "receptionist/receptionist";
    }


}

