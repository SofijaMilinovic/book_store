package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.OrderData;
import com.sofija.bookstore.facade.OrderFacade;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{userId}")
    public List<OrderData> getAllByUserId(@PathVariable int userId) {
        return orderFacade.getAllByUserId(userId);
    }

    @PostMapping("")
    public Response create(@RequestBody OrderData orderData) {
        OrderData createdOrderData = orderFacade.create(orderData);
        return ResponseUtil.createResponse(createdOrderData, HttpStatus.CREATED.value(), "Order placed successfully");
    }

    @PutMapping("")
    public Response completeOrder(@RequestBody OrderData orderData) {
        orderFacade.completeOrder(orderData.getId());
        return ResponseUtil.createResponse(HttpStatus.OK.value(), "Order successfully completed");
    }
}
