package com.spring.java.mapper;

import com.spring.java.dto.sign.SignDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {
    int insert(SignDTO signDTO);
}