package org.softuni.residentevil.controllers;

import org.softuni.residentevil.common.annotations.PreAuthenticate;
import org.softuni.residentevil.common.enums.UserRole;
import org.softuni.residentevil.models.binding.UserLoginBindingModel;
import org.softuni.residentevil.models.binding.UserRegisterBindingModel;
import org.softuni.residentevil.models.service.UserServiceModel;
import org.softuni.residentevil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    @PreAuthenticate
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @PreAuthenticate
    public String loginConfirm(@ModelAttribute UserLoginBindingModel userLoginBindingModel, HttpSession session) {
        UserServiceModel userFromDb = this.userService
                .getUserByUsername(userLoginBindingModel.getUsername());

        if(userFromDb == null || !userFromDb.getPassword().equals(userLoginBindingModel.getPassword())) {
            return "login";
        }

        session.setAttribute("user-id", userFromDb.getId());
        session.setAttribute("user-username", userFromDb.getUsername());
        session.setAttribute("user-role", userFromDb.getUserRole());

        if(userFromDb.getUserRole() == UserRole.ADMIN) {
            return "redirect:/admin/home";
        }

        return "redirect:/home";
    }

    @GetMapping("/register")
    @PreAuthenticate
    public String register(@ModelAttribute("model") UserRegisterBindingModel model) {
        return "register";
    }

    @PostMapping("/register")
    @PreAuthenticate
    public String registerConfirm(@ModelAttribute() UserRegisterBindingModel userRegisterBindingModel, Model model) {
        try {
            this.userService.validateUserBindingModel(userRegisterBindingModel);
            this.userService.createUser(userRegisterBindingModel);
            return "redirect:/login";
        } catch (IllegalArgumentException iae) {
            model.addAttribute("exceptionMessage", iae.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    @PreAuthenticate(loggedIn = true)
    public String logout(HttpSession session) {
        session.invalidate();

        return ("redirect:/");
    }
}
