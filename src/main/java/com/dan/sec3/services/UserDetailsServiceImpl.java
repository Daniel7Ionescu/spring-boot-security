package com.dan.sec3.services;

import com.dan.sec3.models.SecurityUser;
import com.dan.sec3.models.User;
import com.dan.sec3.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("in user details service");
        User foundUser = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        log.info("Found: id: {}, firstName: {}, email: {}",
                foundUser.getId(),
                foundUser.getFirstName(),
                foundUser.getEmail());

        return new SecurityUser(foundUser);
    }
}
