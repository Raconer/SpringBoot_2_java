package com.spring.java.mapper;

import com.spring.java.dto.product.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    ProductDTO get();
}
