package com.springapp.mvc.controller;

import com.springapp.mvc.user.model.Role;
import com.springapp.mvc.user.model.User;
import com.springapp.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping("/admin")
    public ModelAndView adminView() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return createModelAndView((User)authentication.getPrincipal());
    }

    private ModelAndView createModelAndView(User user) {
        List<User> userList = userService.findAllExcept(user);
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject(userList);
        return modelAndView;
    }

    @RequestMapping(value = "/addAdminRole")
    public String addAdminRole(@RequestParam("userId") Integer userId) {
        userService.setRole(userId, Role.ROLE_ADMIN);
        return "redirect:admin";
    }

    @RequestMapping(value = "/deleteAdminRole")
    public String deleteAdminRole(@RequestParam("userId") Integer userId) {
        userService.setRole(userId, Role.ROLE_USER);
        return "redirect:admin";
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer userId) {
        userService.deleteByUser(userId);
        return "redirect:admin";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, ModelMap modelMap) {
        User existingUser = userService.findByUserName(user.getUsername());
        if (existingUser != null) {
            modelMap.addAttribute("message", "User already exists.");
        } else {
            userService.create(user); // TODO validation of fields
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelMap.addAttribute(userService.findAllExcept((User)authentication.getPrincipal()));
        return "admin";
    }
}
