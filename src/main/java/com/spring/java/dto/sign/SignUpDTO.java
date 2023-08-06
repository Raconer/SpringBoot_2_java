package com.spring.java.dto.sign;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
public class SignUpDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -9044086110827916681L;

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
