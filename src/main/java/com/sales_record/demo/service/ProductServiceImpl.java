package com.sales_record.demo.service;

import com.sales_record.demo.exception.ResourceNotFoundException;
import com.sales_record.demo.model.CartLine;
import com.sales_record.demo.model.OrderDetail;
import com.sales_record.demo.model.Product;
import com.sales_record.demo.repository.OrderDetailRepository;
import com.sales_record.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

    @Override
    public Product updateProduct(Long productId, Product productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        product.setModel(productRequest.getModel());
        product.setGender(productRequest.getGender());
        product.setQuality(productRequest.getQuality());
        product.setSize(productRequest.getSize());
        product.setColor(productRequest.getColor());
        product.setPrice(productRequest.getPrice());
        product.setAvailable(productRequest.getAvailable());
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<CartLine> getAllProductsByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        List<CartLine> cartLine = new ArrayList<>();

        for (OrderDetail order: orderDetails) {
            CartLine info = new CartLine();
            Product product = order.getProduct();
            info.setModel(product.getModel());
            info.setQuality(product.getQuality());
            info.setGender(product.getGender());
            info.setColor(product.getColor());
            info.setSize(product.getSize());
            long e = order.getId();
            info.setId(e);
            double price = product.getPrice();
            info.setPrice(price);
            int i = order.getQuantity();
            info.setQuantity(i);
            cartLine.add(info);
        }

        return cartLine;
    }
}
