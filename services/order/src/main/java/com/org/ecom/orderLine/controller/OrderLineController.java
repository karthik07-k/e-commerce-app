package com.org.ecom.orderLine.controller;

import com.org.ecom.orderLine.model.OrderLineResponse;
import com.org.ecom.orderLine.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findAllByOrderId(@PathVariable("orderId") Integer orderId) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }
}
