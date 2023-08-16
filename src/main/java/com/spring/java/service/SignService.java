package com.spring.java.service;

import com.spring.java.dto.sign.SignAuthDTO;
import com.spring.java.dto.sign.SignDTO;
import com.spring.java.dto.sign.SignInDTO;
import com.spring.java.mapper.SignMapper;
import com.spring.java.utils.JwtUtil;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignService {
  private SignMapper signMapper;
  private AuthService authService;
  private PasswordEncoder passwordEncoder;
  private JwtUtil jwtUtil;

  // CREATE
  public int insert(SignDTO signDTO) {
    String password = this.passwordEncoder.encode(signDTO.getPassword());

    signDTO.setId(UUID.randomUUID().toString());
    signDTO.setPassword(password);
    signDTO.setRegDate(LocalDateTime.now());
    return this.signMapper.insert(signDTO);
  }

  public SignAuthDTO signIn(SignInDTO signInDTO) {
    String email = signInDTO.email();
    SignDTO signDTO = this.authService.signByEmail(email);

    String password = signInDTO.password();

    if (!passwordEncoder.matches(password, signDTO.getPassword()))
      throw new BadCredentialsException("Not Equals Sign In Data");

    String token = this.jwtUtil.create(email);
    return SignAuthDTO.builder().email(email).token(token).build();
  }
}
