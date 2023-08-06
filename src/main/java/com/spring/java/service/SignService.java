package com.spring.java.service;

import com.spring.java.dto.sign.SignAuthDTO;
import com.spring.java.dto.sign.SignDTO;
import com.spring.java.dto.sign.SignInDTO;
import com.spring.java.dto.sign.SignUpDTO;
import com.spring.java.mapper.SignMapper;
import com.spring.java.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
  private JwtUtil jwtUtil;

  // CREATE
  public int insert(SignDTO signDTO) {
    String password = this.passwordEncoder.encode(signDTO.getPassword());

    signDTO.setId(UUID.randomUUID().toString());
    signDTO.setPassword(password);
    signDTO.setRegDate(LocalDateTime.now());
    return this.signMapper.insert(signDTO);
  }

  // READ

  public SignAuthDTO signIn(SignInDTO signInDTO) {
    String email = signInDTO.email();
    SignDTO signDTO = this.signMapper.get(email);

    if (signDTO == null) throw new UsernameNotFoundException("Not Found User");

    String password = signInDTO.password();

    if (!passwordEncoder.matches(password, signDTO.getPassword()))
      throw new BadCredentialsException("Not Equals Sign In Data");

    String token = this.jwtUtil.create(email);
    return SignAuthDTO.builder().email(email).token(token).build();
  }
}
