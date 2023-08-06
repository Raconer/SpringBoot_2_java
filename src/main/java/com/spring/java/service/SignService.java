package com.spring.java.service;

import com.spring.java.dto.sign.SignUpDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignService {
    private PasswordEncoder passwordEncoder;

    public int insert(SignUpDTO signUpDTO){

        String password = this.passwordEncoder.encode(signUpDTO.getPassword());
            System.out.println(password);

        return 0;
    }
}
