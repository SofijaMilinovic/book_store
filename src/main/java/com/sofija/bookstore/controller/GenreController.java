package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.GenreData;
import com.sofija.bookstore.facade.GenreFacade;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/genres")
@CrossOrigin(origins = "*")
public class GenreController {

    @Resource
    private GenreFacade genreFacade;

    @GetMapping("")
    public List<GenreData> getAll() {
        return genreFacade.getAll();
    }
}
