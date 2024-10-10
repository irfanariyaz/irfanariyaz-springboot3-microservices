package com.code.microservices.inventory.repository;


import com.code.microservices.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    Boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode,Integer quantity);
}
