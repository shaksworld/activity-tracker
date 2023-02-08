package com.fragile.activity_tracker.repository;

import com.fragile.activity_tracker.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @Mock
    UserRepository userRepository;

    @Test
    public void testFindUserByEmailAndPassword() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setUsername("john doe");
        user.setPassword("password");
        user.setEmail("johndoe@example.com");

        when(userRepository.findUserByEmailAndPassword("johndoe@example.com", "password")).thenReturn(user);

        User foundUser = userRepository.findUserByEmailAndPassword("johndoe@example.com", "password");

        assertEquals(user, foundUser);
    }
}


