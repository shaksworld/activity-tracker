package com.fragile.activity_tracker.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDto {
    @NotBlank(message ="username can not be empty")
    private  String username;
    @NotBlank(message ="username can not be empty")
    private String email;
}
