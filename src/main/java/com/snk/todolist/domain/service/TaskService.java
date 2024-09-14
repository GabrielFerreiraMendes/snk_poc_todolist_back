package com.snk.todolist.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snk.todolist.domain.dto.TaskDTO;
import com.snk.todolist.domain.model.Task;
import com.snk.todolist.domain.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(TaskDTO dto) {
        return this.taskRepository.save(new Task(dto));
    }

    public Task updateTaskStatus(Long taskId) {
        var task = this.taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        if (task.getStatus().equalsIgnoreCase("TODO")) {
            task.setStatus("DONE");
        }

        return this.taskRepository.save(task);
    }

    public List<Task> findByUserId(Long userId){
        return this.taskRepository.findByUserId(userId);
    }    
    
}
