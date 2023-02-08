package com.fragile.activity_tracker.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fragile.activity_tracker.entity.Task;
import com.fragile.activity_tracker.entity.User;
import com.fragile.activity_tracker.error.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    private User user;

    private Task task;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setEmail("johndoe@email.com");
        List<Task> tasks = new ArrayList<>();
        user.setTasks(tasks);
    }

    @Test
    public void createUserTest() throws UserNotFoundException {
        when(userService.createUser(any(User.class))).thenReturn(user);
        User result = userService.createUser(user);
        assertEquals(user, result);
    }

    @Test
    public void findUserByEmailAndPasswordTest() throws UserNotFoundException {
        when(userService.findUserByEmailAndPassword(any(User.class))).thenReturn(user);
        User result = userService.findUserByEmailAndPassword(user);
        assertEquals(user, result);
    }

    @Test
    public void findUserByIdTest() throws UserNotFoundException {
        when(userService.findUserById(any(Long.class))).thenReturn(user);
        User result = userService.findUserById(1L);
        assertEquals(user, result);
    }
}
