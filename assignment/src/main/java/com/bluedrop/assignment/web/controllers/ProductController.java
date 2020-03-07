package com.bluedrop.assignment.web.controllers;

import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.services.ProductService;
import com.bluedrop.assignment.web.models.ProductDto;
import com.bluedrop.assignment.web.models.ProductPagedList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/product")
@RestController
@RequiredArgsConstructor
@Api(tags = "Product")
public class ProductController {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final ProductService productService;


    @ApiOperation(value = "This endpoint is used to get the all product details")
    @GetMapping(produces = { "application/json" })
    public ResponseEntity<ProductPagedList> listProducts(@RequestParam(value = "pageNumber", required = false ) Integer pageNumber,
                                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(value = "name", required = false) String name) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        ProductPagedList productPagedList = productService.listProducts(name, PageRequest.of(pageNumber, pageSize), State.ACTIVE);
        return new ResponseEntity<>(productPagedList, HttpStatus.OK);
    }

    /**
     * Temp method just to support returning non-paginated data.
     * @return
     */
    @ApiOperation(value = "This endpoint is used to get the all product details. Does not use pagination.")
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> geAllProducts() {
        return new ResponseEntity<>(productService.listAllProducts(State.ACTIVE), HttpStatus.OK);
    }

    @ApiOperation(value = "This endpoint is used to get the product by the product id")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") final UUID productId) {
        return new ResponseEntity<>(productService.getById(productId), HttpStatus.OK);
    }

    @ApiOperation(value = "This endpoint is used to get the product by the product sku")
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductDto> getProductBySku(@PathVariable("sku") final String sku) {
        return new ResponseEntity<>(productService.getBySku(sku), HttpStatus.OK);
    }

    @ApiOperation(value = "This endpoint is used to create a new product")
    @PostMapping()
    public ResponseEntity saveNewProduct(@RequestBody @Validated ProductDto productDto) {
        return new ResponseEntity(productService.saveNewProduct(productDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "This endpoint is used to update a product")
    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable UUID productId, @RequestBody @Validated ProductDto productDto) {
        return new ResponseEntity(productService.updateProduct(productId, productDto), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "This endpoint is used to soft delete a product")
    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable UUID productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
