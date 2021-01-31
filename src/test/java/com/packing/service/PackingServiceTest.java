package com.packing.service;

import com.packing.dto.CaseDto;
import com.packing.dto.OrderlineDto;
import com.packing.dto.ProductDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class PackingServiceTest {

    @TestConfiguration
    static class PackingServiceTestContextConfiguration {

        @Bean
        public PackingService packingService() {
            return new PackingService();
        }
    }


    @Autowired
    private PackingService packingService;

    @Test
    void getProductsFromOrderlines() {
        ProductDto productDto = new ProductDto();
        productDto.setSizeX(4);
        productDto.setSizeY(4);
        productDto.setSizeZ(4);

        List<ProductDto> expectedProducts = new ArrayList<>();
        expectedProducts.add(productDto);
        expectedProducts.add(productDto);
        expectedProducts.add(productDto);
        expectedProducts.add(productDto);
        expectedProducts.add(productDto);

        OrderlineDto orderlineDto = new OrderlineDto();
        orderlineDto.setProduct(productDto);
        orderlineDto.setNumberOfProducts(5);
        List<OrderlineDto> orderlineDtos = new ArrayList<>();
        orderlineDtos.add(orderlineDto);

        List<ProductDto> products = packingService.getProductsFromOrderlines(orderlineDtos);

        Assert.assertEquals(expectedProducts, products);
    }

    @Test
    void testSavingIntoDB() {
        CaseDto caseDto = new CaseDto();
        caseDto.setSizeX(4);
        caseDto.setSizeY(4);
        caseDto.setSizeZ(4);

        ProductDto productDto = new ProductDto();
        productDto.setSizeX(4);
        productDto.setSizeY(4);
        productDto.setSizeZ(3);

        ProductDto productDto1 = new ProductDto();
        productDto1.setSizeX(2);
        productDto1.setSizeY(2);
        productDto1.setSizeZ(4);

        List<ProductDto> caseProducts = new ArrayList<>();
        caseProducts.add(productDto);
        caseProducts.add(productDto1);

        caseDto.setProducts(caseProducts);

        CaseDto caseDto1 = new CaseDto();
        caseDto.setSizeX(4);
        caseDto.setSizeY(4);
        caseDto.setSizeZ(3);

        caseProducts = new ArrayList<>();
        caseProducts.add(productDto);

        caseDto1.setProducts(caseProducts);

        List<CaseDto> cases = new ArrayList<>();
        cases.add(caseDto);
        cases.add(caseDto1);

        packingService.saveIntoDB(cases);


    }

    @Test
    void packTwoProductsIntoThreeCases() {
        CaseDto caseDto = new CaseDto();
        caseDto.setSizeX(4);
        caseDto.setSizeY(4);
        caseDto.setSizeZ(4);

        List<CaseDto> expectedCases = new ArrayList<>();
        expectedCases.add(caseDto);

        CaseDto caseDto1 = new CaseDto();
        caseDto1.setSizeX(4);
        caseDto1.setSizeY(3);
        caseDto1.setSizeZ(3);

        CaseDto caseDto2 = new CaseDto();
        caseDto2.setSizeX(2);
        caseDto2.setSizeY(2);
        caseDto2.setSizeZ(4);

        List<CaseDto> cases = new ArrayList<>();
        cases.add(caseDto2);
        cases.add(caseDto);
        cases.add(caseDto1);

        ProductDto productDto = new ProductDto();
        productDto.setSizeX(4);
        productDto.setSizeY(4);
        productDto.setSizeZ(3);

        ProductDto productDto1 = new ProductDto();
        productDto1.setSizeX(2);
        productDto1.setSizeY(2);
        productDto1.setSizeZ(4);

        List<ProductDto> products = new ArrayList<>();
        products.add(productDto);
        products.add(productDto1);

        List<CaseDto> packingCases = packingService.packProducts(cases, products);

        Assert.assertEquals(expectedCases, packingCases);
    }

    @Test
    void packTwoOrdersIntoThreeCases() {
        ProductDto productDto = new ProductDto();
        productDto.setSizeX(4);
        productDto.setSizeY(4);
        productDto.setSizeZ(3);

        ProductDto productDto1 = new ProductDto();
        productDto1.setSizeX(2);
        productDto1.setSizeY(2);
        productDto1.setSizeZ(4);

        OrderlineDto orderlineDto = new OrderlineDto();
        orderlineDto.setNumberOfProducts(2);
        orderlineDto.setProduct(productDto);

        OrderlineDto orderlineDto1 = new OrderlineDto();
        orderlineDto1.setNumberOfProducts(1);
        orderlineDto1.setProduct(productDto1);

        List<OrderlineDto> orderlines = new ArrayList<>();
        orderlines.add(orderlineDto);
        orderlines.add(orderlineDto1);

        CaseDto caseDto = new CaseDto();
        caseDto.setSizeX(4);
        caseDto.setSizeY(4);
        caseDto.setSizeZ(4);

        CaseDto caseDto1 = new CaseDto();
        caseDto1.setSizeX(5);
        caseDto1.setSizeY(4);
        caseDto1.setSizeZ(3);

        CaseDto caseDto2 = new CaseDto();
        caseDto2.setSizeX(2);
        caseDto2.setSizeY(2);
        caseDto2.setSizeZ(4);

        List<CaseDto> cases = new ArrayList<>();
        cases.add(caseDto);
        cases.add(caseDto2);
        cases.add(caseDto1);

        List<CaseDto> expectedCases = new ArrayList<>();
        expectedCases.add(caseDto);
        expectedCases.add(caseDto1);

        List<CaseDto> packedCases = packingService.packOrders(orderlines, cases);

        Assert.assertEquals(expectedCases, packedCases);
    }

}