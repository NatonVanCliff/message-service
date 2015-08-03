package com.springapp.mvc.controller;

import com.springapp.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public ModelAndView loginView(
			@RequestParam(required = false) String authfailed, String logout) {
		String message = "";
		if (authfailed != null) {
			message = "Invalid username or password, try again!";
		}
		return new ModelAndView("login", "message", message);
	}
}
