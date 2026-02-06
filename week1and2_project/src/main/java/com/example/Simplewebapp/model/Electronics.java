package com.example.Simplewebapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Electronics extends Product {
    private String brand;

    public Electronics(int prodId, String prodName, int prodPrice, String brand) {
        super(prodId, prodName, prodPrice);
        this.brand = brand;
    }
}