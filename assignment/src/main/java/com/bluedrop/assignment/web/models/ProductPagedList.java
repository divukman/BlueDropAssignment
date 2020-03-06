package com.bluedrop.assignment.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProductPagedList extends PageImpl<ProductDto> implements Serializable {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProductPagedList(
                         @JsonProperty("content") List<ProductDto> content,
                         @JsonProperty("number") int number,
                         @JsonProperty("size") int size,
                         @JsonProperty("totalElements") Long totalElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public ProductPagedList(List<ProductDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ProductPagedList(List<ProductDto> content) {
        super(content);
    }
}
