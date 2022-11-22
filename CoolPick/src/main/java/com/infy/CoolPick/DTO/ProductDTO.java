package com.infy.CoolPick.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class ProductDTO {

    private Integer id;
    private String productVendor;
    @Size(min=5, max=10, message = "{product.name.length}")
    private String productName;
    private Double productPrice;
    private Integer productStock;

}
