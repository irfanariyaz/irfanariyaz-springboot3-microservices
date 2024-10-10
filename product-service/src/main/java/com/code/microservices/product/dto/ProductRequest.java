package com.code.microservices.product.dto;

import javax.swing.*;
import java.math.BigDecimal;

public record ProductRequest(String id, String name, String description, String skuCode ,BigDecimal price) {
}
