package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.BookData;
import com.sofija.bookstore.exception.BookException;
import com.sofija.bookstore.facade.BookFacade;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable int id) {
        try {
            bookFacade.delete(id);
            return ResponseUtil.createResponse(HttpStatus.NO_CONTENT.value(), "Book successfully deleted");
        } catch (BookException ex) {
            return ResponseUtil.createResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        }
    }

    @PostMapping("")
    public Response create(@RequestBody BookData bookData) {
        BookData createdBookData = bookFacade.create(bookData);
        return ResponseUtil.createResponse(createdBookData, HttpStatus.CREATED.value(), "Book successfully created");
    }

    @PutMapping("")
    public Response update(@RequestBody BookData bookData) {
        bookFacade.update(bookData);
        return ResponseUtil.createResponse(HttpStatus.OK.value(), "Book successfully updated");
    }
}
