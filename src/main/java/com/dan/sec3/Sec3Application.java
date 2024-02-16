package com.dan.sec3;

import com.dan.sec3.models.Role;
import com.dan.sec3.models.User;
import com.dan.sec3.repositories.RoleRepository;
import com.dan.sec3.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Sec3Application {

	public static void main(String[] args) {
		SpringApplication.run(Sec3Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (UserRepository userRepository,
												PasswordEncoder passwordEncoder,
												RoleRepository roleRepository) {
		return args -> {
			if(userRepository.count() > 0) {
				return;
			}
			Role adminRole = new Role("ADMIN");
			Role userRole = new Role("USER");
			roleRepository.save(adminRole);
			roleRepository.save(userRole);

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User("Eric", "cool", passwordEncoder.encode("123"), roles);

			userRepository.save(admin);
		};
	}

}
