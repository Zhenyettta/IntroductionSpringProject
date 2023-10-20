package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.dto.OrderDTO;
import com.naukma.introductionspringproject.entity.OrderEntity;

public interface OrderService {
    OrderEntity createOrder(OrderDTO order);
    OrderEntity readOrder(Long id);
    void updateOrder(OrderDTO order);
    void deleteOrder(Long id);
}
