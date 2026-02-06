package com.example.Simplewebapp.services;

import com.example.Simplewebapp.model.Product;

import java.util.List;

public interface ProductOperations {
    List<Product> getProducts();
    Product getProductById(int id);
    void addProduct(Product p);
}