package org.softuni.eventures.seeder;

import org.softuni.eventures.entities.User;
import org.softuni.eventures.repositories.UserRepository;
import org.softuni.eventures.repositories.UserRoleRepository;
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
            User admin = createAdmin();

            this.userRepository.save(admin);
            this.userRepository.flush();
        }
    }

    private User createUser(String username, String password, String... authorities) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        user.setAuthorities(this.userRoleRepository.findAllByAuthorityIn(authorities));

        return user;
    }

    private User createAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@admin.com");
        user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setAuthorities(this.userRoleRepository.findAllByAuthorityIn("USER", "ADMIN"));
        user.setUniqueCitizenNumber("1234567890");

        return user;
    }
}
