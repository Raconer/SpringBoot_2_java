package com.spring.java.service;

import com.spring.java.dto.sign.SignDTO;
import com.spring.java.mapper.SignMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
  private SignMapper signMapper;
  // READ
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.signByEmail(email);
  }

  public SignDTO signByEmail(String email) {
    SignDTO signDTO = this.signMapper.get(email);
    if (signDTO == null) throw new UsernameNotFoundException("Not Found User");
    return signDTO;
  }
}
