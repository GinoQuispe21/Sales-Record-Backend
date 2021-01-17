package com.sales_record.demo.controller;

import com.sales_record.demo.model.Order;
import com.sales_record.demo.resource.OrderResource;
import com.sales_record.demo.resource.SaveOrderResource;
import com.sales_record.demo.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public Page<OrderResource> getAllOrders(Pageable pageable) {
        Page<Order> orderPage = orderService.getAllOrders(pageable);
        List<OrderResource> resources = orderPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/orders/{id}")
    public OrderResource getOrderById(@PathVariable(name = "id") Long orderId) {
        return convertToResource(orderService.getOrderById(orderId));
    }

    @PostMapping("/orders")
    public OrderResource createOrder(@Valid @RequestBody SaveOrderResource resource)  {
        Order order = convertToEntity(resource);
        return convertToResource(orderService.createOrder(order));
    }

    @PutMapping("/orders/{id}")
    public OrderResource updateOrder(@PathVariable(name = "id") Long orderId, @Valid @RequestBody SaveOrderResource resource) {
        Order order = convertToEntity(resource);
        return convertToResource(orderService.updateOrder(orderId, order));
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") Long orderId) {
        return orderService.deleteOrder(orderId);
    }

    private Order convertToEntity(SaveOrderResource resource) { return mapper.map(resource, Order.class); }

    private OrderResource convertToResource(Order entity) { return mapper.map(entity, OrderResource.class); }
}
