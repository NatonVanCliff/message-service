package com.springapp.mvc.controller;

import com.springapp.mvc.contact.model.Contact;
import com.springapp.mvc.contact.service.ContactService;
import com.springapp.mvc.message.model.Message;
import com.springapp.mvc.message.service.MessageService;
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
public class MessagesController {

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @Autowired
    MessageService messageService;

    @RequestMapping("/messages")
    public ModelAndView messagesView() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return createModelAndView((User)authentication.getPrincipal());
    }

    private ModelAndView createModelAndView(User user) {
        List<Contact> contactList = contactService.findByUser(user.getId());
        List<Message> messageList = messageService.findByRecipient(user.getId());
        List<User> availableUsers = userService.findAvailableUsers(user);
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject(user);
        modelAndView.addObject("contactList", contactList);
        modelAndView.addObject("messageList", messageList);
        modelAndView.addObject("userList", availableUsers);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteContact")
    public String deleteContact(@RequestParam("userId") Integer userId,
                                @RequestParam("contactId") Integer contactId) {
        contactService.removeContact(userId, contactId);
        return "redirect:messages";
    }

    @RequestMapping(value = "/deleteMsg")
    public String deleteMessage(@RequestParam("msgId") Integer messageId) {
        messageService.delete(messageId);
        return "redirect:messages";
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(@ModelAttribute() Contact contact) {
        contactService.addContact(contact);
        return "redirect:messages";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute() Message message) {
        messageService.send(message);
        return "redirect:messages";
    }
}

