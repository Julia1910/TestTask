package com.packing.service;

import com.packing.dao.PackingRepository;
import com.packing.dto.CaseDto;
import com.packing.dto.OrderlineDto;
import com.packing.dto.ProductDto;
import com.packing.model.Case;
import com.packing.model.Packing;
import com.packing.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackingService {

    @Autowired
    private PackingRepository packingRepository;

    @Autowired
    private CaseService caseService;

    @Autowired
    private ProductService productService;


    public List<CaseDto> packOrders(final List<OrderlineDto> orderlines, final List<CaseDto> cases) {
        List<ProductDto> products = getProductsFromOrderlines(orderlines);
        List<CaseDto> packedCases = packProducts(cases, products);
        saveIntoDB(packedCases);
        return packedCases;
    }

    public void saveIntoDB(List<CaseDto> cases) {
        List<Packing> packings = new ArrayList<>();
        for (CaseDto caseDto : cases) {
            Case caseEntity = new Case();
            caseEntity.setSizeX(caseDto.getSizeX());
            caseEntity.setSizeY(caseDto.getSizeY());
            caseEntity.setSizeZ(caseDto.getSizeZ());
            caseService.add(caseEntity);
            for (ProductDto productDto : caseDto.getProducts()) {
                Product product = new Product();
                product.setSizeX(productDto.getSizeX());
                product.setSizeY(productDto.getSizeY());
                product.setSizeZ(productDto.getSizeZ());
                productService.add(product);
                Packing packing = new Packing();
                packing.setACase(caseEntity);
                packing.setProduct(product);
                packingRepository.save(packing);
                packings.add(packing);
            }
        }
    }

    public List<CaseDto> packProducts(final List<CaseDto> cases, final List<ProductDto> products) {
        List<CaseDto> packedCases = new ArrayList<>();
        for (CaseDto caseDto : cases) {
            caseDto.setVolume(getVolume(caseDto));
        }
        List<CaseDto> sortedCases = sortCases(cases);
        Iterator<ProductDto> productDtoIterator = products.listIterator();
        while (productDtoIterator.hasNext()) {
            ProductDto product = productDtoIterator.next();
            product.setVolume(getVolume(product));
            for (CaseDto caseDto : sortedCases) {
                if (!packedCases.isEmpty()) {
                    for (int j = 0; j < packedCases.size(); j++) {
                        if (product.getVolume() <= packedCases.get(j).getVolume()) {
                            if (products.contains(product)) {
                                packedCases.add(addProductToCase(packedCases.get(j), product));
                                productDtoIterator.remove();
                            }
                            break;
                        }
                    }
                }
                if ((product.getVolume() <= caseDto.getVolume())) {
                    if (products.contains(product)) {
                        packedCases.add(addProductToCase(caseDto, product));
                        productDtoIterator.remove();
                    }
                    break;
                }
            }
        }
        return packedCases.stream().distinct().collect(Collectors.toList());
    }

    private List<CaseDto> sortCases(List<CaseDto> cases) {
        return cases
                .stream()
                .sorted(Comparator.comparing(CaseDto::getVolume, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private CaseDto addProductToCase(CaseDto caseDto, ProductDto product) {
        List<ProductDto> packedProducts = new ArrayList<>();
        if (caseDto.getProducts() != null) {
            packedProducts.addAll(caseDto.getProducts());
        }
        packedProducts.add(product);
        caseDto.setProducts(packedProducts);
        caseDto.setVolume(caseDto.getVolume() - product.getVolume());
        return caseDto;
    }

    public List<ProductDto> getProductsFromOrderlines(List<OrderlineDto> orderlines) {
        List<ProductDto> products = new ArrayList<>();
        for (OrderlineDto orderline : orderlines) {
            for (int i = 0; i < orderline.getNumberOfProducts(); i++) {
                products.add(orderline.getProduct());
            }
        }
        return products;
    }

    private double getVolume(ProductDto product) {
        return product.getSizeX() * product.getSizeY() * product.getSizeZ();
    }

    private double getVolume(CaseDto caseDto) {
        return caseDto.getSizeX() * caseDto.getSizeY() * caseDto.getSizeZ();
    }


}
