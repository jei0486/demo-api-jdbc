package com.demo.api.model;

import com.demo.api.domain.user.UserState;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class User {

    private String id;

    @NotBlank
    @Size(max = 50)
    private String loginId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private UserState state;

    private String password;

    @NotNull
    private LocalDateTime createdAt;
}
