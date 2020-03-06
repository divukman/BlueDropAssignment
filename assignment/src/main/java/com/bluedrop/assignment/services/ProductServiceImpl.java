package com.bluedrop.assignment.services;

import com.bluedrop.assignment.entities.Product;
import com.bluedrop.assignment.repositories.ProductRepository;
import com.bluedrop.assignment.web.controllers.NotFoundException;
import com.bluedrop.assignment.web.mappers.ProductMapper;
import com.bluedrop.assignment.web.models.ProductDto;
import com.bluedrop.assignment.web.models.ProductPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Returns the list of products.
     *
     * @param name - Product name. Can be null or empty, then returns the page request.
     *               We assume there can be many products of the same name (!?!)
     * @param pageRequest - Page request
     * @return productPagedList - A list of product DTO objects
     */
    @Override
    public ProductPagedList listProducts(String name, PageRequest pageRequest) {
        ProductPagedList productPagedList;
        Page<Product> productPage;

        if (!StringUtils.isEmpty(name)) {
            productPage = productRepository.findAllByName(name, pageRequest);
        } else {
            productPage = productRepository.findAll(pageRequest);
        }

        final List<ProductDto> lstProductDtos = productPage.getContent().stream().map(productMapper::productToProductDto).collect(Collectors.toList());
        productPagedList = new ProductPagedList(
                                lstProductDtos,
                                PageRequest.of(productPage.getPageable().getPageNumber(), productPage.getPageable().getPageSize()),
                                lstProductDtos.size()
                                );

        return productPagedList;
    }


    @Override
    public List<ProductDto> listAllProducts() {
        return productRepository.findAll().stream().map(productMapper::productToProductDto).collect(Collectors.toList());
    }

    /**
     * Gets the product by product Id.
     * @param productId
     * @return product dto object
     */
    @Override
    public ProductDto getById(UUID productId) {
        return productMapper.productToProductDto(productRepository.findById(productId).orElseThrow(NotFoundException::new));
    }

    /**
     * Saves the new product
     * @param productDto
     * @return - product dto object
     */
    @Override
    public ProductDto saveNewProduct(ProductDto productDto) {
        return productMapper.productToProductDto(productRepository.save(productMapper.productDtoToProduct(productDto)));
    }

    /**
     * Updates the product.
     * @param productId
     * @param productDto
     * @return updated product's dto object
     */
    @Override
    public ProductDto updateProduct(UUID productId, ProductDto productDto) {
        final Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setSku(product.getSku());

        return productMapper.productToProductDto(productRepository.save(product));
    }

    /**
     * Gets the product by the SKU
     * @param sku
     * @return product dto object
     */
    @Override
    public ProductDto getBySku(String sku) {
        return productMapper.productToProductDto(productRepository.findBySku(sku).orElseThrow(NotFoundException::new));
    }

}
