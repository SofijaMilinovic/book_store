package com.sofija.bookstore.controller;

import com.sofija.bookstore.model.Order;
import com.sofija.bookstore.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("")
    public List<Order> getAll() {
        return orderService.getAll();
    }
}
