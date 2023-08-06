package com.spring.java.service;

import com.spring.java.dto.sign.SignDTO;
import com.spring.java.dto.sign.SignUpDTO;
import com.spring.java.mapper.SignMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SignService {
    private SignMapper signMapper;
    private PasswordEncoder passwordEncoder;

    // CREATE
    public int insert(SignDTO signDTO){
        String password = this.passwordEncoder.encode(signDTO.getPassword());

        signDTO.setId(UUID.randomUUID().toString());
        signDTO.setPassword(password);
        signDTO.setRegDate(LocalDateTime.now());
        return  this.signMapper.insert(signDTO);    }
}
