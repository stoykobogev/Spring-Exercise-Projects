package org.softuni.residentevil.controllers;

import org.softuni.residentevil.common.annotations.PreAuthenticate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @GetMapping("/home")
    @PreAuthenticate(loggedIn = true)
    public ModelAndView home(HttpSession session, ModelAndView modelAndView) {
        modelAndView.addObject("username", session.getAttribute("user-username"));
        modelAndView.setViewName("user-home");

        return modelAndView;
    }
}
