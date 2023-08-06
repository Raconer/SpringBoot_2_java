package com.spring.java.dto.sign;

import com.spring.java.core.utils.FakerUtil;

public class SignUp {

    private final static String TEMP_PASSWORD = "1q2w3e4r";

    public static SignUpDTO getData() {
        return SignUpDTO.builder()
                .email(FakerUtil.faker.internet().emailAddress())
                .password(TEMP_PASSWORD)
                .name(FakerUtil.faker.name().fullName())
                .build();
    }

    public static SignUpDTO getValidData() {
        return SignUpDTO.builder()
                .email("")
                .password("")
                .name("")
                .build();
    }
}
