package com.spring.java.dto.sign;


public class Sign {
    private final static String EMAIL = "jean.wisozk@hotmail.com";
    private final static String TEMP_PASSWORD = "1q2w3e4r";

    public static SignInDTO getData() {
        return SignInDTO.builder()
                .email(EMAIL)
                .password(TEMP_PASSWORD)
                .build();
    }

    public static SignInDTO getNotfoundUserData() {
        return SignInDTO.builder()
                .email("false_" + EMAIL)
                .password(TEMP_PASSWORD)
                .build();
    }

    public static SignInDTO getNotMatchPWData() {
        return SignInDTO.builder()
                .email(EMAIL)
                .password(TEMP_PASSWORD + "!!")
                .build();
    }
}
