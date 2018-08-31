package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.User;
import org.softuni.cardealer.models.binding.UserBindingModel;
import org.softuni.cardealer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView registerGet(ModelAndView modelAndView) {
        modelAndView.setViewName("users/register");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginGet( ModelAndView modelAndView) {
        modelAndView.setViewName("users/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginPost(HttpSession session, UserBindingModel userBindingModel, ModelAndView modelAndView) {

        String exceptionMessage = this.userService.validateLogin(userBindingModel);

        if (exceptionMessage != null) {
            modelAndView.addObject("exceptionMessage", exceptionMessage);
            modelAndView.setViewName("users/login");
        } else {
            session.setAttribute("username", userBindingModel.getUsername());
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerPost(HttpSession session, UserBindingModel userBindingModel, ModelAndView modelAndView) {

        String exceptionMessage = this.userService.validateRegister(userBindingModel);

        if (exceptionMessage != null) {
            modelAndView.addObject("exceptionMessage", exceptionMessage);
            modelAndView.setViewName("users/register");
        } else {
            User user = this.userService.persistUser(userBindingModel);
            session.setAttribute("username", user.getUsername());
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session, ModelAndView modelAndView) {
        session.setAttribute("username", null);

        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }
}
