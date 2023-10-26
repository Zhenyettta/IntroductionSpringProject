package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.dto.OrderDTO;
import com.naukma.introductionspringproject.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
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

    @PutMapping
    public ResponseEntity<Object> updateOrder(@Valid @RequestBody OrderDTO order){
        orderService.updateOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderDTO category){
        orderService.createOrder(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

