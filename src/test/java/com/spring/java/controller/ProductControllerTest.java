package com.spring.java.controller;

import com.spring.java.dto.sign.Sign;
import com.spring.java.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
class ProductControllerTest {
  private String PATH = "/product";

  @Autowired private MockMvc mockMvc;
  @Autowired private JwtUtil jwtUtil;

  private String token;

  @BeforeEach
  public void setUpEach() {
    this.token = jwtUtil.create(Sign.EMAIL);
  }

  @Test
  void get() throws Exception {
    // GIVEN

    // WHEN & THEN
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(this.PATH).header("Authorization", "Bearer " + this.token))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }
}
