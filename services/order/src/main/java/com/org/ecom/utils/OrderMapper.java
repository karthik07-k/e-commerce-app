package com.org.ecom.utils;

import com.org.ecom.model.OrderRequest;
import com.org.ecom.model.OrderResponse;
import com.org.ecom.order.Order;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(@Valid OrderRequest request) {
        return Order.builder()
                .orderId(request.id())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
