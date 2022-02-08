package com.sofija.bookstore.controller;

import com.sofija.bookstore.model.BookModel;
import com.sofija.bookstore.service.BookService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("")
    public List<BookModel> getAll() {
        return bookService.getAll();
    }
}
