package com.sofija.bookstore.service;

import com.sofija.bookstore.model.OrderEntryModel;
import com.sofija.bookstore.model.OrderModel;
import com.sofija.bookstore.model.OrderStatusModel;
import com.sofija.bookstore.repository.OrderEntryRepository;
import com.sofija.bookstore.repository.OrderRepository;
import com.sofija.bookstore.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderEntryRepository orderEntryRepository;

    @Resource
    private OrderStatusRepository orderStatusRepository;

    public List<OrderModel> getAll() {
        return orderRepository.findAll();
    }

    public OrderModel create(OrderModel orderModel, List<OrderEntryModel> orderEntryModels) {
        orderModel.setOrderStatusModel(getPendingOrderStatusModel());
        OrderModel createdOrderModel = orderRepository.save(orderModel);
        orderEntryModels.forEach(orderEntryModel -> orderEntryModel.setOrder(createdOrderModel));
        orderEntryRepository.saveAll(orderEntryModels);
        createdOrderModel.setOrderEntryModels(orderEntryModels);
        return createdOrderModel;
    }

    private OrderStatusModel getPendingOrderStatusModel() {
        return orderStatusRepository.findByName("PENDING");
    }
}
