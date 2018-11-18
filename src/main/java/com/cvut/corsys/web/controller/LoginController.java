package com.cvut.corsys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void login(@RequestParam(name="wrongPass", required = false) Boolean wrongPass, Model model) {
		if (wrongPass != null && wrongPass) {
			model.addAttribute("wrongPass", "Zle heslo!!!");
		}
	}

	
}
