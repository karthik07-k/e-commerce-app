package com.org.ecom.repository;

import com.org.ecom.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public class OrderRepository extends JpaRepository<Order, Integer> {
}
