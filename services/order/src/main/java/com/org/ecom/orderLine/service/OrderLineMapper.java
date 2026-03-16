package com.org.ecom.orderLine.service;


import com.org.ecom.order.Order;
import com.org.ecom.orderLine.model.OrderLine;
import com.org.ecom.orderLine.model.OrderLineRequest;
import com.org.ecom.orderLine.model.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequst) {
        return OrderLine.builder()
                .id(orderLineRequst.id())
                .productId(orderLineRequst.productId())
                .order(
                        Order.builder()
                                .orderId(orderLineRequst.orderId())
                                .build()
                )
                .quantity(orderLineRequst.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLineResponse orderLineResponse) {
        return new OrderLineResponse(
                orderLineResponse.id(),
                orderLineResponse.quantity()
        );
    }
}
