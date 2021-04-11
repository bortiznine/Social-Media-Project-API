package com.socialmedia.demo.service;

import com.socialmedia.demo.exception.InformationExistException;
import com.socialmedia.demo.exception.InformationNotFoundException;
import com.socialmedia.demo.model.Request.LoginRequest;
import com.socialmedia.demo.model.Response.LoginResponse;
import com.socialmedia.demo.model.User;
import com.socialmedia.demo.repository.UserRepository;
import com.socialmedia.demo.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public User createUser(User userObject) {
        System.out.println("service calling createUser ==>");
        //base case: check if user already exist in our system
        //else create the user
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address " + userObject.getEmailAddress() + " already exist");
        }
    }

    public User findUserByEmailAddress(String email) {
        return userRepository.findByEmailAddress(email);
    }

    public ResponseEntity<Object> loginUser(LoginRequest loginRequest) {
        System.out.println("service calling loginUser");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));

        } catch ( NullPointerException e){
            throw new InformationNotFoundException("user with that email address " + loginRequest.getEmail()+ " not found");
        }
    }
}
