package com.spring.java.controller;

import com.spring.java.common.enums.TestStatus;
import com.spring.java.core.MockMvcCore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
class ProductControllerTest {
    private String PATH = "/product";

    @Autowired
    private MockMvcCore mvcCore;

    @Test
    void get() {
        // GIVEN

        // WHEN & THEN
        this.mvcCore.perform(this.PATH, TestStatus.SUCCESS);

    }
}