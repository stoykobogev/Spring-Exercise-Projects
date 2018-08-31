package org.softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.softuni.cardealer.entities.User;
import org.softuni.cardealer.models.binding.UserBindingModel;
import org.softuni.cardealer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String EMPTY_USERNAME = "Username cannot be empty";
    private static final String USERNAME_NOT_EXISTS = "Username does not exist";
    private static final String WRONG_PASSWORD = "Wrong password";
    private static final String PASSWORDS_MISMATCH = "Passwords mismatch";
    private static final String USERNAME_TAKEN = "Username is already taken";
    private static final String EMAIL_TAKEN = "Email is already taken";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String validateLogin(UserBindingModel userBindingModel) {

        if (userBindingModel.getUsername().isEmpty()) {
            return EMPTY_USERNAME;
        }

        User user = this.userRepository.findFirstByUsername(userBindingModel.getUsername());

        if (user == null) {
            return USERNAME_NOT_EXISTS;
        }

        if (!user.getPassword().equals(userBindingModel.getPassword())) {
            return WRONG_PASSWORD;
        }

        return null;
    }

    @Override
    public String validateRegister(UserBindingModel userBindingModel) {

        if (!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())) {
            return PASSWORDS_MISMATCH;
        }

        User user = this.userRepository.findFirstByUsername(userBindingModel.getUsername());
        if (user != null) {
            return USERNAME_TAKEN;
        }

        user = this.userRepository.findFirstByEmail(userBindingModel.getEmail());
        if (user != null) {
            return EMAIL_TAKEN;
        }

        return null;
    }

    @Override
    public User persistUser(UserBindingModel userBindingModel) {
        User user = this.modelMapper.map(userBindingModel, User.class);

        return this.userRepository.saveAndFlush(user);
    }
}
