package com.org.ecom.service;

import com.org.ecom.exception.CustomerNotFoundException;
import com.org.ecom.model.OrderLineRequest;
import com.org.ecom.model.OrderRequest;
import com.org.ecom.model.PurchaseRequest;
import com.org.ecom.order.Order;
import com.org.ecom.remote.CustomerClient;
import com.org.ecom.remote.ProductClient;
import com.org.ecom.remote.model.PurchaseResponse;
import com.org.ecom.repository.OrderRepository;
import com.org.ecom.utils.OrderMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    public Order createOrder(@Valid OrderRequest request) {
        //check the customer --> customer ms
            var customerResponse = customerClient.getCustomer(request.customerId());
            if (customerResponse != null) {
                throw new CustomerNotFoundException("Customer not found, so cannot create order for a customer that does not exist");
            }
        //purchase the products --> product ms
            var purchaseResponse = purchaseProducts(request.products());

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
        //TODO start payment process - payment ms

        //send the order confirmation --> notification ms (kafka)

        return null;
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
}
