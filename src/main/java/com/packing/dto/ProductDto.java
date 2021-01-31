package com.packing.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDto {

    private Integer sizeX;

    private Integer sizeY;

    private Integer sizeZ;

    private double volume;
}
