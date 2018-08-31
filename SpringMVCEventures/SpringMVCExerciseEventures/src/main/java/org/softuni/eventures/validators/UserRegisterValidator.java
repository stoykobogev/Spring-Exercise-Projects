package org.softuni.eventures.validators;

import org.softuni.eventures.eceptions.FormArgumentException;
import org.softuni.eventures.models.binding.UserRegisterBindingModel;
import org.softuni.eventures.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserRegisterValidator implements Validator {

    private static final String INVALID_UCN_PATTERN_MESSAGE = "UCN should have length of 10 and contain only numbers";
    private static final String USERNAME_EXISTS_MESSAGE = "Username already exists";
    private static final String INVALID_EMAIL_MESSAGE = "Email is invalid";
    private static final String EMAIL_EXISTS_MESSAGE = "Email already exists";
    private static final String UCN_EXISTS_MESSAGE = "UCN already exists";
    private static final String PASSWORDS_MISMATCH_MESSAGE = "Confirm Password is different form Password";
    private static final String GENERIC_EMPTY_FIELD_MESSAGE = "Field cannot be empty";
    private static final String INVALID_USERNAME_PATTERN_MESSAGE = "Username should be at least 3 symbols long " +
            "and contain only (A-Z a-z 0-9 _ - . * ~)";
    private static final String INVALID_PASSWORD_LENGTH_MESSAGE = "Password should be at least 5 symbols long";

    private final UserService userService;
    private Pattern ucnPattern;
    private Pattern emailPattern;
    private Pattern usernamePattern;

    @Autowired
    public UserRegisterValidator(UserService userService) {
        this.userService = userService;
        this.ucnPattern = Pattern.compile("^[0-9]{10}$");
        this.emailPattern = Pattern.compile("^[^@.]+@[^@.]+(\\.[^@.]+)+$");
        this.usernamePattern = Pattern.compile("^[A-Za-z0-9_\\-.*~]{3,}$");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterBindingModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterBindingModel model = (UserRegisterBindingModel) target;

        try {
            validateEmail(model, errors);
            validatePasswords(model, errors);
            validateUsername(model, errors);
            validateUCN(model, errors);
        } catch (Exception e) {
            throw new FormArgumentException();
        }
    }

    private void validateEmail(UserRegisterBindingModel model, Errors errors) {
        String email = model.getEmail();
        if (email == null || email.isEmpty()) {
            errors.rejectValue("email", "EMPTY_EMAIL", GENERIC_EMPTY_FIELD_MESSAGE);
        } else if (!this.emailPattern.matcher(model.getEmail()).matches()) {
            errors.rejectValue("email", "INVALID_EMAIL", INVALID_EMAIL_MESSAGE);
        } else if (this.userService.emailExists(model.getEmail())) {
            errors.rejectValue("email", "EMAIL_EXISTS", EMAIL_EXISTS_MESSAGE);
        }
    }

    private void validateUsername(UserRegisterBindingModel model, Errors errors) {
        String username = model.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.rejectValue("username", "EMPTY_USERNAME", GENERIC_EMPTY_FIELD_MESSAGE);
        } else if (!this.usernamePattern.matcher(username).matches()) {
            errors.rejectValue("username", "INVALID_USERNAME_PATTERN", INVALID_USERNAME_PATTERN_MESSAGE);
        } else if (this.userService.usernameExists(username)) {
            errors.rejectValue("username", "USERNAME_EXISTS", USERNAME_EXISTS_MESSAGE);
        }
    }

    private void validateUCN(UserRegisterBindingModel model, Errors errors) {
        if (!this.ucnPattern.matcher(model.getUniqueCitizenNumber()).matches()) {
            errors.rejectValue("uniqueCitizenNumber", "INVALID_UCN_PATTERN", INVALID_UCN_PATTERN_MESSAGE);
        } else if (this.userService.ucnExists(model.getUniqueCitizenNumber())) {
            errors.rejectValue("uniqueCitizenNumber", " UCN_EXISTS", UCN_EXISTS_MESSAGE);
        }
    }

    private void validatePasswords(UserRegisterBindingModel model, Errors errors) {
        String password = model.getPassword();
        if (password == null || password.isEmpty()) {
            errors.rejectValue("password", "EMPTY_PASSWORD", GENERIC_EMPTY_FIELD_MESSAGE);
        } else if (password.length() < 5) {
            errors.rejectValue("password", "INVALID_PASSWORD_LENGTH", INVALID_PASSWORD_LENGTH_MESSAGE);
        } else if (!password.equals(model.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "PASSWORD_MISMATCH", PASSWORDS_MISMATCH_MESSAGE);
        }
    }
}
