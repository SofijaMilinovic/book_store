package com.sofija.bookstore.controller;

import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.service.UserService;
import com.sofija.bookstore.model.User;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("")
    public Response register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return ResponseUtil.createResponse(registeredUser, HttpStatus.ACCEPTED.value(), "Registration successful");
        } catch (Exception ex) {
            return ResponseUtil.createResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }
}
