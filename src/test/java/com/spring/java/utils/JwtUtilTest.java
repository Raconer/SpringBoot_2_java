package com.spring.java.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.spring.java.common.exception.CustomAuthenticationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
class JwtUtilTest {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtilTest.class.getName());

  @Autowired private JwtUtil jwtUtil;

  @Value("${common.jwt.expire_time}")
  private long expiredTime;

  private final String testEmail = "test@test.com";
  private String token;

  @BeforeEach
  void setUpBeforeEach() {
    this.token = this.jwtUtil.create(this.testEmail);
  }

  @Test
  void create() {
    // GIVEN
    // WHEN
    // THEN
    assertNotNull(this.token);
  }

  @Test
  void isValidate() {
    // GIVEN
    // WHEN
    boolean isValidate = this.jwtUtil.isValidate(this.token);
    // THEN
    assertTrue(isValidate);
  }

  void isException(Throwable cause, Class<? extends Exception> exceptionType) {
    logger.info(exceptionType.getName() + " -> " + cause.getMessage());
    assertNotNull(cause);
    assertTrue(exceptionType.isInstance(cause));
  }

  @Test
  void testExpiredJwtException() {
    // GIVEN

    // WHEN & THEN
    // 정상 토큰 확인
    this.isValidate();

    // Expired Exception 체크
    CustomAuthenticationException exception =
        assertThrows(
            CustomAuthenticationException.class,
            () -> {
              try {
                Thread.sleep(this.expiredTime);
                this.isValidate();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    this.isException(exception.getCause(), ExpiredJwtException.class);
  }

  @Test
  void testMalformedJwtException() {
    // GIVEN

    // WHEN & THEN
    // 정상 토큰 확인
    this.isValidate();

    // Expired Exception 체크
    CustomAuthenticationException exception =
        assertThrows(
            CustomAuthenticationException.class,
            () -> {
              this.token = "invalid_jwt_token_format";
              this.isValidate();
            });

    this.isException(exception.getCause(), MalformedJwtException.class);
  }

  @Test
  void testSignatureException() {
    // GIVEN

    // WHEN & THEN
    // 정상 토큰 확인
    this.isValidate();

    // Expired Exception 체크
    CustomAuthenticationException exception =
        assertThrows(
            CustomAuthenticationException.class,
            () -> {
              this.token =
                  "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE2OTEyOTMxODQsImlhdCI6MTY5MTI5MzE4MywiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIn0.IrJpEdFEqP5OVn1DimVc9GleC39VvHIn1x7-R9Y4j2I";
              this.isValidate();
            });

    this.isException(exception.getCause(), SignatureException.class);
  }

  // TODO : 추후 테스트 코드 작성 필요

  /*
  @Test
  void testUnsupportedJwtException() {
      // GIVEN
      // WHEN & THEN
    }
  */
  /*
    @Test
    void testAuthenticationException() {
      // GIVEN
      // WHEN & THEN
    }
  */
  /*
    @Test
    void testIllegalArgumentException() {
      // GIVEN
      // WHEN & THEN
    }
  */
}
