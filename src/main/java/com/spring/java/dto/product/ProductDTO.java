package com.spring.java.dto.product;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(callSuper = true)
public class ProductDTO {
  private Integer id;
  private String name;
  private int price;
  private LocalDateTime regDate;
}
