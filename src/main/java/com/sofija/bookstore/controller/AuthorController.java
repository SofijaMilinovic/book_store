package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.AuthorData;
import com.sofija.bookstore.facade.AuthorFacade;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin
public class AuthorController {

    @Resource
    private AuthorFacade authorFacade;

    @GetMapping("")
    public List<AuthorData> getAll() {
        return authorFacade.getAll();
    }
}
