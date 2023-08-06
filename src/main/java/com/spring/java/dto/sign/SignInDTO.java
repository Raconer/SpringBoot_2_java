package com.spring.java.dto.sign;

import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
public record SignInDTO(@NotEmpty @Email String email, @NotEmpty String password) {}
