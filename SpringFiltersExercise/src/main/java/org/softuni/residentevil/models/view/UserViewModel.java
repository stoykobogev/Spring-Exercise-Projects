package org.softuni.residentevil.models.view;

import org.softuni.residentevil.entities.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

public class UserViewModel {

    private String id;
    private String username;
    private String role;
    private String email;

    public UserViewModel() {
    }

    public UserViewModel(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRole(Set<UserRole> roles) {
        Set<String> rolesAsStrings = roles.stream().map(UserRole::getAuthority).collect(Collectors.toSet());
        if (rolesAsStrings.contains("ADMIN")) {
            this.role = "ADMIN";
        } else if (rolesAsStrings.contains("MODERATOR")) {
            this.role = "MODERATOR";
        } else {
            this.role = "USER";
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
