package org.softuni.residentevil.seeder;

import org.softuni.residentevil.entities.User;
import org.softuni.residentevil.repositories.UserRepository;
import org.softuni.residentevil.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DataSeeder(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.userRepository.findAll().isEmpty()) {
            User admin = createUser("admin", "admin", "USER", "ADMIN", "MODERATOR");
            User moderator = createUser("moderator", "moderator", "USER", "MODERATOR");
            User superAdmin = createUser("superadmin", "superadmin",
                    "USER", "ADMIN", "MODERATOR", "SUPER_ADMIN");

            this.userRepository.save(admin);
            this.userRepository.save(moderator);
            this.userRepository.save(superAdmin);
            this.userRepository.flush();
        }
    }

    private User createUser(String username, String password, String... authorities) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAuthorities(this.userRoleRepository.findAllByAuthorityIn(authorities));

        return user;
    }
}
