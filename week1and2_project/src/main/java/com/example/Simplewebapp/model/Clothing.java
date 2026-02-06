package com.example.Simplewebapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Clothing extends Product {
    private String size;

    public Clothing(int prodId, String prodName, int prodPrice, String size) {
        super(prodId, prodName, prodPrice);
        this.size = size;
    }
}