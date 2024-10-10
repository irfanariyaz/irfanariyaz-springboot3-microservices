package com.code.microservices.inventory.service;

import com.code.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private  final InventoryRepository inventoryRepository;

    public Boolean inStock(String skuCode,Integer quantity) {
        //find an inventory for a given skuCode where quantity >=0

        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }
}
