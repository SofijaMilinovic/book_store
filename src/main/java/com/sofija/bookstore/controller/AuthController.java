package com.sofija.bookstore.controller;

import com.sofija.bookstore.exception.UserException;
import com.sofija.bookstore.model.UserModel;
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
    public Response register(@RequestBody UserModel userModel) {
        try {
            UserModel registeredUserModel = userService.register(userModel);
            return ResponseUtil.createResponse(registeredUserModel, HttpStatus.ACCEPTED.value(), "Registration successful");
        } catch (UserException ex) {
            return ResponseUtil.createResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody UserModel userModel) {
        try {
            UserModel loggedInUserModel = userService.login(userModel);
            return ResponseUtil.createResponse(loggedInUserModel, HttpStatus.OK.value(), "Login successful");
        } catch (UserException ex) {
            return ResponseUtil.createResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        }
    }
}
