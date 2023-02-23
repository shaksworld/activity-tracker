package com.fragile.activity_tracker.service;

import com.fragile.activity_tracker.entity.Task;
import com.fragile.activity_tracker.error.UserNotFoundException;
import com.fragile.activity_tracker.repository.TaskRepository;
import com.fragile.activity_tracker.serviceIpm.TaskServiceIpm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepositoryMock;
    @InjectMocks
    private TaskServiceIpm taskService;

    private Task task;
    private List<Task> taskList;

    @Before
    public void setUp() {
        task = new Task();
        task.setTitle("Test Title");
        task.setDescription("Test Description");

        taskList = new ArrayList<>();
        taskList.add(task);
    }

    @Test
    public void testCreateTask() {
        when(taskRepositoryMock.save(task)).thenReturn(task);
        Task createdTask = taskService.createTask(task);
        Assert.assertEquals(task, createdTask);
        verify(taskRepositoryMock, times(1)).save(task);
    }

//    @Test
//    public void testGetAllTask() {
//        when(taskRepositoryMock.findAll()).thenReturn(taskList);
//        List<Task> allTasks = taskService.getAllTask();
//        Assert.assertEquals(taskList, allTasks);
//        verify(taskRepositoryMock, times(1)).findAll();
//    }

//    @Test
//    public void testGetAllTasks() {
//        // setup mock behavior
//        Task task1 = Task.builder()
//                .id(1L)
//                .title("task1")
//                .build();
//        Task task2 = Task.builder()
//                .id(2L)
//                .title("task2")
//                .build();
//        List<Task> tasks = Arrays.asList(task1, task2);
//        when(taskRepositoryMock.findAll()).thenReturn(tasks);
//
//        // call the method being tested
////        List<Task> result = taskService.getAllTask();
//
//        // verify the results
//        Assert.assertEquals(tasks, result);
//    }

    @Test
    public void testGetTask() throws UserNotFoundException {
        Long id = 1L;
        when(taskRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(task));
        Task returnedTask = taskService.getTask(id);
        Assert.assertEquals(task, returnedTask);
        verify(taskRepositoryMock, times(1)).findById(id);
    }

    @Test
    public void testUpdateTask() {
        taskService.updateTask(task);
        verify(taskRepositoryMock, times(1)).save(task);
    }

    @Test
    public void testChangeTaskStatusToNextBackward() {
        Long id = 1L;
        taskService.changeTaskStatusToNextBackWard(id);
        verify(taskRepositoryMock, times(1)).findById(id);
    }

    @Test
    public void testChangeTaskToDone() {
        Long id = 1L;
        taskService.changeTaskToDone(id);
        verify(taskRepositoryMock, times(1)).findById(id);
    }

    @Test
    public void testChangeTaskStatusToNext() {
        Long id = 1L;
        taskService.changeTaskStatusToNext(id);
        verify(taskRepositoryMock, times(1)).findById(id);
    }

}
