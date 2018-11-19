package com.cvut.corsys.web.controller;

import com.cvut.corsys.entity.Role;
import com.cvut.corsys.entity.User;
import com.cvut.corsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam(name="wrongPass", required = false) Boolean wrongPass, Model model) {
        if (wrongPass != null && wrongPass) {
            model.addAttribute("wrongPass", "Zle heslo!!!");
        }
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String login(Model model) {
        // 1.krat getRole vrati rolu daneho uzivatela, druhy krat vrati String nazov role
        String role = this.userService.getLoggedUser().getRole().getRole();
        model.addAttribute("userRole", role);
        return "welcome";
    }


}

