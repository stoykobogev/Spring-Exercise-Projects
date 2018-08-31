package org.softuni.eventures.controllers;

import org.softuni.eventures.eceptions.FormArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController extends BaseController {

    private static final String SERVER_EXCEPTION_MESSAGE = "Something went wrong yo, not your fault though";

    @ExceptionHandler(FormArgumentException.class)
    public ModelAndView catchFormException() {
        return this.viewError(FormArgumentException.class.getAnnotation(ResponseStatus.class).reason(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView catchServerError() {
        return this.viewError(SERVER_EXCEPTION_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
