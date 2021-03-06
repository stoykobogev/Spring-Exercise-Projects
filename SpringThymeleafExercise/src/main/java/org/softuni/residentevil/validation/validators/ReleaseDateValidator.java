package org.softuni.residentevil.validation.validators;

import org.softuni.residentevil.validation.annotaions.ReleaseDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ReleaseDateValidator implements ConstraintValidator<ReleaseDate, String> {

    private LocalDate today;

    @Override
    public void initialize(ReleaseDate constraintAnnotation) {
        today = LocalDate.now();
    }

    @Override
    public boolean isValid(String localDate, ConstraintValidatorContext constraintValidatorContext) {
        return (localDate != null && !localDate.isEmpty() && LocalDate.parse(localDate).isBefore(today));
    }
}
