package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Order;
import com.naukma.introductionspringproject.model.User;
import com.naukma.introductionspringproject.repository.OrderRepo;
import com.naukma.introductionspringproject.service.MealService;
import com.naukma.introductionspringproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {
    MealService mealService;
    OrderRepo orderRepo;

    @Autowired
    public void setMealServiceImpl(MealService mealService, OrderRepo orderRepo) {
        this.mealService = mealService;
        this.orderRepo = orderRepo;
    }

    @Override
    public Order createOrder() {
        Order order = new Order();
        order.setOrderGiven(LocalDateTime.of(2023, 9, 14, 12, 30));
        orderRepo.save(order);
        return order;
    }

    @Override
    public Order readOrder(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found by id " + id));
    }

    @Override
    public void updateOrder(Order order) {
        Order orderNew = orderRepo.findById(order.getId()).orElseThrow(() -> new NotFoundException("Order not found by id " + order.getId()));
        orderNew.setUser(order.getUser());
        orderNew.setMeals(order.getMeals());
        orderNew.setOrderGiven(order.getOrderGiven());
        orderNew.setOrderTaken(order.getOrderTaken());
        orderRepo.save(orderNew);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
