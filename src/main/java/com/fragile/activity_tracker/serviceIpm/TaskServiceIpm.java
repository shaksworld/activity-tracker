package com.fragile.activity_tracker.serviceIpm;

import com.fragile.activity_tracker.entity.Task;
import com.fragile.activity_tracker.enums.Status;
import com.fragile.activity_tracker.error.UserNotFoundException;
import com.fragile.activity_tracker.repository.TaskRepository;
import com.fragile.activity_tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceIpm implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(Long id) throws UserNotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            return task.get();
        } throw new UserNotFoundException("Task not found");

    }

    @Override
    public void updateTask(Task updateTask) {
     taskRepository.save(updateTask);

    }

    //toggle status forward
    @Override
    public void changeTaskStatusToNext(Long id) {
        Optional<Task> optional = taskRepository.findById(id);
        if(optional.isPresent()){
          taskRepository.moveBackward("ACTIVE", id);
          taskRepository.updateTime(LocalDateTime.now(),  id);
        }


    }

    //toggle status backward
    @Override
    public void changeTaskStatusToNextBackWard(Long id) {
        Optional<Task> optional = taskRepository.findById(id);
        if(optional.isPresent()){
            taskRepository.moveBackward("PENDING", id);
            taskRepository.updateTime(LocalDateTime.now(),  id);
        }
    }

    // change task to done

    @Override
    public void changeTaskToDone(Long id) {
        Optional<Task> optional = taskRepository.findById(id);
        if(optional.isPresent()){
            taskRepository.moveBackward("DONE", id);
            taskRepository.completedTime(LocalDateTime.now(),  id);
        }
    }



    @Override
    public void deleteTask(Long id) {
      taskRepository.deleteById(id);
    }
}
