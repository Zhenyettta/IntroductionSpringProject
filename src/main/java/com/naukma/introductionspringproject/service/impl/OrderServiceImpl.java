package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.entity.OrderEntity;
import com.naukma.introductionspringproject.model.Order;
import com.naukma.introductionspringproject.repository.OrderRepo;
import com.naukma.introductionspringproject.service.MealService;
import com.naukma.introductionspringproject.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;

    private final MealService mealService;
    private final OrderRepo orderRepo;

    public OrderServiceImpl(ModelMapper modelMapper, MealService mealService, OrderRepo orderRepo) {
        this.modelMapper = modelMapper;
        this.mealService = mealService;
        this.orderRepo = orderRepo;
    }

    @Override
    public Order createOrder(Order order) {
        orderRepo.save(modelMapper.map(order, OrderEntity.class));
        return order;
    }

    @Override
    public Order readOrder(Long id) {
        return modelMapper.map(orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found by id " + id)), Order.class);
    }

    @Override
    public void updateOrder(Order order) {
        OrderEntity orderNew = orderRepo.findById(order.getId()).orElseThrow(() -> new NotFoundException("Order not found by id " + order.getId()));
        orderNew.setOrderGiven(order.getOrderGiven());
        orderNew.setOrderTaken(order.getOrderTaken());
        orderRepo.save(orderNew);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepo.existsById(id))
            orderRepo.deleteById(id);
        else throw new NotFoundException("Order not found by id " + id);
    }
}
