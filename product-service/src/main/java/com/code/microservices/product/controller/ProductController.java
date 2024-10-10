package com.code.microservices.product.controller;


import com.code.microservices.product.dto.ProductRequest;
import com.code.microservices.product.model.Product;

import com.code.microservices.product.dto.ProductResponse;
import com.code.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Create a new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  ProductResponse createProduct(@RequestBody ProductRequest request ){
      return productService.createProduct(request);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
//        try{
//            Thread.sleep(5000); // Simulate a delay of 5 seconds
//        }catch (InterruptedException e){
//            throw new RuntimeException();
//            }
        return  productService.getAllProducts();
    }


    // Implement other CRUD operations
}
