package com.example.Simplewebapp.services;

import com.example.Simplewebapp.model.Product;

@FunctionalInterface
public interface ProductFilter {
    boolean test(Product product);
}