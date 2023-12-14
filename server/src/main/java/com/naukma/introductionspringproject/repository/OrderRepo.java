package com.naukma.introductionspringproject.repository;

import com.naukma.introductionspringproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
}
