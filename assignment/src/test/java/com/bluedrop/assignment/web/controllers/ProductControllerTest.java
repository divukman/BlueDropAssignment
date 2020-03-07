package com.bluedrop.assignment.web.controllers;

import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.services.ProductService;
import com.bluedrop.assignment.web.models.ProductDto;
import com.bluedrop.assignment.web.models.ProductPagedList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductService productService;


    private ProductDto getProductDto() {
        final ProductDto productDto = ProductDto.builder()
                .name("Dummy Product")
                .price(new BigDecimal("99.99"))
                .sku("SKUDUMMY")
                .build();

        return productDto;
    }

    private List<ProductDto> getProductDtoList() {
        final List<ProductDto> lstProducts = new ArrayList<>(2);

        final ProductDto product1 = ProductDto.builder()
                .name("BMW i3")
                .sku("BMWSKU1")
                .price(new BigDecimal("11233.27"))
                .build();

        final ProductDto product2 = ProductDto.builder()
                .name("Tesla Model S")
                .sku("TeslaSKU")
                .price(new BigDecimal("32328.33"))
                .build();

        lstProducts.add(product1);
        lstProducts.add(product2);

        return  lstProducts;
    }

    @Test
    void listProducts() throws Exception {
        final List<ProductDto> lstProductDTOs = getProductDtoList();
        final ProductPagedList productPagedList = new ProductPagedList(
                lstProductDTOs,
                PageRequest.of(0, 10),
                lstProductDTOs.size()
        );

        when(productService.listProducts(any(), eq(PageRequest.of(0, 2)), eq(State.ACTIVE))).thenReturn(productPagedList);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("pageNumber", "0");
        requestParams.add("pageSize", "2");

        final String result = mockMvc.perform(
                get("/api/v1/product/")
                    .params(requestParams)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()
                    )
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.contains("BMWSKU1"));
        assertTrue(result.contains("TeslaSKU"));
    }

    @Test
    void geAllProducts() throws Exception {
        when(productService.listAllProducts(State.ACTIVE)).thenReturn(getProductDtoList());

        final String result = mockMvc.perform(get("/api/v1/product/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.contains("BMWSKU1"));
        assertTrue(result.contains("TeslaSKU"));
    }

    @Test
    void getProductById() throws Exception {
        given(productService.getById(any())).willReturn(getProductDto());

        mockMvc.perform(get("/api/v1/product/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getProductBySku() throws Exception {
        final ProductDto productDto = getProductDto();
        given(productService.getBySku(any())).willReturn(productDto);

        mockMvc.perform(get("/api/v1/product/sku/dummySKU")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value(productDto.getSku()));
    }

    @Test
    void saveNewProduct() throws Exception {
        final ProductDto productDto = getProductDto();
        final String strProductDtoJson = objectMapper.writeValueAsString(productDto);

        given(productService.saveNewProduct(any())).willReturn(productDto);

        mockMvc.perform(
                post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(strProductDtoJson)
        ).andExpect(status().isCreated());
    }

    @Test
    void updateProduct() throws Exception {
        final ProductDto productDto = getProductDto();
        given(productService.updateProduct(any(), any())).willReturn(productDto);

        final String strProductDtoJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(put("/api/v1/product/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(strProductDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/api/v1/product/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}