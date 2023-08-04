package com.spring.java.service;

import com.spring.java.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
  private ProductMapper productMapper;

  public int get() {
    return this.productMapper.get();
  }
}
