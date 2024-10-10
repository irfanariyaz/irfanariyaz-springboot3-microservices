package com.code.microservices.product.service;

import com.code.microservices.product.dto.ProductRequest;
import com.code.microservices.product.dto.ProductResponse;
import com.code.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.code.microservices.product.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private  final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        // Implement validation, business logic, and save the product to the database
        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .skuCode(productRequest.skuCode())
                .build();
        productRepository.save(product);
        log.info("product created successfully");
        return buildProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
       return products.stream().map(this::buildProductResponse).toList();
    }
    public ProductResponse buildProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .skuCode(product.getSkuCode())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
