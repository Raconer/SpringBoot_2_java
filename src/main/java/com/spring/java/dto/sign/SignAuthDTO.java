package com.spring.java.dto.sign;

import lombok.Builder;

@Builder
public record SignAuthDTO(
   String email,
   String token
) {}
