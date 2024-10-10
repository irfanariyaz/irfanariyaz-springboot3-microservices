package com.code.microservices.order.controller;

import com.code.microservices.order.dto.OrderRequest;
import com.code.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        System.out.println("orderRequest"+ orderRequest);
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
