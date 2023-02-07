package com.fragile.activity_tracker.service;

import com.fragile.activity_tracker.entity.Task;
import com.fragile.activity_tracker.error.UserNotFoundException;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    List<Task> getAllTask();

    Task getTask(Long id) throws UserNotFoundException;

    void updateTask(Task updateTask);

    //toggle status forward
    void changeTaskStatusToNextBackWard(Long id);

    void changeTaskToDone(Long id);
    void changeTaskStatusToNext(Long id);

    void deleteTask(Long id);
}
