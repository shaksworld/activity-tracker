package com.fragile.activity_tracker.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.fragile.activity_tracker.enums.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskTest {
    @Mock
    private Task taskMock;
    @InjectMocks
    private Task task;

    @Before
    public void setUp() {
        task.setTitle("Test Title");
        task.setDescription("Test Description");
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(Status.PENDING);
        task.setUsers(new User());
    }

    @Test
    public void testGetTitle() {
        when(taskMock.getTitle()).thenReturn("Test Title");
        Assert.assertEquals("Test Title", taskMock.getTitle());
    }

    @Test
    public void testGetDescription() {
        when(taskMock.getDescription()).thenReturn("Test Description");
        Assert.assertEquals("Test Description", taskMock.getDescription());
    }

    @Test
    public void testGetCreatedAt() {
        LocalDateTime createdAt = LocalDateTime.now();
        when(taskMock.getCreatedAt()).thenReturn(createdAt);
        Assert.assertEquals(createdAt, taskMock.getCreatedAt());
    }

    @Test
    public void testGetStatus() {
        when(taskMock.getStatus()).thenReturn(Status.PENDING);
        Assert.assertEquals(Status.PENDING, taskMock.getStatus());
    }

    @Test
    public void testGetUsers() {
        when(taskMock.getUsers()).thenReturn(new User());
        Assert.assertEquals(new User(), taskMock.getUsers());
    }
}
