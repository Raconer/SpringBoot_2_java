package com.spring.java.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptor.password}")// 암호화에 사용할 비밀번호 주로 VMoption으로 설정
    String encryptKey;

    @Bean(name = "jasyptStringEncryptor") // application.yml에 사용할 bean이름 설정
    StringEncryptor stringEncryptor() {

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor(); // 암호화, 복호화를 할때 사용
        SimpleStringPBEConfig config = new SimpleStringPBEConfig(); // 암호화, 복호화 구성 정보

        config.setPassword(encryptKey); // 암호화에 사용할 키값
        config.setPoolSize("1"); // 객체 pooling 크기 1로 설정
        config.setAlgorithm("PBEWithMD5AndDES"); // 암호화 알고리즘
        config.setStringOutputType("base64"); // 암호화 결과 인코딩
        config.setKeyObtentionIterations("1000"); // 암호화 키를 얻기 위해 반복 수행 횟수
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // 암호화 salt값
        encryptor.setConfig(config);
        return encryptor;
    }
}
