package com.infy.CoolPick.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    private Integer id;
    private String productVendor;
    private String productName;
    private Double productPrice;
    private Integer productStock;


}
