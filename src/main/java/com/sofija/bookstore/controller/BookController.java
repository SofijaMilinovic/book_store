package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.BookData;
import com.sofija.bookstore.facade.BookFacade;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {

    @Resource
    private BookFacade bookFacade;

    @GetMapping("")
    public List<BookData> getAll() {
        return bookFacade.getAll();
    }
}
