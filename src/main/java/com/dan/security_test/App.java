package com.dan.security_test;

import com.dan.security_test.models.Role;
import com.dan.security_test.models.user.User;
import com.dan.security_test.repositories.RoleRepository;
import com.dan.security_test.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository,
                                               PasswordEncoder passwordEncoder,
                                               RoleRepository roleRepository) {
        return args -> {
            if (userRepository.count() > 0) {
                return;
            }

            Role adminRole = new Role("ADMIN");
            Role userRole = new Role("USER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(
                    "Eric",
                    "Cartman",
                    "coolEric@gmail.com",
                    passwordEncoder.encode("123"), roles);

            userRepository.save(admin);
        };
    }
}
