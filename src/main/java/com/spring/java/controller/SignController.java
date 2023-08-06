package com.spring.java.controller;

import com.spring.java.common.response.CommonRes;
import com.spring.java.dto.sign.SignUpDTO;
import com.spring.java.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sign")
@AllArgsConstructor
public class SignController {

  private SignService signService;

  @PostMapping("/up")
  public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDTO signUpDTO) {
    this.signService.insert(signUpDTO);
    return CommonRes.Def("SUCESS");
  }
}
