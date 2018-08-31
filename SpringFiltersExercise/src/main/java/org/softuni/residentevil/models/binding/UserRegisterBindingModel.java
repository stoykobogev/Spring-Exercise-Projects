package org.softuni.residentevil.models.binding;

import javax.validation.constraints.NotEmpty;

import static org.softuni.residentevil.common.constants.ValidationMessages.NULL_MESSAGE;

public class UserRegisterBindingModel {

    @NotEmpty(message = NULL_MESSAGE)
    private String username;

    @NotEmpty(message = NULL_MESSAGE)
    private String password;

    @NotEmpty(message = NULL_MESSAGE)
    private String confirmPassword;

    private String email;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
