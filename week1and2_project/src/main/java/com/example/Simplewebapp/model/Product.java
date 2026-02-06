package com.example.Simplewebapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product {
    private int prodId;
    private String prodName;
    private int prodPrice;
}