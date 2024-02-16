package com.dan.security_test.services;

import com.dan.security_test.models.user.User;
import com.dan.security_test.models.user.UserDTO;
import com.dan.security_test.models.user.UserRegistrationDTO;
import com.dan.security_test.models.Role;
import com.dan.security_test.repositories.RoleRepository;
import com.dan.security_test.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        if(userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()){
            log.info("Username already in use");
            return new UserDTO(); // to do: throw and handle
        }

        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        User user = new User(
                userRegistrationDTO.getFirstName(),
                userRegistrationDTO.getLastName(),
                userRegistrationDTO.getEmail(),
                encodedPassword,
                authorities
        );
        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
    }
}
