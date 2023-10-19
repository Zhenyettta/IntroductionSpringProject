package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.entity.OrderEntity;

public interface OrderService {
    OrderEntity createOrder();
    OrderEntity readOrder(Long id);
    void updateOrder(OrderEntity order);
    void deleteOrder(Long id);
}
