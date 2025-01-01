package com.test.order.repository;

import com.test.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo  extends JpaRepository<Order, Long> {

}

