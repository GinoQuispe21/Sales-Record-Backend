package com.sales_record.demo.service;

import com.sales_record.demo.exception.ResourceNotFoundException;
import com.sales_record.demo.model.Customer;
import com.sales_record.demo.model.Order;
import com.sales_record.demo.repository.CustomerRepository;
import com.sales_record.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> deleteOrder(Long customerId, Long orderId) {
        return orderRepository.findByIdAndCustomerId(orderId, customerId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Order not found with Id" + orderId + " and CustomerId " + customerId));
    }

    @Override
    public Order updateOrder(Long customerId, Long orderId, Order orderDetails) {
        if (!customerRepository.existsById(customerId))
            throw new ResourceNotFoundException("Customer", "Id", customerId);
        return orderRepository.findById(orderId).map(order -> {
            order.setPaymentMethod(orderDetails.getPaymentMethod());
            order.setPaymentAgainstDelivery(orderDetails.getPaymentAgainstDelivery());
            order.setPayment(orderDetails.getPayment());
            order.setAmount(orderDetails.getAmount());
            order.setState(orderDetails.getState());
            order.setGeneratedDate(orderDetails.getGeneratedDate());
            order.setDeliveryDate(orderDetails.getDeliveryDate());
            order.setDeliveryPrice(orderDetails.getDeliveryPrice());
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
    }

    @Override
    public Order createOrder(Long customerId, Order order) {
        return customerRepository.findById(customerId).map(customer -> {
            order.setCustomer(customer);
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    public Order getOrderByIdAndCustomerId(Long customerId, Long orderId) {
        return orderRepository.findByIdAndCustomerId(orderId, customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with Id" + orderId + " and CustomerId " + customerId
                ));
    }

    @Override
    public Page<Order> getAllOrdersByCustomerId(Long customerId, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
