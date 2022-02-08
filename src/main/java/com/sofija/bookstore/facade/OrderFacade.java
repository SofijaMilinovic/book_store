package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.OrderData;
import com.sofija.bookstore.data.OrderEntryData;
import com.sofija.bookstore.data.OrderStatusData;
import com.sofija.bookstore.model.OrderEntryModel;
import com.sofija.bookstore.model.OrderModel;
import com.sofija.bookstore.model.OrderStatusModel;
import com.sofija.bookstore.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderFacade {

    @Resource
    private UserFacade userFacade;

    @Resource
    private BookFacade bookFacade;

    @Resource
    private OrderService orderService;

    public List<OrderData> getAll() {
        return orderService.getAll()
                .stream()
                .map(this::createOrderData)
                .collect(Collectors.toList());
    }

    private OrderData createOrderData(OrderModel orderModel) {
        OrderData orderData = new OrderData();
        orderData.setId(orderModel.getId());
        orderData.setOrderStatusData(createOrderStatusData(orderModel.getOrderStatusModel()));
        orderData.setOrderEntryDataList(createOrderEntryDataList(orderData, orderModel.getOrderEntryModels()));
        orderData.setAddress(orderModel.getAddress());
        orderData.setCity(orderModel.getCity());
        orderData.setCountry(orderModel.getCountry());
        orderData.setUserData(userFacade.createUserData(orderModel.getUserModel()));
        return orderData;
    }

    private OrderStatusData createOrderStatusData(OrderStatusModel orderStatusModel) {
        OrderStatusData orderStatusData = new OrderStatusData();
        orderStatusData.setId(orderStatusModel.getId());
        orderStatusData.setName(orderStatusModel.getName());
        return orderStatusData;
    }

    private List<OrderEntryData> createOrderEntryDataList(OrderData orderData, List<OrderEntryModel> orderEntryModels) {
        return orderEntryModels.stream()
                .map(orderEntryModel -> createOrderEntryData(orderData, orderEntryModel))
                .collect(Collectors.toList());
    }

    private OrderEntryData createOrderEntryData(OrderData orderData, OrderEntryModel orderEntryModel) {
        OrderEntryData orderEntryData = new OrderEntryData();
        orderEntryData.setOrderData(orderData);
        orderEntryData.setBookData(bookFacade.createBookData(orderEntryModel.getBookModel()));
        orderEntryData.setQuantity(orderEntryModel.getQuantity());
        orderEntryData.setId(orderEntryModel.getId());
        return orderEntryData;
    }
}
