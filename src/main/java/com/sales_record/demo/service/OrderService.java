package com.sales_record.demo.service;

import com.sales_record.demo.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> deleteOrder(Long orderId);
    Order updateOrder(Long orderId, Order orderRequest);
    Order createOrder(Order order);
    Order getOrderById(Long orderId);
    Page<Order> getAllOrders(Pageable pageable);
}
