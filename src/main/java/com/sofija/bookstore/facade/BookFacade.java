package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.AuthorData;
import com.sofija.bookstore.data.BookData;
import com.sofija.bookstore.data.GenreData;
import com.sofija.bookstore.model.AuthorModel;
import com.sofija.bookstore.model.BookModel;
import com.sofija.bookstore.model.GenreModel;
import com.sofija.bookstore.service.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookFacade {

    @Resource
    private BookService bookService;

    public List<BookData> getAll() {
        return bookService.getAll()
                .stream()
                .map(this::createBookData)
                .collect(Collectors.toList());
    }

    public BookData createBookData(BookModel bookModel) {
        BookData bookData = new BookData();
        bookData.setId(bookModel.getId());
        bookData.setGenreData(createGenreData(bookModel.getGenreModel()));
        bookData.setAuthorData(createAuthorData(bookModel.getAuthorModel()));
        bookData.setPrice(bookModel.getPrice());
        bookData.setTitle(bookModel.getTitle());
        bookData.setImagePath(bookModel.getImagePath());
        return bookData;
    }

    private GenreData createGenreData(GenreModel genreModel) {
        GenreData genreData = new GenreData();
        genreData.setId(genreModel.getId());
        genreData.setName(genreModel.getName());
        return genreData;
    }

    private AuthorData createAuthorData(AuthorModel authorModel) {
        AuthorData authorData = new AuthorData();
        authorData.setId(authorModel.getId());
        authorData.setFirstName(authorModel.getFirstName());
        authorData.setLastName(authorModel.getLastName());
        authorData.setDateOfBirth(authorModel.getDateOfBirth());
        return authorData;
    }
}
