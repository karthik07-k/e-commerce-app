package com.org.ecom.controller;

import com.org.ecom.model.OrderRequest;
import com.org.ecom.order.Order;
import com.org.ecom.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService service;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(service.createOrder(request));
    }
}
