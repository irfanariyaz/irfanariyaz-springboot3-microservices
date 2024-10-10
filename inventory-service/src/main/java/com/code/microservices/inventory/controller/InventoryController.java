package com.code.microservices.inventory.controller;

import com.code.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        System.out.printf("SkuCode of order:"+ skuCode);
        Boolean res= inventoryService.inStock(skuCode,quantity);
        System.out.println(res);
        return res;
    }
}
