package com.spring.java.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  public void get() {
    System.out.println("Product Service GET");
  }
}
