package com.dan.security_test.services;

import com.dan.security_test.models.user.SecurityUser;
import com.dan.security_test.models.user.User;
import com.dan.security_test.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
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
