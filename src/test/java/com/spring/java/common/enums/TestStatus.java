package com.spring.java.common.enums;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public enum TestStatus {
    SUCCESS(MockMvcResultMatchers.status().isOk());

    public ResultMatcher matcher;
    TestStatus(ResultMatcher matcher) {
        this.matcher = matcher;
    }
}
