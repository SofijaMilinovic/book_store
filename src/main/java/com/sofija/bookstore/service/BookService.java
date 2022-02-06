package com.sofija.bookstore.service;

import com.sofija.bookstore.model.Book;
import com.sofija.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
