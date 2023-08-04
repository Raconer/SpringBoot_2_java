package com.spring.java.dto.product;

import com.spring.java.core.utils.FakerUtil;

/*
 * Product Test Data
 */
public class Product {

  public static ProductDTO getData() {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setPrice(FakerUtil.getPrice(1000, 100000));
    productDTO.setName(FakerUtil.faker.name().name());
    productDTO.setRegDate(FakerUtil.getPast().atStartOfDay());

    return productDTO;
  }
}
