package org.softuni.eventuresjms.services;

import org.softuni.eventuresjms.entities.User;


public interface UserService {

    User findUserByUsername(String username);
}
