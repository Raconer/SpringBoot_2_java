package com.spring.java.dto.sign.map;

import com.spring.java.dto.sign.SignDTO;
import com.spring.java.dto.sign.SignUpDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SignMap {
    SignMap INSTANCE = Mappers.getMapper(SignMap.class);

    SignDTO signUpToSign(SignUpDTO signUpDTO);
}
