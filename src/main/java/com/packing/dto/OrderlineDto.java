package com.packing.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderlineDto {
    private Integer numberOfProducts;

    private ProductDto product;
}
