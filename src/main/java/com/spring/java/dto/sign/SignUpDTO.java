package com.spring.java.dto.sign;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Builder
public record SignUpDTO(
        @NotEmpty String email,
        @NotEmpty String password,
        @NotEmpty String username
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -9044086110827916681L;
}
