package com.code.microservices.product.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductResponse(
         String id,
         String name,
         String description,
         String skuCode,
         BigDecimal price
) {
}
