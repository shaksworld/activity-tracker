package com.fragile.activity_tracker.service;

import com.fragile.activity_tracker.dto.requestDto.UserDto;
import com.fragile.activity_tracker.entity.User;
import com.fragile.activity_tracker.error.UserNotFoundException;

public interface UserService {

    UserDto createUser(User user);

    UserDto findUserByEmailAndPassword(User user) throws UserNotFoundException;

     UserDto findUserById(Long id) throws UserNotFoundException;
}
