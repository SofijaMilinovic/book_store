package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.OrderData;
import com.sofija.bookstore.facade.OrderFacade;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Resource
    private OrderFacade orderFacade;

    @GetMapping("")
    public List<OrderData> getAll() {
        return orderFacade.getAll();
    }
}
