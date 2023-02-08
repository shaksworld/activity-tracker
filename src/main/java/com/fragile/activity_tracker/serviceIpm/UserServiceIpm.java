package com.fragile.activity_tracker.serviceIpm;

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
    public User createUser(User user) throws UserNotFoundException {
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmailAndPassword(User user) throws UserNotFoundException {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword()));
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        throw new UserNotFoundException("user not available");
    }

    @Override
    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("user not available");
        }
    }




}