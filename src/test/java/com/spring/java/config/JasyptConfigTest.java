package com.spring.java.config;

import static org.junit.jupiter.api.Assertions.*;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
class JasyptConfigTest {

  @Value("${jasypt.test.text}")
  String decryptText;

  @Test
  public void testEncryptDecrypt() {
    String secretKey = "TEST_SECRET_KEY";
    String originalText = "Hello, Jasypt!";

    // StringEncryptor 생성
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(secretKey);
    encryptor.setConfig(config);

    // 암호화 및 복호화
    String encryptedText = encryptor.encrypt(originalText);
    String decryptedText = encryptor.decrypt(encryptedText);

    assertNotEquals(originalText, encryptedText);
    assertEquals(originalText, decryptedText);
  }

  @Test
  public void testApplication() {
    String originTest = "Jasypt Test Text Success";
    assertEquals(originTest, decryptText);
  }
}
