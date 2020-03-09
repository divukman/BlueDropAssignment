package com.bluedrop.assignment.web.mappers;

import com.bluedrop.assignment.entities.Product;
import com.bluedrop.assignment.web.models.ProductDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
}
