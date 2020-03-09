package com.bluedrop.assignment.web.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDto implements Serializable {
    static final long serialVersionUID = 9033452488635021259L;

    @NotNull
    private String sku;

    @NotNull
    Integer quantity;
}
