package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.GenreData;
import com.sofija.bookstore.facade.GenreFacade;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/genres")
@CrossOrigin
public class GenreController {

    private static final Logger LOG = LoggerFactory.getLogger(GenreController.class);

    @Resource
    private GenreFacade genreFacade;

    @GetMapping("")
    public Response getAll() {
        try {
            List<GenreData> genres = genreFacade.getAll();
            return ResponseUtil.createResponse(genres, HttpStatus.OK.value());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }
}
