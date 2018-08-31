package org.softuni.eventures.services;

import org.softuni.eventures.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean usernameExists(String username);

    boolean ucnExists(String ucn);

    boolean emailExists(String email);

    void saveUser(User user);

    User getUserByUsername(String username);
}
