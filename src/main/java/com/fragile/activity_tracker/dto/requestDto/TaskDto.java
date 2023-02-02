package com.fragile.activity_tracker.dto.requestDto;

import com.fragile.activity_tracker.entity.User;
import com.fragile.activity_tracker.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String title;
    private String  description;

    private Date createdAt;

    private Date  updatedAt;
    private Status status;

    private User user;

}
