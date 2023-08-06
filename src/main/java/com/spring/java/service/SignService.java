package com.spring.java.service;

import com.spring.java.dto.sign.SignDTO;
import com.spring.java.dto.sign.SignUpDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignService {
    private PasswordEncoder passwordEncoder;

    public int insert(SignDTO signDTO){
        System.out.println(signDTO.toString());
        String password = this.passwordEncoder.encode(signDTO.getPassword());
    System.out.println(password);

        return 0;
    }
}
