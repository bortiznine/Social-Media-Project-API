package com.socialmedia.demo.controller;

import com.socialmedia.demo.model.Request.LoginRequest;
import com.socialmedia.demo.model.User;
import com.socialmedia.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //http://localhost:portnumber/auth/users/register
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        System.out.println("calling createUser");
        return userService.createUser(userObject);
    }

    //http://localhost:9092/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("calling loginUser");
        return userService.loginUser(loginRequest);
    }

    @PostMapping("/passwordreset")
    public User passwordReset(@RequestBody UserService userService)
    {
        System.out.println("calling passwordReset");
        //need to refactor to run method to change password.
        return userService.passwordReset(userService);
    }
}


