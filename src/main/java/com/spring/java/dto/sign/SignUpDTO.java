package com.spring.java.dto.sign;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Builder
public record SignUpDTO(
    @NotEmpty @Email String email, @NotEmpty String password, @NotEmpty String username) {}
