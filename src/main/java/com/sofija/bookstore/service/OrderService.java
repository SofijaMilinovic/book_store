package com.sofija.bookstore.service;

import com.sofija.bookstore.model.*;
import com.sofija.bookstore.repository.OrderEntryRepository;
import com.sofija.bookstore.repository.OrderRepository;
import com.sofija.bookstore.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderEntryRepository orderEntryRepository;

    @Resource
    private OrderStatusRepository orderStatusRepository;

    @Resource
    private UserService userService;

    @Value("${golden.customer.threshold}")
    private double goldenCustomerThreshold;

    public List<OrderModel> getAll() {
        return orderRepository.findAll();
    }

    public OrderModel create(OrderModel orderModel, List<OrderEntryModel> orderEntryModels) {
        OrderModel createdOrderModel = createOrder(orderModel);
        saveOrderEntries(createdOrderModel, orderEntryModels);
        promoteUserToGoldenIfConditionMet(orderModel.getUserModel().getId());
        return createdOrderModel;
    }

    private OrderModel createOrder(OrderModel orderModel) {
        orderModel.setOrderStatusModel(getPendingOrderStatusModel());
        return orderRepository.save(orderModel);
    }

    private void saveOrderEntries(OrderModel createdOrderModel, List<OrderEntryModel> orderEntryModels) {
        orderEntryModels.forEach(orderEntryModel -> orderEntryModel.setOrder(createdOrderModel));
        orderEntryRepository.saveAll(orderEntryModels);
        createdOrderModel.setOrderEntryModels(orderEntryModels);
    }

    private void promoteUserToGoldenIfConditionMet(int userId) {
        if (isGoldenCustomer(userId)) {
            return;
        }

        List<OrderModel> completedOrders = getAllCompletedOrdersByUserId(userId);
        double totalSum = getTotalSum(completedOrders);
        if (totalSum >= goldenCustomerThreshold) {
            userService.addRoleToUser("ROLE_GOLDEN_CUSTOMER", userId);
        }
    }

    private boolean isGoldenCustomer(int userId) {
        return userService.getById(userId)
                .getRoleModels()
                .stream()
                .anyMatch(roleModel -> roleModel.getName().equals("ROLE_GOLDEN_CUSTOMER"));
    }

    private List<OrderModel> getAllCompletedOrdersByUserId(int userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .filter(orderModel -> orderModel.getOrderStatusModel().getName().equals("COMPLETED"))
                .collect(Collectors.toList());
    }

    private double getTotalSum(List<OrderModel> completedOrders) {
        return getOrderEntries(completedOrders)
                .stream()
                .mapToDouble(orderEntryModel -> orderEntryModel.getBookModel().getPrice() * orderEntryModel.getQuantity())
                .sum();
    }

    private List<OrderEntryModel> getOrderEntries(List<OrderModel> completedOrders) {
        return completedOrders.stream()
                .flatMap(orderModel -> orderModel.getOrderEntryModels().stream())
                .collect(Collectors.toList());
    }

    private OrderStatusModel getPendingOrderStatusModel() {
        return orderStatusRepository.findByName("PENDING");
    }
}
