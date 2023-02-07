package com.fragile.activity_tracker.service;

import com.fragile.activity_tracker.dto.requestDto.UserDto;
import com.fragile.activity_tracker.entity.User;
import com.fragile.activity_tracker.error.UserNotFoundException;

public interface UserService {

    User createUser(User user) throws UserNotFoundException;

    User findUserByEmailAndPassword(User user) throws UserNotFoundException;

     User findUserById(Long id) throws UserNotFoundException;


}
