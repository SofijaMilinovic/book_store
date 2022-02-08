package com.sofija.bookstore.service;

import com.sofija.bookstore.model.BookModel;
import com.sofija.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookRepository bookRepository;

    public List<BookModel> getAll() {
        return bookRepository.findAll();
    }

    public BookModel getById(int id) {
        return bookRepository.findById(id)
                .orElse(null);
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }
}
