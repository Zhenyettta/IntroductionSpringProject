package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.dto.CategoryDTO;
import com.naukma.introductionspringproject.dto.OrderDTO;
import com.naukma.introductionspringproject.service.CategoryService;
import com.naukma.introductionspringproject.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@Validated
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.readOrder(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> updateCategory(@Valid @RequestBody OrderDTO order){
        orderService.updateOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody OrderDTO category){
        orderService.createOrder(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
