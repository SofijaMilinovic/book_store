package com.sofija.bookstore.controller;

import com.sofija.bookstore.model.UserModel;
import com.sofija.bookstore.service.UserService;
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
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public UserModel getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }
}
