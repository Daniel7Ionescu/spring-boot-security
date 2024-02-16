package com.dan.security_test.services;

import com.dan.security_test.models.user.UserDTO;
import com.dan.security_test.models.user.UserRegistrationDTO;

public interface AuthenticationService {

    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO);
}
