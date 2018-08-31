package org.softuni.eventures.services;

import org.modelmapper.ModelMapper;
import org.softuni.eventures.entities.User;
import org.softuni.eventures.entities.UserRole;
import org.softuni.eventures.models.binding.UserRegisterBindingModel;
import org.softuni.eventures.repositories.UserRepository;
import org.softuni.eventures.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean usernameExists(String username) {
        return this.userRepository.usernameExists(username);
    }

    @Override
    public boolean ucnExists(String ucn) {
        return this.userRepository.ucnExists(ucn);
    }

    @Override
    public boolean emailExists(String email) {
        return this.userRepository.emailExists(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> authorities = this.userRoleRepository.findByAuthority("USER");
        user.setAuthorities(authorities);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public User loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(usernameOrEmail);
        if (user == null) {
            user = this.userRepository.findUserByEmail(usernameOrEmail);
        }

        return user;
    }
}
