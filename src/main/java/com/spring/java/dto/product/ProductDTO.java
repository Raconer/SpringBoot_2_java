package com.spring.java.dto.product;

import com.spring.java.dto.common.InfoDTO;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(callSuper = true)
public class ProductDTO extends InfoDTO {
    private Integer id;
    private String  name;
    private int     price;
}