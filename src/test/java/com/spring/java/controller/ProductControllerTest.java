package com.spring.java.controller;

import com.spring.java.common.enums.TestStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
class ProductControllerTest {
  private String PATH = "/product";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void get() {
    // GIVEN

    // WHEN & THEN
    try {

      this.mockMvc
              .perform(MockMvcRequestBuilders.get(this.PATH))
              .andExpect(TestStatus.SUCCESS.matcher)
              .andDo(MockMvcResultHandlers.print())
              .andReturn();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
