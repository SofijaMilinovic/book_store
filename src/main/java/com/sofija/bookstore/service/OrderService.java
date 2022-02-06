package com.sofija.bookstore.service;

import com.sofija.bookstore.model.Order;
import com.sofija.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
