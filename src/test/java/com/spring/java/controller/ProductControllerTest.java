package com.spring.java.controller;

import com.spring.java.common.enums.TestStatus;
import com.spring.java.core.utils.ConvertUtil;
import com.spring.java.dto.product.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles({"test"})
class ProductControllerTest {
  private String PATH = "/product";

  @Autowired private MockMvc mockMvc;

  @Test
  void get() {
    // GIVEN

    // WHEN & THEN
    try {

      var result = this.mockMvc
          .perform(
              MockMvcRequestBuilders.get(this.PATH)
          )
          .andExpect(TestStatus.SUCCESS.matcher)
          .andDo(MockMvcResultHandlers.print())
          .andReturn().getResponse().getContentAsString();

      ProductDTO data = ConvertUtil.getResult(result, ProductDTO.class);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
