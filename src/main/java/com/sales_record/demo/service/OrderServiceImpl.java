package com.sales_record.demo.service;

import com.sales_record.demo.exception.ResourceNotFoundException;
import com.sales_record.demo.model.Order;
import com.sales_record.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
        orderRepository.delete(order);
        return ResponseEntity.ok().build();
    }

    @Override
    public Order updateOrder(Long orderId, Order orderRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setPaymentAgainstDelivery(orderRequest.getPaymentAgainstDelivery());
        order.setPayment(orderRequest.getPayment());
        order.setAmount(orderRequest.getAmount());
        order.setState(orderRequest.getState());
        order.setGeneratedDate(orderRequest.getGeneratedDate());
        order.setDeliveryDate(orderRequest.getDeliveryDate());
        order.setDeliveryPrice(orderRequest.getDeliveryPrice());
        return orderRepository.save(order);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
