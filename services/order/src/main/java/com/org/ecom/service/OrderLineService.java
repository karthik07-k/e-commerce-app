package com.org.ecom.service;

import com.org.ecom.model.OrderLineRequest;
import com.org.ecom.repository.OrderLineRepository;
import com.org.ecom.utils.OrderLineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return repository.save(order).getId();
    }
}
