package com.spring.java.controller;

import com.spring.java.core.utils.ConvertUtil;
import com.spring.java.dto.sign.Sign;
import com.spring.java.dto.sign.SignInDTO;
import com.spring.java.dto.sign.SignUp;
import com.spring.java.dto.sign.SignUpDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
class SignControllerTest {

  private String PATH = "/sign";

  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("회원 가입 테스트")
  void signUp() throws Exception {
    // GIVEN
    SignUpDTO signUpDTO = SignUp.getData();
    String jsonBody = ConvertUtil.getJsonString(signUpDTO);
    // WHEN & THEN
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(this.PATH + "/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("회원 가입 Validate 검사")
  void signUpValid() throws Exception {
    // GIVEN
    SignUpDTO signUpDTO = SignUp.getValidData();
    String jsonBody = ConvertUtil.getJsonString(signUpDTO);
    // WHEN & THEN
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(this.PATH + "/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("이메일 중복 회원 가입 테스트")
  void signUpDuplicate() throws Exception {
    // GIVEN
    SignUpDTO signUpDTO = SignUp.getData();
    String jsonBody = ConvertUtil.getJsonString(signUpDTO);
    // WHEN & THEN
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(this.PATH + "/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(this.PATH + "/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody))
        .andExpect(MockMvcResultMatchers.status().isInternalServerError())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("로그인 테스트")
  void signIn() throws Exception {
    // GIVEN
    SignInDTO signInDTO = Sign.getData();
    String jsonBody = ConvertUtil.getJsonString(signInDTO);
    // WHEN & THEN
    this.mockMvc
            .perform(
                    MockMvcRequestBuilders.post(this.PATH)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("로그인 테스트_잘못된 계정")
  void signIn_NotFoundUser() throws Exception {
    // GIVEN
    SignInDTO signInDTO = Sign.getNotfoundUserData();
    String jsonBody = ConvertUtil.getJsonString(signInDTO);
    // WHEN & THEN
    this.mockMvc
            .perform(
                    MockMvcRequestBuilders.post(this.PATH)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonBody))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("로그인 테스트_잘못된 비밀번호")
  void signIn_NotMatch_Password() throws Exception {
    // GIVEN
    SignInDTO signInDTO = Sign.getNotMatchPWData();
    String jsonBody = ConvertUtil.getJsonString(signInDTO);
    // WHEN & THEN
    this.mockMvc
            .perform(
                    MockMvcRequestBuilders.post(this.PATH)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonBody))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andDo(MockMvcResultHandlers.print());
  }
}
