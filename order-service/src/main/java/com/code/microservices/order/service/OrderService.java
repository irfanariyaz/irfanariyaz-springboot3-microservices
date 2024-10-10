package com.code.microservices.order.service;

import com.code.microservices.order.client.InventoryClient;
import com.code.microservices.order.dto.OrderRequest;
//import com.code.microservices.order.event.OrderPlacedEvent;
import com.code.microservices.order.event.OrderPlacedEvent;
import com.code.microservices.order.model.Order;
import com.code.microservices.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private  final InventoryClient inventoryClient;
    private  final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public  void  placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        System.out.println("isProductInStock"+isProductInStock);
        //map order request to order object
        if (isProductInStock ) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            //save order to repo
            orderRepository.save(order);
            //send message to kafka topic
            //order number ,email
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
            log.info("start sending order placed event{} to kafka topic",orderPlacedEvent);
            kafkaTemplate.send("order-placed",orderPlacedEvent);
            log.info("Order Placed Successfully");


        }else {

            throw new RuntimeException("Product is not in stock, please try again later");
        }


    }

}
