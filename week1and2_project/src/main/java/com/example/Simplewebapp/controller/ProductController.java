package com.example.Simplewebapp.controller;

import com.example.Simplewebapp.model.Clothing;
import com.example.Simplewebapp.model.Electronics;
import com.example.Simplewebapp.model.Product;
import com.example.Simplewebapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }
    @GetMapping("/products/{prodId}")
    public Product getProductById(@PathVariable int prodId){
        return service.getProductById(prodId);
    }

    @PostMapping("/products/electronics")
    public void addElectronicsProduct(@RequestBody Electronics prod) {
        service.addProduct(prod);
    }

    @PostMapping("/products/clothing")
    public void addClothingProduct(@RequestBody Clothing prod) {
        service.addProduct(prod);
    }

    @GetMapping("/products/total-price")
    public int getTotalPrice() {
        return service.calculateTotalPrice(service.getProducts());
    }


    @GetMapping("/products/expensive")
    public List<Product> getExpensiveProducts() {
        return service.getFilteredProducts(product -> product.getProdPrice() > 2000);
    }
}