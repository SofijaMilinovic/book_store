package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.UserData;
import com.sofija.bookstore.facade.UserFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Resource
    private UserFacade userFacade;

    @GetMapping("")
    public List<UserData> getAll() {
        return userFacade.getAll();
    }

    @GetMapping("/{userId}")
    public UserData getById(@PathVariable Integer userId) {
        return userFacade.getById(userId);
    }
}
