package com.org.ecom.utils;


import com.org.ecom.model.OrderLine;
import com.org.ecom.model.OrderLineRequest;
import com.org.ecom.order.Order;
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
}
