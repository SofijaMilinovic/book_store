package com.sofija.bookstore.controller;

import com.sofija.bookstore.model.Book;
import com.sofija.bookstore.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("")
    public List<Book> getAll() {
        return bookService.getAll();
    }
}
