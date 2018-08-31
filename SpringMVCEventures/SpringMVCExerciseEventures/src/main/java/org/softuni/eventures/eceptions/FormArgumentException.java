package org.softuni.eventures.eceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Don't fiddle with the form mate")
public class FormArgumentException extends RuntimeException {
}
