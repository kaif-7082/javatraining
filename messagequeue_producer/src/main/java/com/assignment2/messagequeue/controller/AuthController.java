package com.assignment2.messagequeue.controller;

import com.assignment2.messagequeue.aop.LogExecution;
import com.assignment2.messagequeue.model.User;
import com.assignment2.messagequeue.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    @LogExecution
    public String login(@RequestBody User loginRequest) {
        return authService.loginUser(loginRequest);
    }
}