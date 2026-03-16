package com.org.ecom.controller;

import com.org.ecom.model.OrderRequest;
import com.org.ecom.model.OrderResponse;
import com.org.ecom.order.Order;
import com.org.ecom.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService service;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(service.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("orderId") Integer orderId){
        return ResponseEntity.ok(service.findById(orderId));
    }
}
