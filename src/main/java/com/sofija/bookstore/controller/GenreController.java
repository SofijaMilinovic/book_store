package com.sofija.bookstore.controller;

import com.sofija.bookstore.model.GenreModel;
import com.sofija.bookstore.service.GenreService;
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
    private GenreService genreService;

    @GetMapping("")
    public List<GenreModel> getAll() {
        return genreService.getAll();
    }
}
