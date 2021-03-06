package com.sales_record.demo.controller;

import com.sales_record.demo.model.CartLine;
import com.sales_record.demo.model.Product;
import com.sales_record.demo.resource.ProductResource;
import com.sales_record.demo.resource.SaveProductResource;
import com.sales_record.demo.service.ProductService;
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
public class ProductController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Page<ProductResource> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productService.getAllProducts(pageable);
        List<ProductResource> resources = productPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/products/{id}")
    public ProductResource getProductById(@PathVariable(name = "id") Long productId) {
        return convertToResource(productService.getProductById(productId));
    }

    @PostMapping("/products")
    public ProductResource createProduct(@Valid @RequestBody SaveProductResource resource)  {
        Product product = convertToEntity(resource);
        return convertToResource(productService.createProduct(product));
    }

    @PutMapping("/products/{id}")
    public ProductResource updateProduct(@PathVariable(name = "id") Long productId, @Valid @RequestBody SaveProductResource resource) {
        Product product = convertToEntity(resource);
        return convertToResource(productService.updateProduct(productId, product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/orders/{orderId}/products")
    public List<CartLine> getAllArticlesByOrderId(@PathVariable(name = "orderId") Long orderId) {
        return productService.getAllProductsByOrderId(orderId);
    }

    private Product convertToEntity(SaveProductResource resource) { return mapper.map(resource, Product.class); }

    private ProductResource convertToResource(Product entity) { return mapper.map(entity, ProductResource.class); }
}
