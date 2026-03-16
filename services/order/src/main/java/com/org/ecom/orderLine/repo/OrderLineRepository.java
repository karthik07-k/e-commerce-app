package com.org.ecom.orderLine.repo;

import com.org.ecom.orderLine.model.OrderLine;
import com.org.ecom.orderLine.model.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
