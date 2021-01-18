package com.sales_record.demo.service;

import com.sales_record.demo.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<?> deleteOrder(Long customerId, Long orderId);
    Order updateOrder(Long customerId, Long orderId, Order orderDetails);
    Order createOrder(Long customerId,Order order);
    Order getOrderByIdAndCustomerId(Long customerId, Long orderId);
    Page<Order> getAllOrdersByCustomerId(Long customerId, Pageable pageable);
    List<Order> getAllOrders();

}
