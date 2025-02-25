package com.unistock.model;

import lombok.Data;

@Data
public class Product {

    private Integer id;
    private String code;
    private String name;
    private Double price;

    public Product(Integer id, String code, String name, Double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
