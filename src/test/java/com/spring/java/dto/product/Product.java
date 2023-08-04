package com.spring.java.dto.product;

import com.spring.java.core.utils.FakerUtil;

/*
 * Product Test Data
 */
public class Product {

    public static ProductDTO getData() {
        return ProductDTO.builder()
                .price(FakerUtil.getPrice(1000, 100000))
                .name(FakerUtil.faker.name().name())
                .regDate(FakerUtil.getPast())
                .build();
    }
}