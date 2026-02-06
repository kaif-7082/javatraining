package com.assignment2.messagequeue.service;

import com.assignment2.messagequeue.aop.LogExecution;
import com.assignment2.messagequeue.dto.UserActivityEvent;
import com.assignment2.messagequeue.model.User;
import com.assignment2.messagequeue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StreamBridge streamBridge;

    // The binding name defined in application.properties
    private static final String BINDING_NAME = "userLogin-out-0";

    public String registerUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            return "User already exists!";
        }
        userRepository.save(user);
        return "User Registered Successfully!";
    }

    @LogExecution
    public String loginUser(User loginRequest) {
        User dbUser = userRepository.findById(loginRequest.getUserId()).orElse(null);

        if (dbUser == null) {
            return "User not found!";
        }

        if (dbUser.getPassword().equals(loginRequest.getPassword())) {
            // Logic: Password Match -> Send SUCCESS Event
            UserActivityEvent event = new UserActivityEvent(dbUser.getUserId(), "LOGIN_SUCCESS");
            streamBridge.send(BINDING_NAME, event);
            return "Login Successful!";
        } else {
            // Logic: Wrong Password -> Send FAILURE Event
            UserActivityEvent event = new UserActivityEvent(dbUser.getUserId(), "LOGIN_FAILURE");
            streamBridge.send(BINDING_NAME, event);
            return "Wrong Password!";
        }
    }
}