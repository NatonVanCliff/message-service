package com.springapp.mvc.controller;

import com.springapp.mvc.user.model.User;
import com.springapp.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping("/registration")
    public ModelAndView registrationView() {
        return new ModelAndView("/registration");
    }

    @RequestMapping(value = "/singup", method = RequestMethod.POST)
    public String singUp(@ModelAttribute User user, ModelMap modelMap) {
        User existingUser = userService.findByUserName(user.getUsername());
        if (existingUser != null) {
            modelMap.addAttribute("message", "User already exists.");
            return "registration";
        } else {
            userService.create(user); // TODO validation of fields
            return "redirect:login";
        }
    }
}