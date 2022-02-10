package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.OrderData;
import com.sofija.bookstore.facade.OrderFacade;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderFacade orderFacade;

    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response create(@RequestBody OrderData orderData) {
        try {
            OrderData createdOrderData = orderFacade.create(orderData);
            return ResponseUtil.createResponse(createdOrderData, HttpStatus.CREATED.value(), "Order placed successfully");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    @RequestMapping(
            value = "/users/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response getAllByUserId(@PathVariable int userId) {
        try {
            List<OrderData> orders = orderFacade.getAllByUserId(userId);
            return ResponseUtil.createResponse(orders, HttpStatus.OK.value());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response completeOrder(@RequestBody OrderData orderData) {
        try {
            orderFacade.completeOrder(orderData.getId());
            return ResponseUtil.createResponse(HttpStatus.OK.value(), "Order successfully completed");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseUtil.createErrorResponse();
        }
    }
}
