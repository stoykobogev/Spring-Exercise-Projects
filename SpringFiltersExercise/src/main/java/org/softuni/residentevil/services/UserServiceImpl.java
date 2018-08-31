package org.softuni.residentevil.services;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.entities.User;
import org.softuni.residentevil.common.enums.UserRole;
import org.softuni.residentevil.models.binding.UserRegisterBindingModel;
import org.softuni.residentevil.models.service.UserServiceModel;
import org.softuni.residentevil.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.softuni.residentevil.common.constants.ValidationMessages.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createUser(UserRegisterBindingModel bindingModel) {
        User userEntity = this.modelMapper.map(bindingModel, User.class);

        userEntity.setUserRole(UserRole.USER);

        return this.userRepository.save(userEntity) != null;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        return this.modelMapper.map(
                this.userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void validateUserBindingModel(UserRegisterBindingModel model) {
        if (this.userRepository.usernameExists(model.getUsername())) {
            throw new IllegalArgumentException(USERNAME_EXISTS_MESSAGE);
        }

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            throw new IllegalArgumentException(PASSWORDS_MISMATCH_MESSAGE);
        }
    }
}
