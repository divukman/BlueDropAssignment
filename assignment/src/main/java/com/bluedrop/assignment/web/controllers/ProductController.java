package com.bluedrop.assignment.web.controllers;

import com.bluedrop.assignment.services.ProductService;
import com.bluedrop.assignment.web.models.ProductDto;
import com.bluedrop.assignment.web.models.ProductPagedList;
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
public class ProductController {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final ProductService productService;


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

        ProductPagedList productPagedList = productService.listProducts(name, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(productPagedList, HttpStatus.OK);
    }

    /**
     * Temp method just to support returning non-paginated data.
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> geAllProducts() {
        return new ResponseEntity<>(productService.listAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") final UUID productId) {
        return new ResponseEntity<>(productService.getById(productId), HttpStatus.OK);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductDto> getProductBySku(@PathVariable("sku") final String sku) {
        return new ResponseEntity<>(productService.getBySku(sku), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity saveNewProduct(@RequestBody @Validated ProductDto productDto) {
        return new ResponseEntity(productService.saveNewProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable UUID productId, @RequestBody @Validated ProductDto productDto) {
        return new ResponseEntity(productService.updateProduct(productId, productDto), HttpStatus.NO_CONTENT);
    }

}
