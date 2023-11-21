package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.HttpStatuses;
import com.naukma.introductionspringproject.dto.OrderDTO;
import com.naukma.introductionspringproject.model.Order;
import com.naukma.introductionspringproject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Validated
@io.swagger.v3.oas.annotations.tags.Tag(name = "Order Management", description = "Operations pertaining to order in Order Management")
public class OrderController {
    private final ModelMapper modelMapper;
    private final OrderService orderService;

    public OrderController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an order by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> getById(@Parameter(description = "Id value for the order you need to retrieve") @PathVariable Long id) {
        return new ResponseEntity<>(orderService.readOrder(id), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> updateOrder(@Parameter(description = "Update order object") @Valid @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(modelMapper.map(orderDTO, Order.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "409", description = HttpStatuses.CONFLICT)
    })
    public ResponseEntity<Object> createOrder(@Parameter(description = "Create order object") @Valid @RequestBody OrderDTO orderDTO) {
        orderService.createOrder(modelMapper.map(orderDTO, Order.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> deleteOrder(@Parameter(description = "Id value for the order you want to delete") @PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

