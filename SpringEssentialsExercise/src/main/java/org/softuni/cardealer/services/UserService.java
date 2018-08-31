package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.User;
import org.softuni.cardealer.models.binding.UserBindingModel;

public interface UserService {
    String validateLogin(UserBindingModel userBindingModel);

    String validateRegister(UserBindingModel userBindingModel);

    User persistUser(UserBindingModel userBindingModel);
}
