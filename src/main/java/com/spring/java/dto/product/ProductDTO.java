package com.spring.java.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String  name;
    private int     price;
    private LocalDate regDate;
}
