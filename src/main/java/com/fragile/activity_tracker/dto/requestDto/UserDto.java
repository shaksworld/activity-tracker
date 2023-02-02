package com.fragile.activity_tracker.dto.requestDto;

import com.fragile.activity_tracker.entity.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message ="username can not be empty")
    private String name;

    @NotBlank(message ="username can not be empty")
    private  String username;
    @NotBlank(message ="username can not be empty")
    private String email;
    private List<Task> tasks;

}
