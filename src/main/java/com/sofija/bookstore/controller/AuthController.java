package com.sofija.bookstore.controller;

import com.sofija.bookstore.exception.UserException;
import com.sofija.bookstore.model.User;
import com.sofija.bookstore.service.UserService;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return ResponseUtil.createResponse(registeredUser, HttpStatus.ACCEPTED.value(), "Registration successful");
        } catch (UserException ex) {
            return ResponseUtil.createResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        try {
            User loggedInUser = userService.login(user);
            return ResponseUtil.createResponse(loggedInUser, HttpStatus.OK.value(), "Login successful");
        } catch (UserException ex) {
            return ResponseUtil.createResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        }
    }
}
