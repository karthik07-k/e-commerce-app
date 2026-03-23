package com.org.ecom.service;

import com.org.ecom.exception.OrderNotFoundException;
import com.org.ecom.kafka.OrderConfirmation;
import com.org.ecom.kafka.OrderProducer;
import com.org.ecom.orderLine.model.OrderLineRequest;
import com.org.ecom.model.OrderRequest;
import com.org.ecom.model.OrderResponse;
import com.org.ecom.model.PurchaseRequest;
import com.org.ecom.order.Order;
import com.org.ecom.orderLine.service.OrderLineService;
import com.org.ecom.remote.CustomerClient;
import com.org.ecom.remote.PaymentClient;
import com.org.ecom.remote.ProductClient;
import com.org.ecom.remote.model.PurchaseResponse;
import com.org.ecom.remote.model.remote.PaymentRequest;
import com.org.ecom.repository.OrderRepository;
import com.org.ecom.utils.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Order createOrder(@Valid OrderRequest request) {
        //check the customer --> customer ms
            var customer = customerClient.getCustomer(request.customerId()).getBody();
        //purchase the products --> product ms
            var purchaseProducts = purchaseProducts(request.products());

        //persist order - order ms
        var order = repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getOrderId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
            request.id(),
                request.amount(),
                request.paymentMethod(),
                order.getOrderId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation --> notification ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                       request.reference(),
                        order.getTotalAmount(),
                        order.getPaymentMethod(),
                        customer,
                        purchaseProducts
                )
        );
        return order;
    }

    private List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request) {
        try {
            List<PurchaseResponse> responseList = productClient.purchaseProducts(request);
            if (responseList != null) {
                return responseList;
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to purchase products: " + ex.getMessage(), ex);
        }
        return Collections.emptyList();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll().stream().map(mapper::fromOrder).toList();
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new OrderNotFoundException(
                        format("Order with id %d not found", orderId)
                ));
    }
}
