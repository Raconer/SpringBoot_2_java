package com.spring.java.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidInfo {
    private String field;
    private String msg;
}
