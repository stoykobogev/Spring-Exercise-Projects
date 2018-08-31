package org.softuni.eventures.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView viewError(String errorMessage, HttpStatus status) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", errorMessage);
        mav.setStatus(status);
        mav.setViewName("error/errorLayout");
        return mav;
    }
}
