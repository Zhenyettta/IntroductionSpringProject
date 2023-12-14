package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order readOrder(Long id);
    void updateOrder(Order order);
    void deleteOrder(Long id);
}
