package com.spring.java.controller;

import com.spring.java.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
  private ProductService productService;
  public ProductController(ProductService productService) {
    this.productService = productService;
  }
  @GetMapping
  public ResponseEntity<?> get() {
    this.productService.get();
    return ResponseEntity.ok("Success");
  }
}
