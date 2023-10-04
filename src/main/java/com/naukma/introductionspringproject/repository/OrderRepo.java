package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
