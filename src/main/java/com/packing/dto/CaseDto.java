package com.packing.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Data
public class CaseDto {
    private Integer sizeX;

    private Integer sizeY;

    private Integer sizeZ;

    private List<ProductDto> products;

    private double volume;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseDto caseDto = (CaseDto) o;
        return sizeX.equals(caseDto.sizeX) &&
                sizeY.equals(caseDto.sizeY) &&
                sizeZ.equals(caseDto.sizeZ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeX, sizeY, sizeZ);
    }
}
