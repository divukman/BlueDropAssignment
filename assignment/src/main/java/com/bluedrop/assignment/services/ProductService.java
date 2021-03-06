package com.bluedrop.assignment.services;

import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.web.models.ProductDto;
import com.bluedrop.assignment.web.models.ProductPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductPagedList listProducts(String name, PageRequest pageRequest, State productState);

    List<ProductDto> listAllProducts(State productState);

    ProductDto getById(UUID productId);

    ProductDto saveNewProduct(ProductDto productDto);

    ProductDto updateProduct(UUID productId, ProductDto productDto);

    ProductDto getBySku(String sku);

    void deleteProductById(UUID productId);
}
