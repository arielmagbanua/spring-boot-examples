package com.example.ecommerce.controllers;

import com.example.ecommerce.models.Product;
import com.example.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String apiGreet() {
        return "Welcome to Ecommerce API!";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProduct(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PostMapping("product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("addProducts")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        List<Product> createdProducts = productService.addProducts(products);

        if (createdProducts == null || createdProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // no products created
        }

        return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
    }

    @PutMapping("product")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(String keyword) {
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }
}
