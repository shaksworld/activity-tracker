package com.fragile.activity_tracker.serviceIpm;

import com.fragile.activity_tracker.dto.requestDto.TaskDto;
import com.fragile.activity_tracker.dto.requestDto.UserDto;
import com.fragile.activity_tracker.entity.User;
import com.fragile.activity_tracker.error.UserNotFoundException;
import com.fragile.activity_tracker.repository.UserRepository;
import com.fragile.activity_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceIpm implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(User user) {
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getName(), savedUser.getUsername(), savedUser.getEmail(),  user.getTasks());
    }

    @Override
    public UserDto findUserByEmailAndPassword(User user) throws UserNotFoundException {
        Optional<UserDto> foundUser = Optional.ofNullable(userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword()));
        if(foundUser.isPresent()) {
            foundUser.get();
        }throw new UserNotFoundException("Invalid email and password");
    }

    @Override
    public UserDto findUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserDto(user.get().getName(), user.get().getUsername(), user.get().getEmail(), user.get().getTasks());
        } else {
            throw new UserNotFoundException("user not available");
        }
    }
}
