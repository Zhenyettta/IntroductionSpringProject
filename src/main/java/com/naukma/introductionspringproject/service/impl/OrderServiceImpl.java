package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.dto.OrderDTO;
import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.entity.OrderEntity;
import com.naukma.introductionspringproject.repository.OrderRepo;
import com.naukma.introductionspringproject.service.MealService;
import com.naukma.introductionspringproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public OrderEntity createOrder(OrderDTO orderS) {
        OrderEntity order = new OrderEntity();
        order.setOrderGiven(LocalDateTime.of(2023, 9, 14, 12, 30));
        orderRepo.save(order);
        return order;
    }

    @Override
    public OrderEntity readOrder(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found by id " + id));
    }

    @Override
    public void updateOrder(OrderDTO order) {
        OrderEntity orderNew = orderRepo.findById(order.getId()).orElseThrow(() -> new NotFoundException("Order not found by id " + order.getId()));
        orderNew.setOrderGiven(order.getOrderGiven());
        orderNew.setOrderTaken(order.getOrderTaken());
        orderRepo.save(orderNew);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
