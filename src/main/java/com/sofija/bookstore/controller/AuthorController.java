package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.AuthorData;
import com.sofija.bookstore.facade.AuthorFacade;
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
@RequestMapping("/authors")
@CrossOrigin
public class AuthorController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @Resource
    private AuthorFacade authorFacade;

    @GetMapping("")
    public Response getAll() {
        try {
            List<AuthorData> authors = authorFacade.getAll();
            return ResponseUtil.createResponse(authors, HttpStatus.OK.value());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }
}
