package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Log;
import org.softuni.cardealer.repositories.LogRepository;
import org.softuni.cardealer.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/all")
    public ModelAndView all(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
            return modelAndView;
        }

        List<Log> logList = this.logService.getAllLogs();
        modelAndView.addObject("logs", logList);
        modelAndView.setViewName("logs/all");

        return modelAndView;
    }

    @PostMapping("/all")
    public ModelAndView allByUsername(HttpSession session, ModelAndView modelAndView, @RequestParam String username) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
            return modelAndView;
        }

        List<Log> logList = this.logService.getLogsByUsername(username);
        modelAndView.addObject("logs", logList);
        modelAndView.setViewName("logs/all");

        return modelAndView;
    }

    @PostMapping("/all/delete")
    public ModelAndView allDelete(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
            return modelAndView;
        }

        this.logService.clearLogs();
        modelAndView.setViewName("logs/all");

        return modelAndView;
    }
}
