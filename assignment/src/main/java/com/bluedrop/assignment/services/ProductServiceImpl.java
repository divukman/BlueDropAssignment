package com.bluedrop.assignment.services;

import com.bluedrop.assignment.entities.Product;
import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.repositories.ProductRepository;
import com.bluedrop.assignment.web.exception.NotFoundException;
import com.bluedrop.assignment.web.mappers.ProductMapper;
import com.bluedrop.assignment.web.models.ProductDto;
import com.bluedrop.assignment.web.models.ProductPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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
     * @param productState - State of the product: Active or Deleted
     * @return productPagedList - A list of product DTO objects
     */
    @Override
    public ProductPagedList listProducts(final String name, final PageRequest pageRequest, final State productState) {
        ProductPagedList productPagedList;
        Page<Product> productPage;

        if (!StringUtils.isEmpty(name)) {
            productPage = productRepository.findAllByNameAndState(name, productState, pageRequest);
        } else {
            productPage = productRepository.findAllByState(productState, pageRequest);
        }

        final List<ProductDto> lstProductDtos = productPage.getContent().stream().map(productMapper::productToProductDto).collect(Collectors.toList());
        productPagedList = new ProductPagedList(
                                lstProductDtos,
                                PageRequest.of(productPage.getPageable().getPageNumber(), productPage.getPageable().getPageSize()),
                                lstProductDtos.size()
                                );

        return productPagedList;
    }


    /**
     * Find all products with a given state.
     * @param productState - Active or Deleted
     * @return - A list of product dto objects
     */
    @Override
    public List<ProductDto> listAllProducts(final State productState) {
        return productRepository.findAllByState(productState).stream().map(productMapper::productToProductDto).collect(Collectors.toList());
    }

    /**
     * Gets the product by product Id.
     * @param productId
     * @return product dto object
     */
    @Override
    public ProductDto getById(UUID productId) {
        return productMapper.productToProductDto(productRepository.findById(productId).orElseThrow( () -> new NotFoundException(String.format("Product with UUID: %s not found.", productId.toString())) ));
    }

    /**
     * Saves the new product
     * @param productDto
     * @return - product dto object
     */
    @Override
    public ProductDto saveNewProduct(ProductDto productDto) {
        final Product product = productMapper.productDtoToProduct(productDto);
        product.setState(State.ACTIVE);

        return productMapper.productToProductDto(productRepository.save(product));
    }

    /**
     * Updates the product.
     * @param productId
     * @param productDto
     * @return updated product's dto object
     */
    @Override
    public ProductDto updateProduct(UUID productId, ProductDto productDto) {
        final Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(String.format("Product with UUID: %s not found.", productId.toString())) );

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
        return productMapper.productToProductDto(productRepository.findBySku(sku).orElseThrow(() -> new NotFoundException(String.format("Product with SKU: %s not found.", sku))));
    }


    /**
     * Performs a soft deletion of a product (Updates product state to DELETED).
     * @param productId
     */
    @Override
    public void deleteProductById(UUID productId) {
        final Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(String.format("Product with UUID: %s not found.", productId.toString())));
        product.setState(State.DELETED);
        productRepository.save(product);
    }

}
